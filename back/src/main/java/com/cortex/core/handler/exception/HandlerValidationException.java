package com.cortex.core.handler.exception;

public class HandlerValidationException extends Exception {

    private static final long serialVersionUID = 3112689385687904001L;

    public HandlerValidationException() {
        super();
    }

    public HandlerValidationException(final String message) {
        super(message);
    }
}
