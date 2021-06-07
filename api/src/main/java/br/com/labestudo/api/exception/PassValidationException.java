package br.com.labestudo.api.exception;

import br.com.labestudo.api.model.dto.MessageErrorDto;

import java.util.Collection;

public class PassValidationException extends MessagesException {

    public PassValidationException(Collection<MessageErrorDto> messageErrorDtoList) {
        super(messageErrorDtoList);
    }
}
