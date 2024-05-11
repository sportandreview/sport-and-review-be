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
public class NotVerifiedCodeException extends AbstractThrowableProblem {

    private final String entity;
    private final String value;

    public NotVerifiedCodeException(String entity, String value) {
        super(ErrorConstants.INTERNAL_ERROR, "Errore", Status.INTERNAL_SERVER_ERROR, value, null, null, getAlertParameters(entity));
        this.entity = entity;
        this.value = value;
        log.error("error when verifying the OTP code- {} ({})", entity, value);
    }

    private static Map<String, Object> getAlertParameters(String entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("entity", entity);
        return parameters;
    }
}
