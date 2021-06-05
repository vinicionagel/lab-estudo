package br.com.labestudo.api.exception;

import br.com.labestudo.api.model.dto.MessagesErrorDto;

import java.util.Collection;

public class PassValidationException extends MessagesException {

    public PassValidationException(Collection<MessagesErrorDto> messagesErrorDtoList) {
        super(messagesErrorDtoList);
    }
}
