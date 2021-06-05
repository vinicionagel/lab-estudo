package br.com.labestudo.api.exception;

import br.com.labestudo.api.model.dto.MessagesErrorDto;
import lombok.Getter;

import java.util.Collection;

@Getter
public class MessagesException extends Exception {

    private Collection<MessagesErrorDto> messagesErrorDtoList;

    public MessagesException(Collection<MessagesErrorDto> messagesErrorDtoList) {
        this.messagesErrorDtoList = messagesErrorDtoList;
    }

}
