package fontys.ind.configuration.security.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccessTokenException extends ResponseStatusException {
    public InvalidAccessTokenException() {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED!");
    }
}

