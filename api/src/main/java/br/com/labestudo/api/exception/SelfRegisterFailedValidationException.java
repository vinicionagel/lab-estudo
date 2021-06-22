package br.com.labestudo.api.exception;

public class SelfRegisterFailedValidationException extends MessageException {

    public SelfRegisterFailedValidationException(String key) {
        super(key);
    }

}
