package vn.hoangson.todo;

import org.springframework.web.bind.annotation.RestController;

import vn.hoangson.todo.domain.Todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body("Hello Spring Boot!");
    }
    
    @GetMapping("/hoidanit")
    public ResponseEntity<Todo> hoidanit() {
        Todo todo = new Todo("Hỏi dân IT", false);
        return ResponseEntity.ok().body(todo);
    }
    
}
