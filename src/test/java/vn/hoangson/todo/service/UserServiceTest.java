package vn.hoangson.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;

import vn.hoangson.todo.domain.User;
import vn.hoangson.todo.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    //fake
    @Mock
    private UserRepository userRepository;

    //UserRepository (fake) -> UserService (real)
    @InjectMocks
    private UserService userService;

    @Test
    public void createUser_shouldReturnUser_WhenEmailValid() {
        //arrange: Chuẩn bị
        User inputUser = new User(null, "hoidanit", "hoidanit@example.com");
        User outputUser = new User(1L, "hoidanit", "hoidanit@example.com");

        when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(false);

        when(this.userRepository.save(any())).thenReturn(outputUser);

        //act: Hành động
        User result = this.userService.createUser(inputUser);

        //assert: So sánh
        assertEquals(1L, result.getId());
    }

    @Test
    public void createUser_shouldThrowException_WhenEmailInvalid() {
        //arrange: Chuẩn bị
        User inputUser = new User(null, "hoidanit", "hoidanit@gmail.com");

        when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

        //act: Hành động 
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.userService.createUser(inputUser);
        }); 
        // assert: So sánh
        assertEquals("Email already exists", exception.getMessage());
    }
}
