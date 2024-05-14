package fontys.ind.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidDataException extends ResponseStatusException {
    public InvalidDataException(String errorMessage){
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
