package com.demo.todos.controllers;

import com.demo.todos.dto.IListGetDto;
import com.demo.todos.dto.IListPostDto;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodosControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void saveList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<IListPostDto> entity = new HttpEntity<>(
                IListPostDto.builder()
                        .name("build list")
                        .dueDate(LocalDateTime.now())
                        .build()
                , headers);

        ResponseEntity<IListGetDto> response = testRestTemplate.postForEntity("/api/v1/todos/list/1",entity, IListGetDto.class);
        System.out.println(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

}
