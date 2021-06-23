package com.demo.todos.repository;

import com.demo.todos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.lists a where u.id=:id")
    Optional<User> findUserAndListsById(@Param("id") Long id);
}
