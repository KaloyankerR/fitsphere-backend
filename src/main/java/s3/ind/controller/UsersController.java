package s3.ind.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.domain.CreateUserRequest;
import s3.ind.domain.CreateUserResponse;
import s3.ind.domain.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private List<User> users;

    @PostConstruct
    private void initUsers() {
        User user1 = User.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .country("USA")
                .build();

        User user2 = User.builder()
                .id(2)
                .firstName("Rali")
                .lastName("Stoyanova")
                .country("Burgas")
                .build();

        User user3 = User.builder()
                .id(3)
                .firstName("Daniel")
                .lastName("Langov")
                .country("Moreto")
                .build();

        User user4 = User.builder()
                .id(4)
                .firstName("Peter")
                .lastName("Parker")
                .country("New York")
                .build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> response = new ArrayList<>(users);
        return ResponseEntity.ok(response);
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
                .country(request.getCountry())
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
