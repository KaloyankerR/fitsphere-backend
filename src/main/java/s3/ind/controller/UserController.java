package s3.ind.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.bussiness.UserService;
import s3.ind.domain.request.CreateUserRequest;
import s3.ind.domain.response.CreateUserResponse;
import s3.ind.domain.User;
import s3.ind.domain.response.UserResponse;

import java.util.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;
    private List<User> users;

    @PostConstruct
    private void initUsers() {
        User user1 = User.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .build();

        User user2 = User.builder()
                .id(2)
                .firstName("Rali")
                .lastName("Stoyanova")
                .build();

        User user3 = User.builder()
                .id(3)
                .firstName("Daniel")
                .lastName("Langov")
                .build();

        User user4 = User.builder()
                .id(4)
                .firstName("Peter")
                .lastName("Parker")
                .build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, UserResponse>> getAllUsers() {
        // List<User> response = new ArrayList<>(users);
        // return ResponseEntity.ok(this.userService.get);
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final Optional<User> userOptional = users.stream().filter(user -> user.getId() == id).findFirst();
        return userOptional.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        int newId = users.size() + 1;

        User newUser = User.builder()
                .id(newId)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        users.add(newUser);

        CreateUserResponse response = CreateUserResponse.builder()
                .id(newId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.noContent().build();
    }

}
