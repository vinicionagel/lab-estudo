package br.com.labestudo.api.exception;

import java.util.Collection;

import br.com.labestudo.api.model.dto.MessageErrorDto;

public class PassValidationException extends MessagesException {

	private static final long serialVersionUID = 1L;

	public PassValidationException(Collection<MessageErrorDto> messageErrorDtoList) {
		super(messageErrorDtoList);
	}
}
