package br.com.labestudo.api.exception;

public class UserRegisteredException extends MessageException {

    public UserRegisteredException() {
        super("user.registered.error", null);
    }
}
