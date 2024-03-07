package s3.ind.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private List<User> users = createMockUsers();

    private List<User> createMockUsers() {
        List<User> mockUsers = new ArrayList<>();

        User user1 = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .country("USA")
                .build();

        User user2 = User.builder()
                .id(2L)
                .firstName("Rali")
                .lastName("Stoyanova")
                .country("Burgas")
                .build();

        mockUsers.add(user1);
        mockUsers.add(user2);

        return mockUsers;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // List<User> response = new ArrayList<>(users);
        List<User> response = createMockUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final Optional<User> userOptional = users.stream().filter(user -> user.getId() == id).findFirst();

        return userOptional.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
    }



}
