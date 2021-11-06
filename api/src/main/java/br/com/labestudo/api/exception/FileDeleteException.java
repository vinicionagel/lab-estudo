package br.com.labestudo.api.exception;

import java.io.IOException;

public class FileDeleteException extends IOException {
    private static final long serialVersionUID = 1L;

    public FileDeleteException() {
    }

    public FileDeleteException(String msg) {
        super(msg);
    }

    public FileDeleteException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FileDeleteException(Throwable cause) {
        super(cause);
    }
}
