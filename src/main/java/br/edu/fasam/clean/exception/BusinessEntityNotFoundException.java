package br.edu.fasam.clean.exception;

public final class BusinessEntityNotFoundException extends RuntimeException {

    public BusinessEntityNotFoundException() {
    }

    public BusinessEntityNotFoundException(String message) {
        super(message);
    }

    public BusinessEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
