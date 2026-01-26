package vn.hoangson.todo.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoangson.todo.domain.Todo;
import vn.hoangson.todo.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo getTodoById(Long id) {
        Optional<Todo> todoOptional = this.todoRepository.findById(id);
        return todoOptional.isPresent() ? todoOptional.get() : null;
    }

    public Todo handleCreateTodo(Todo todo) {
        return this.todoRepository.save(todo);
    }

    public List<Todo> handleGetTodo() {
        return this.todoRepository.findAll();
    }

    public void handleUpdateTodo(Long id, Todo inputTodo) {
        Optional<Todo> todoOptional = this.todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo currentTodo = todoOptional.get();
            currentTodo.setIsCompleted(inputTodo.getIsCompleted());
            currentTodo.setTitle(inputTodo.getTitle());

            this.todoRepository.save(currentTodo);
        }
    }

    public void handleDeleteTodo(long id) {
        this.todoRepository.deleteById(id);
    }
}
