package br.com.labestudo.api.exception;

import java.io.IOException;

public class PathConfigurableException extends IOException {
    private static final long serialVersionUID = 1L;

    public PathConfigurableException() {
    }

    public PathConfigurableException(String msg) {
        super(msg);
    }

    public PathConfigurableException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PathConfigurableException(Throwable cause) {
        super(cause);
    }
}
