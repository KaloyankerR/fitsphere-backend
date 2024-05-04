package s3.ind.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.business.UserService;
import s3.ind.domain.request.user.CreateTrainerRequest;
import s3.ind.domain.request.user.CreateUserRequest;
import s3.ind.domain.request.user.UpdateUserRequest;
import s3.ind.domain.response.user.CreateUserResponse;
import s3.ind.domain.response.user.GetAllUsersResponse;
import s3.ind.domain.response.user.GetAllTrainersResponse;
import s3.ind.domain.response.user.GetUserResponse;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;
    
    @GetMapping("/trainers")
    public ResponseEntity<GetAllTrainersResponse> getAllTrainers() {
        return ResponseEntity.ok(userService.getAllTrainers());
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {

        return ResponseEntity.ok((userService.getAllUsers()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable(value = "id") final Integer id) {
        // TODO: add to be optional
        final Optional<GetUserResponse> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/trainer")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateTrainerRequest request) {
        CreateUserResponse response = userService.createTrainer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<GetUserResponse> updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequest request) {
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
