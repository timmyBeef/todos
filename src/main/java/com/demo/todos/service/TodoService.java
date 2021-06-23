package com.demo.todos.service;

import com.demo.todos.dto.*;
import com.demo.todos.entity.IList;
import com.demo.todos.entity.Item;
import com.demo.todos.entity.User;
import com.demo.todos.exception.DataNotExistException;
import com.demo.todos.mapper.IListMapper;
import com.demo.todos.mapper.ItemMapper;
import com.demo.todos.repository.IListRepository;
import com.demo.todos.repository.ItemRepository;
import com.demo.todos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;

    private final IListRepository iListRepository;

    private final ItemRepository itemRepository;

    private final IListMapper iListMapper;

    private final ItemMapper itemMapper;

    public List<IListGetDto> findAllListByUserId(Long userId) {

        return this.getUser(userId).getLists().stream()
                .map(iList -> iListMapper.fromEntity(iList))
                .collect(Collectors.toList());
    }

    public List<ItemGetDto> findItemsByIListId(Long listId) {
        return iListRepository.findItemsByListId(listId)
                .orElseThrow(() -> new DataNotExistException("cant find list"))
                .getItems().stream()
                .map(item -> itemMapper.fromEntity(item))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public IListGetDto createIList(IListPostDto dto, Long userId) {
        User user = getUser(userId);
        IList iList = iListMapper.toEntity(dto);
        iList.setUser(user);
        return iListMapper.fromEntity(iListRepository.save(iList));
    }

    @Transactional(rollbackFor = Exception.class)
    public IListGetDto saveIList(IListPostDto dto, Long listId) {
        IList iList = getiList(listId);
        iListMapper.updateIList(dto, iList);
        return iListMapper.fromEntity(iListRepository.save(iList));
    }

    @Transactional(rollbackFor = Exception.class)
    public ItemGetDto createItem(ItemPostDto dto, Long listId) {
        IList iList = getiList(listId);
        Item item = itemMapper.toEntity(dto);
        item.setIlist(iList);
        return itemMapper.fromEntity(itemRepository.save(item));
    }

    @Transactional(rollbackFor = Exception.class)
    public ItemGetDto saveItem(ItemPutDto dto, Long itemId) {
        Item item = getItem(itemId);
        itemMapper.updateItem(dto, item);
        return itemMapper.fromEntity(itemRepository.save(item));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveMovedItemsOrder(List<ItemPostDto> items) {
        for (int i = 0; i < items.size(); i++) {
            ItemPostDto dto = items.get(i);
            Item it = getItem(dto.getId());
            it.setOrderNum(i);
            itemRepository.save(it);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeList(Long listId) {
        iListRepository.delete(this.getiList(listId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeItem(Long itemId) {
        itemRepository.delete(this.getItem(itemId));
    }

    private User getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotExistException("cant find user"));
        return user;
    }

    private IList getiList(Long listId) {
        return iListRepository.findById(listId)
                .orElseThrow(() -> new DataNotExistException("cant find list"));
    }

    private Item getItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new DataNotExistException("cant find item"));
    }
}
