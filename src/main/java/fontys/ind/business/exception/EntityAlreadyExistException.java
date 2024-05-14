package fontys.ind.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyExistException extends ResponseStatusException {
    public EntityAlreadyExistException(String errorMessage) {
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
