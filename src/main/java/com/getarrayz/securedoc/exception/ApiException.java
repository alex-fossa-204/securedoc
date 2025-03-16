package com.getarrayz.securedoc.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        super("Неизвестная ошибка.");
    }

}
