package vn.hoangson.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoangson.todo.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}