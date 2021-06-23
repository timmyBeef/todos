package com.demo.todos.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TodosErrorDto {
    private  String message;
    private  String details;
}
