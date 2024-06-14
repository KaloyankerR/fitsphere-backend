package fontys.ind.controller;

import fontys.ind.business.exception.InvalidUserException;
import fontys.ind.domain.response.ApiWrapperResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fontys.ind.business.UserService;
import fontys.ind.domain.request.user.CreateTrainerRequest;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.domain.request.user.UpdateUserRequest;
import fontys.ind.domain.response.user.CreateUserResponse;
import fontys.ind.domain.response.user.GetAllUsersResponse;
import fontys.ind.domain.response.user.GetAllTrainersResponse;
import fontys.ind.domain.response.user.GetUserResponse;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins={"http://localhost:5173", "https://superb-kari-fitsphere-554f9337.koyeb.app"})
public class UserController {
    private final UserService userService;


    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        return ResponseEntity.ok((userService.getAllUsers()));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        try {
            ApiWrapperResponse response = userService.getUsersByRole(role);
            return ResponseEntity.ok(response);
        } catch (InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends GetUserResponse> getUser(@PathVariable(value = "id") final Integer id) {
        final Optional<? extends GetUserResponse> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/trainer")
    @RolesAllowed("ADMIN")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateTrainerRequest request) {
        CreateUserResponse response = userService.createTrainer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping()
//    @RolesAllowed("ADMIN")
    public ResponseEntity<GetUserResponse> updateUser(@RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser((long) id);
        return ResponseEntity.noContent().build();
    }
}
