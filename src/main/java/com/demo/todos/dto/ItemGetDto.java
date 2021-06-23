package com.demo.todos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemGetDto {
    private Long id;
    private String description;
    private LocalDateTime deadline;
    private Integer orderNum;
}
