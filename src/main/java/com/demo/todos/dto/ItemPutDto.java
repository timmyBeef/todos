package com.demo.todos.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemPutDto {
    private String description;
    private LocalDateTime deadline;
    private Integer orderNum;
}
