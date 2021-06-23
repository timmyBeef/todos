package com.demo.todos.mapper;

import com.demo.todos.dto.IListGetDto;
import com.demo.todos.dto.IListPostDto;
import com.demo.todos.entity.IList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IListMapper {

    IListGetDto fromEntity(IList iList);

    IList toEntity(IListPostDto dto);

    void updateIList(IListPostDto dto, @MappingTarget IList iList);

}
