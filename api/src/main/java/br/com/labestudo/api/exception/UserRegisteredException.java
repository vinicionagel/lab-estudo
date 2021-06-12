package br.com.labestudo.api.exception;

public class UserRegisteredException extends MessageException {

	private static final long serialVersionUID = 1L;

	public UserRegisteredException() {
		super("user.registered.error");
	}
}
