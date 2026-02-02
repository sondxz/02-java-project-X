package vn.hoangson.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    
    @Test
    public void name() {
        // arrange: Chuẩn bị

        // act: Hành động
       
        // assert: So sánh

    }

    @Test
    public void getAllUsers_shouldReturnAllUsers() {
        // arrange: Chuẩn bị
        List<User> outputUsers = new ArrayList<>();
        outputUsers.add(new User(1L, "eric", "hoidanit@gmail.com"));
        outputUsers.add(new User(2L, "test", "test@gmail.com"));

        when(this.userRepository.findAll()).thenReturn(outputUsers);
        // act: Hành động
        List<User> result = this.userService.getAllUsers();

        // assert: So sánh
        assertEquals(2, result.size());
        assertEquals("hoidanit@gmail.com", result.get(0).getEmail());
    }

    @Test
    public void getUserById_shouldReturnOptionalUser() {
        // arrange: Chuẩn bị
        Long inputId = 1L;
        User inputUser = new User(1L, "eric", "hoidanit@gmail.com");
        Optional<User> userOptionalOutput = Optional.of(inputUser);

        when(this.userRepository.findById(inputId)).thenReturn(userOptionalOutput);

        // act: Hành động
        Optional<User> result = this.userService.getUserById(inputId);

        // assert: So sánh
        assertEquals(true, result.isPresent());
    }

    @Test
    public void deleteUser_shouldReturnVoid_whenUserExist() {
        // arrange: Chuẩn bị
        Long inputId = 1L;
        when(this.userRepository.existsById(inputId)).thenReturn(true);

        // act: Hành động
        this.userService.deleteUser(inputId);

        // assert: So sánh
        verify(this.userRepository).deleteById(inputId);
    }

    @Test
    public void deleteUser_shouldReturnException_whenUserNotExist() {
        // arrange: Chuẩn bị
        Long inputId = 1L;
        when(this.userRepository.existsById(inputId)).thenReturn(false);

        // act: Hành động
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            this.userService.deleteUser(inputId);
        });
        // assert: So sánh
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void updateUser_shouldReturnUser_whenValid() {
        // arrange: Chuẩn bị
        Long inputId = 1L;
        User inputUser = new User(null, "old name", "old@gmail.com");
        User outputUser = new User(null, "new name", "new@gmail.com");

        when(this.userRepository.findById(inputId)).thenReturn(Optional.of(inputUser));
        when(this.userRepository.save(any())).thenReturn(outputUser);

        // act: Hành động
        User result = this.userService.updateUser(inputId, outputUser);

        // assert: So sánh
        assertEquals("new name", result.getName());
    }
}
