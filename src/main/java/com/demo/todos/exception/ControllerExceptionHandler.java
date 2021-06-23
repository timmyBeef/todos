package com.demo.todos.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {DataNotExistException.class})
    public ResponseEntity<TodosErrorDto> handleTransactionNotExistException(DataNotExistException e) {
        log.info("does not have data", e);
        return new ResponseEntity(new TodosErrorDto("DATA_NOT_EXIST", e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TodosErrorDto> handleException(Exception exception, WebRequest request) {
        log.error("There is Exception occurred", exception);
        return new ResponseEntity(new TodosErrorDto(exception.getMessage(), "error"), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
