package br.com.labestudo.api.exception;

public class SelfRegisterFailedValidationException extends MessageException {

    public SelfRegisterFailedValidationException() {
        super("selfRegister.failValidation");
    }

}
