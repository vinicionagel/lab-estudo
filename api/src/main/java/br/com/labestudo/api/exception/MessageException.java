package br.com.labestudo.api.exception;

import lombok.Getter;

@Getter
public class MessageException extends Exception {

    private String key;
    private String[] args;

    public MessageException(String key, String... args) {
        this.key = key;
        this.args = args;
    }

    public MessageException(Throwable throwable, String key, String... args) {
        super(throwable);
        this.key = key;
        this.args = args;
    }
}
