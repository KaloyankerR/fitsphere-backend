package s3.ind.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidWorkoutException extends ResponseStatusException {
    public InvalidWorkoutException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
