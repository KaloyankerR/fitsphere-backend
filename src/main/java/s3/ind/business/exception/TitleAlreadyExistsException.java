package s3.ind.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TitleAlreadyExistsException extends ResponseStatusException {
    public TitleAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "TITLE_ALREADY_EXISTS");
    }
}
