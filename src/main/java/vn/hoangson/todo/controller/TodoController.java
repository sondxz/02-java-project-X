package vn.hoangson.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoangson.todo.domain.Todo;
import vn.hoangson.todo.service.TodoService;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todoData = this.todoService.getTodoById(id);
        return ResponseEntity.ok().body(todoData);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodo() {
        List<Todo> listTodo = this.todoService.handleGetTodo();
        return ResponseEntity.ok().body(listTodo);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo newTodo = this.todoService.handleCreateTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<String> postUpdateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        this.todoService.handleUpdateTodo(id, todo);
        return ResponseEntity.ok().body(" update successfully");
    }

    @DeleteMapping("/todos")
    public String deleteTodo() {
        // this.todoService.handleDeleteTodo();
        return " delete todo endpoint";
    }

}
