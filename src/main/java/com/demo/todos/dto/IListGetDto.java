package com.demo.todos.dto;

import com.demo.todos.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IListGetDto {

//    @Schema(value = "list id")
    private Long id;
    private String name;
    private LocalDateTime dueDate;
    private LocalDateTime createTime;
//    private List<ItemGetDto> items;
}
