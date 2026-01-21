package vn.hoangson.todo.service;

import org.springframework.stereotype.Service;

import vn.hoangson.todo.domain.Todo;
import vn.hoangson.todo.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void handleCreateTodo(Todo todo) {
        System.out.println("Handling create todo: " + todo.toString());
        this.todoRepository.save(todo);
    }
}
