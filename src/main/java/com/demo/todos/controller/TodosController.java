package com.demo.todos.controller;

import com.demo.todos.dto.*;
import com.demo.todos.entity.IList;
import com.demo.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Todos API")
@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodosController {

    private final TodoService service;

    @Operation(summary = "find all lists by user's id")
    @GetMapping("/list/all/{userId}")
    public List<IListGetDto> getAllList(@PathVariable Long userId) {
        return service.findAllListByUserId(userId);
    }

    @Operation(summary = "create list with user's id")
    @PostMapping("/list/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public IListGetDto createList(@RequestBody IListPostDto dto, @PathVariable Long userId) {
        return service.createIList(dto, userId);
    }

    @Operation(summary = "save list with user's id")
    @PutMapping("/list/{listId}")
    public IListGetDto saveList(@RequestBody IListPostDto dto, @PathVariable Long listId) {
        return service.saveIList(dto, listId);
    }

    @Operation(summary = "find all items by list's id")
    @GetMapping("/item/all/{listId}")
    public List<ItemGetDto> getAllItems(@PathVariable Long listId) {
        return service.findItemsByIListId(listId);
    }

    @Operation(summary = "create item with list's id")
    @PostMapping("/item/{listId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemGetDto createItem(@RequestBody ItemPostDto dto, @PathVariable Long listId) {
        return service.createItem(dto, listId);
    }

    @Operation(summary = "save item with item's id")
    @PutMapping("/item/{itemId}")
    public ItemGetDto saveItem(@RequestBody ItemPutDto dto, @PathVariable Long itemId) {
        return service.saveItem(dto, itemId);
    }

    @Operation(summary = "after items moved, save all items' new order with list's id")
    @PutMapping("/item/orders/{listId}")
    public void saveItemOrders(@RequestBody List<ItemPostDto> items, @PathVariable Long listId) {
        service.saveMovedItemsOrder(items);
    }

    @Operation(summary = "remove list with list's id")
    @DeleteMapping("/list/{listId}")
    public void removeList(@PathVariable Long listId) {
        service.removeList(listId);
    }

    @Operation(summary = "remove item with item's id")
    @DeleteMapping("/item/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        service.removeItem(itemId);
    }
}
