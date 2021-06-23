package com.demo.todos.mapper;

import com.demo.todos.dto.IListPostDto;
import com.demo.todos.dto.ItemGetDto;
import com.demo.todos.dto.ItemPostDto;
import com.demo.todos.dto.ItemPutDto;
import com.demo.todos.entity.IList;
import com.demo.todos.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {

    ItemGetDto fromEntity(Item item);

    ItemPostDto fromEntityToPostDto(Item item);

    Item toEntity(ItemPostDto dto);

    void updateItem(ItemPostDto dto, @MappingTarget Item item);

    void updateItem(ItemPutDto dto, @MappingTarget Item item);

}
