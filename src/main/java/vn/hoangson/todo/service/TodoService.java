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

    public void handleCreateTodo(Todo todo) {
        System.out.println("Handling create todo: " + todo.toString());
        this.todoRepository.save(todo);
    }

    public void handleGetTodo() {
        // List<Todo> todos = this.todoRepository.findAll();
        // todos.forEach(todo -> {
        //     System.out.println(todo.toString());
        // });

        // Optional<Todo> todoOptional = this.todoRepository.findById(2L);
        // if(todoOptional.isPresent()) {
        //     System.out.println(todoOptional.get().toString());
        // } else {
        //     System.out.println("Todo not found");
        // }

        Optional<Todo> todoOptional = this.todoRepository.findByTitle("Sample Todo");
        if (todoOptional.isPresent()) {
            System.out.println(todoOptional.get().toString());
        } else {
            System.out.println("Todo not found");
        }
    }
}
