package com.demo.todos;

import com.demo.todos.entity.Item;
import com.demo.todos.entity.IList;
import com.demo.todos.entity.User;
import com.demo.todos.repository.ItemRepository;
import com.demo.todos.repository.IListRepository;
import com.demo.todos.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootApplication
public class TodosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            IListRepository listRepository,
            ItemRepository itemRepository) {
        return args -> {
//            Faker faker = new Faker();
//
//            IList list = new IList(faker.funnyName().name(), LocalDateTime.now());
//
//            list.addItem(new Item(
//                    faker.harryPotter().quote(),
//                    LocalDateTime.now().plusDays(10)));
//            list.addItem(new Item(
//                    faker.harryPotter().quote(),
//                    LocalDateTime.now().plusDays(12)));
//            list.addItem(new Item(
//                    faker.harryPotter().quote(),
//                    LocalDateTime.now().plusDays(13)));
////
//            User user = new User("tim", "pass");
//            user.addList(list);
//            userRepository.save(user);
//            log.info("user:{}", user.toString());
//
//            User u = userRepository.findById(1L).orElseThrow(
//                    () -> new RuntimeException("cant find")
//            );
//            log.info("u:{}", user.toString());

        };
    }

    @Bean
    public Faker getFaker() {
        return new Faker();
    }
}
