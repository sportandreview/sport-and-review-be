package it.sportandreview.exception;

import it.sportandreview.constants.ErrorConstants;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Getter
public class NotFoundException extends AbstractThrowableProblem {

    private final String entity;
    private final String value;

    public NotFoundException() {
        super(ErrorConstants.NOT_FOUND, "not found", Status.NOT_FOUND, "something identifier missed on server", null, null, getAlertParameters("Entity undefined"));
        this.entity = null;
        this.value = null;
        log.error("Not found({})", "something identifier missed on server");
    }

    public NotFoundException(String entity, String value) {
        super(ErrorConstants.NOT_FOUND, "not found", Status.NOT_FOUND, value, null, null, getAlertParameters(entity));
        this.entity = entity;
        this.value = value;
        log.error("Not found entity - {} ({})", entity, value);
    }

    private static Map<String, Object> getAlertParameters(String entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("entity", entity);
        return parameters;
    }
}
