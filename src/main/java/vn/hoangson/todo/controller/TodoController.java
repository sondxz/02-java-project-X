package vn.hoangson.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoangson.todo.domain.Todo;
import vn.hoangson.todo.service.TodoService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/create-todo")
    public String createTodo() {
        Todo myTodo = new Todo("Sample Todo", true);
        this.todoService.handleCreateTodo(myTodo);
        return "Create todo endpoint";
    }

    @GetMapping("/todos")
    public String getTodo() {
        this.todoService.handleGetTodo();
        return "get todo endpoint";
    }

    @GetMapping("/update-todo")
    public String postUpdateTodo() {
        this.todoService.handleUpdateTodo();
        return "update todo endpoint";
    }

    @GetMapping("/delete-todo")
    public String postDeleteTodo() {
        this.todoService.handleDeleteTodo();
        return " delete todo endpoint";
    }

}
