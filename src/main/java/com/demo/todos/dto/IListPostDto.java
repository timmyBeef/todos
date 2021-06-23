package com.demo.todos.dto;

import com.demo.todos.entity.Item;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class IListPostDto {

    private String name;
    private LocalDateTime dueDate;
}
