package com.demo.todos.exception;


public class DataNotExistException extends RuntimeException {
    public DataNotExistException(String message) {
        super(message);
    }
}
