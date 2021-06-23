package com.demo.todos.service;

import com.demo.todos.dto.*;
import com.demo.todos.entity.IList;
import com.demo.todos.entity.Item;
import com.demo.todos.entity.User;
import com.demo.todos.mapper.ItemMapper;
import com.demo.todos.repository.IListRepository;
import com.demo.todos.repository.ItemRepository;
import com.demo.todos.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IListRepository iListRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    Faker faker;


    @Test
    void findAllListByUserId() {
        givenDB1ListAnd3Items();
        findListSizeShouldBeOne();
    }

    @Test
    void findItemsByIListId() {
        givenDB1ListAnd3Items();
        findItemSizeShouldBe3();
    }

    @Test
    void createIList() {
        givenDB1ListAnd3Items();
        createOneMoreListSizeShouldBe2();
    }

    @Test
    void saveIList() {
        givenDB1ListAnd3Items();
        saveListNameShouldBeSame();
    }

    @Test
    void createItem() {
        givenDB1ListAnd3Items();
        createOneMoreItemSizeShouldBe4();
    }

    @Test
    void saveItem() {
        givenDB1ListAnd3Items();
        saveItemNameShouldBeSame();
    }

    @Test
    void saveMovedItemsOrder() {
        givenDB1ListAnd3Items();
        saveMovedItemsOrderShouldBeSame();

    }

    @Test
    void removeList() {
        givenDB1ListAnd3Items();
        removeListShouldBeNull();
    }

    @Test
    void removeItem() {
        givenDB1ListAnd3Items();
        removeItemShouldBeNull();
    }

    private void saveListNameShouldBeSame() {
        todoService.saveIList(
                IListPostDto.builder()
                        .name("save list")
                        .build(),1L);
        assertEquals("save list", iListRepository.findById(1L).get().getName());
    }
    private void createOneMoreItemSizeShouldBe4() {
        todoService.createItem(
                ItemPostDto.builder()
                        .description("create item")
                        .build(), 1L);
        assertEquals(4, itemRepository.findAll().size());
    }
    private void saveItemNameShouldBeSame() {
        todoService.saveItem(
                ItemPutDto.builder()
                        .description("save item")
                        .build(), 1L);
        assertEquals("save item", itemRepository.findById(1L).get().getDescription());
    }
    private void saveMovedItemsOrderShouldBeSame() {
        List<Item> items = iListRepository.findById(1L).get().getItems();
        Item tmp = items.get(0);
        items.set(0, items.get(1));
        items.set(1, tmp);
        List<ItemPostDto> dto = new ArrayList<>();
        items.stream().forEach((it) -> dto.add(itemMapper.fromEntityToPostDto(it)));

        todoService.saveMovedItemsOrder(dto);
        List<Item> movedItems = iListRepository.findById(1L).get().getItems();

        assertEquals(0, movedItems.get(0).getOrderNum());
        assertEquals(1, movedItems.get(1).getOrderNum());
        assertEquals(2, movedItems.get(2).getOrderNum());
    }
    private void removeListShouldBeNull() {
        todoService.removeList(1L);
        assertNull(iListRepository.findById(1L).orElse(null));
    }

    private void removeItemShouldBeNull() {
        todoService.removeItem(1L);
        assertNull(itemRepository.findById(1L).orElse(null));
    }

    private void findListSizeShouldBeOne() {
        List<IListGetDto> list = todoService.findAllListByUserId(1L);
        assertEquals(1, list.size());
    }

    private void findItemSizeShouldBe3() {
        List<ItemGetDto> itemsByIListId = todoService.findItemsByIListId(1L);
        assertEquals(3, itemsByIListId.size());
    }

    private void createOneMoreListSizeShouldBe2() {
        todoService.createIList(
                IListPostDto.builder()
                        .name(faker.funnyName().name())
                        .dueDate(LocalDateTime.now())
                        .build()
                , 1L);
        assertEquals(2, iListRepository.findAll().size());
    }

    // add user with id = 1, and one list, 3 items
    private void givenDB1ListAnd3Items() {
        IList list = new IList(faker.funnyName().name(), LocalDateTime.now());

        Item item1 = getItem(faker, 10);
        Item item2 = getItem(faker, 12);
        Item item3 = getItem(faker, 13);
        list.addItem(item1);
        list.addItem(item2);
        list.addItem(item3);
        User user = new User("name", "pass");
        user.addList(list);
        userRepository.save(user);
    }

    private Item getItem(Faker faker, int days) {
        return new Item(
                faker.harryPotter().quote(),
                LocalDateTime.now().plusDays(days));
    }


}
