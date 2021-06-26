package br.com.labestudo.api.exception;

import java.util.Collection;

import br.com.labestudo.api.model.dto.MessageErrorDto;
import lombok.Getter;

@Getter
public class MessagesException extends Exception {

    private static final long serialVersionUID = 1L;

    private Collection<MessageErrorDto> messageErrorDtoList;

    public MessagesException(Collection<MessageErrorDto> messageErrorDtoList) {
        this.messageErrorDtoList = messageErrorDtoList;
    }

}
