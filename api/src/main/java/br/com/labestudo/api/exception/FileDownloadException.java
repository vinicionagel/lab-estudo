package br.com.labestudo.api.exception;

import java.io.IOException;

public class FileDownloadException extends IOException {
    private static final long serialVersionUID = 1L;

    public FileDownloadException() {
    }

    public FileDownloadException(String msg) {
        super(msg);
    }

    public FileDownloadException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FileDownloadException(Throwable cause) {
        super(cause);
    }
}
