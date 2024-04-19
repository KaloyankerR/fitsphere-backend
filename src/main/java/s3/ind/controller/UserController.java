package s3.ind.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.business.UserService;
import s3.ind.domain.request.CreateUserRequest;
import s3.ind.domain.request.UpdateUserRequest;
import s3.ind.domain.response.CreateUserResponse;
import s3.ind.domain.User;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok((userService.getAllUsers()));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final Integer id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequest request) {
        request.setUserId(id);
        userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser((long) id);
        return ResponseEntity.noContent().build();
    }

}
