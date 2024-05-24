package it.sportandreview.exception.specific;

import it.sportandreview.exception.ApiException;
import it.sportandreview.exception.ErrorCode;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
