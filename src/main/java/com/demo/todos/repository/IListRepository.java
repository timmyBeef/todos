package com.demo.todos.repository;

import com.demo.todos.entity.IList;
import com.demo.todos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IListRepository extends JpaRepository<IList, Long> {
    @Query("select il from IList il join fetch il.items ite where il.id=:id order by ite.orderNum, ite.id asc ")
    Optional<IList> findItemsByListId(@Param("id") Long id);
}
