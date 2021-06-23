# Todo List RESTful Backend API
## Tech Stack:
* SpringBoot
* Spring Data JPA
* Lombok
* Mapstruct
* H2 in-memory DB
* PostgreSQL
* Docker
* Docker compose
* Flyway

### This is the SpringBoot based RESTful API. It supports the below functions:

* Application support H2 in-mem DB and Postgres DB, by default postgres DB is being used. If you want to use H2 in-mem DB, application-h2.yaml shall be used.
* DB data is pre-populated by Flyway

## Topics
1. [How to run this application](#How-to-run-this-application)
2. [Swagger API definition](#Swagger-API-definition)
3. [How to use API](#How-to-use-API)
4. [Time Complexity for each API](#Time-Complexity-for-each-API)

# How to run this application

There are mutiple ways to run application

### 1. start from IntelliJ IDEA
the default application.yaml will run profile h2, you can change it

![](https://i.imgur.com/QUippcl.png)


### 2. start with H2 in-mem DB
* Run the command to build the whole project: **gradle clean build**
* start the application with H2 in-mem DB: **java -jar ./build/libs/todos-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2**

### 3. start with Postgres DB
* Run the command to build the whole project: **gradle clean build**
* with docker-compose.yaml, so you can only docker-compose up todos-postgres
* start the application with Postgres DB: **java -jar ./build/libs/todos-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres**

### 4. start with docker & docker-compose
* Run the command to build the whole project: **gradle clean build**
* run **docker-compose up**, then application and postgres DB are both up and running.

# Swagger API definition
if you start the application successful, the Swagger API is here:
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config


![](https://i.imgur.com/9nBxLGJ.png)

# How to use API
In here, I'll use Microsoft todos' UI for example

below data is pre-populated:

* 2 users data:
  ![](https://i.imgur.com/pZokIOh.png)

* 1 list
  ![](https://i.imgur.com/fqJmvKl.png)

* 3 items
  ![](https://i.imgur.com/y6VFKMw.png)



```
insert into users (create_time, encoded_password, user_name, id)
values ('2021-06-22T13:57:34.920Z', '$2a$10$L5rUMjVqkojrPaO80SC.VOPxPRd7eoNrkWBrFo3lDy1lDocc8RMmK', 'tim', 1);
insert into users (create_time, encoded_password, user_name, id)
values ('2021-06-22T13:57:34.920Z', '$2a$10$L5rUMjVqkojrPaO80SC.VOPxPRd7eoNrkWBrFo3lDy1lDocc8RMmK', 'jean', 2);

insert into ilist (create_time, due_date, name, update_time, user_id, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'my tasks', '2021-06-22T13:57:34.920Z', 1, 1);

insert into item (create_time, deadline, description, ilist_id, order_num, update_time, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'item1 desc', 1, 0, '2021-06-22T13:57:34.920Z', 1);
insert into item (create_time, deadline, description, ilist_id, order_num, update_time, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'item2 desc', 1, 0, '2021-06-22T13:57:34.920Z', 2);
insert into item (create_time, deadline, description, ilist_id, order_num, update_time, id)
values ('2021-06-22T13:57:34.920Z', '2021-06-22T13:57:34.920Z', 'item3 desc', 1, 0, '2021-06-22T13:57:34.920Z', 3);


```
## find all lists by user's id

### explain with UI

![](https://i.imgur.com/xIxL6AP.png)

### test on swagger

use user's id to find lists first

![](https://i.imgur.com/SHjzqME.png)



---



## find all items by list's id


### explain with UI
when click particular list, it will bring items by list id
![](https://i.imgur.com/KTlKQq5.png)

### test on swagger
![](https://i.imgur.com/Zvm2OqJ.png)


---

## create list with user's id

### explain with UI
enter new list name, then press enter, will call this API to create a new list
![](https://i.imgur.com/nfYX9iO.png)

### test on swagger
![](https://i.imgur.com/Kzz7yYE.png)

![](https://i.imgur.com/LXsn97p.png)



---

## save list with user's id
like create list's UI
### test on swagger
![](https://i.imgur.com/viyETm4.png)

![](https://i.imgur.com/EDN00xO.png)



---

## create item with list's id

### explain with UI
![](https://i.imgur.com/ZAosRlZ.png)

### test on swagger
![](https://i.imgur.com/K7GgAfY.png)

![](https://i.imgur.com/ePuSWKZ.png)

---

## save item with item's id

### test on swagger
![](https://i.imgur.com/ar2nFZE.png)

![](https://i.imgur.com/ywD8kux.png)


---

## after items moved, save all items' new order with list's id

### explain with UI
in items, we can move item, the new order should be stored
![](https://i.imgur.com/mPx360q.png)

### test on swagger
in here, frontend directly send the new data array for backend to save

![](https://i.imgur.com/Bt4jJQm.png)

after save, you can query items again to check it out.

![](https://i.imgur.com/EEqQTDJ.png)

the order is saved
![](https://i.imgur.com/YVRsgqv.png)


---

## remove list with list's id

![](https://i.imgur.com/QDQiwqQ.png)


---

## remove item with item's id
![](https://i.imgur.com/bFtNd1n.png)


---

# Time Complexity for each API

I think time complexity in this project is about the POSTGRES DB operations' time complexity and my program's time complexity

in POSTGRES DB, gerenarally with the binary block size.
* If no index, search is O(n).
* If index, search is O(log n).

You can also set which datastructure you want to use in which index. For instance, B-tree as the method of the partial index here and about the complexities of different operations here for the binary operations:

![](https://i.imgur.com/AUytDpY.png)


## find all items by list's id
find user and related list are all used pk and fk, so the time complexity is O(logn).

but in programming side, we need to transform all data to another dto, so finally the time complexity is still O(n).


```java=
public List<IListGetDto> findAllListByUserId(Long userId) {

    return this.getUser(userId).getLists().stream()
            .map(iList -> iListMapper.fromEntity(iList))
            .collect(Collectors.toList());
}

private User getUser(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new DataNotExistException("cant find user"));
    return user;
}
```

and other APIs have the same situation, so I think all the other APIs are all O(n).

The only exception is for remove, the time complexity are all O(logn).


```java=
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
```


---


## after items moved, save all items' new order with list's id
![](https://i.imgur.com/qKg1wfp.png)

this one has a for loop to iterate all items and update the DB

so the time complexity is O(m*logn), m is item size

```java=
    @Transactional(rollbackFor = Exception.class)
    public void saveMovedItemsOrder(List<ItemPostDto> items) {
        for (int i = 0; i < items.size(); i++) {
            ItemPostDto dto = items.get(i);
            Item it = getItem(dto.getId());
            it.setOrderNum(i);
            itemRepository.save(it);
        }
    }
```

## That's all! hope you'll like it!
