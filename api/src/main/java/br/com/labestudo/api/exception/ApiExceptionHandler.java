package br.com.labestudo.api.exception;

import br.com.labestudo.api.model.dto.ErrorDto;
import br.com.labestudo.api.model.dto.ErrorsDto;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorsDto errorsDto = new ErrorsDto();
        ex.getBindingResult().getAllErrors().forEach(objectError -> errorsDto.getErrors().add(montarError(objectError)));
        return handleExceptionInternal(ex, errorsDto, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        InvalidFormatException cause = (InvalidFormatException) ex.getCause();
        return handleExceptionInternal(ex, new ErrorDto(messageSource.getMessage("message.invalid", new String[]{cause.getPath().stream().findFirst().map(JsonMappingException.Reference::getFieldName).orElse("")}, LocaleContextHolder.getLocale())), headers, status, request);
    }

    private ErrorDto montarError(ObjectError objectError) {
        return new ErrorDto(messageSource.getMessage(objectError, LocaleContextHolder.getLocale()));
    }
}
