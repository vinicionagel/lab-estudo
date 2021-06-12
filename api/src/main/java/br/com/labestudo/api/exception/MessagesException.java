package br.com.labestudo.api.exception;

import br.com.labestudo.api.model.dto.MessageErrorDto;
import lombok.Getter;

import java.util.Collection;

@Getter
public class MessagesException extends Exception {

    private Collection<MessageErrorDto> messageErrorDtoList;

    public MessagesException(Collection<MessageErrorDto> messageErrorDtoList) {
        this.messageErrorDtoList = messageErrorDtoList;
    }

}
