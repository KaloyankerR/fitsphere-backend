package s3.ind.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s3.ind.bussiness.AuthenticationService;
import s3.ind.domain.request.UserLoginRequest;
import s3.ind.domain.response.UserLoginResponse;

@RestController
@RequestMapping("/tokens")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authService;

    @PostMapping
    public ResponseEntity<UserLoginResponse> Login(@RequestBody @Valid UserLoginRequest request){
        UserLoginResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
