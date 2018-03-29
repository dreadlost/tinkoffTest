package ru.tinkoff.testTask.exception;

public class TinkoffException extends RuntimeException {
    public TinkoffException(String message, Throwable throwable){
        super(message, throwable);
    }
}
