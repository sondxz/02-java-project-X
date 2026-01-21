package vn.hoangson.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoangson.todo.domain.Todo;
import vn.hoangson.todo.service.TodoService;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/create-todo")
    public String createTodo() {
        Todo myTodo = new Todo("Sample Todo", false);
        this.todoService.handleCreateTodo(myTodo);
        return "Create todo endpoint";
    }
}
