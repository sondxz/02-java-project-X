package vn.hoangson.todo;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.hoangson.todo.domain.Todo;
import vn.hoangson.todo.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public ResponseEntity<String> index() throws Exception {
        // json => object(frontend send data to backend)
        String json = """
                {
                "name": "eric",
                "email": "hoidanit@gmail.com"
                }""";
        User user = objectMapper.readValue(json, User.class);

        User eric = new User(null, "eric", "hoidanit@gmail.com");
        String jsonOutput = objectMapper.writeValueAsString(eric);

        return ResponseEntity.ok().body(jsonOutput);
    }

    @GetMapping("/hoidanit")
    public ResponseEntity<Todo> hoidanit() {
        Todo todo = new Todo("Hỏi dân IT", false);
        return ResponseEntity.ok().body(todo);
    }

}
