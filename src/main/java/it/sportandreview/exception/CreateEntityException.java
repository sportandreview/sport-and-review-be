package it.sportandreview.exception;

import it.sportandreview.constants.ErrorConstants;
import lombok.extern.log4j.Log4j2;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class CreateEntityException extends AbstractThrowableProblem {

    public CreateEntityException() {
        log.debug("Some entity not created on server");
    }

    public CreateEntityException(String entity, String value) {
        super(ErrorConstants.INTERNAL_ERROR, "ERRORE", Status.INTERNAL_SERVER_ERROR, value, null, null, getAlertParameters(entity));
        log.error("{} not created({})", entity, value);
    }

    private static Map<String, Object> getAlertParameters(String entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("entity", entity);
        return parameters;
    }
}
