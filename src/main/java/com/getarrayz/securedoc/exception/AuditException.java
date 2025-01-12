package com.getarrayz.securedoc.exception;

public class AuditException extends RuntimeException {

    public AuditException(String message) {
        super(message);
    }

    public AuditException() {
        super("Неизвестная ошибка при выполнении аудита сущности");
    }
}
