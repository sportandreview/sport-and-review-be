package it.sportandreview.exception.specific;

import it.sportandreview.exception.ApiException;
import it.sportandreview.exception.ErrorCode;

public class CreateEntityException extends ApiException {
    public CreateEntityException(String entityName) {
        super(ErrorCode.CREATE_ENTITY_ERROR);
    }
}
