package it.sportandreview.exception.handler;

import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.ValidationErrorResponseDTO;
import it.sportandreview.exception.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({DuplicateEntityException.class, EntityNotFoundException.class, ValidationException.class})
    public ResponseEntity<Object> handleBusinessExceptions(Exception ex, WebRequest request) {
        HttpStatus status = determineHttpStatus(ex);
        String message = getLocalizedMessage(ex);
        logException("handleBusinessExceptions", ex, message);
        return buildErrorResponse(status, message);
    }

    @ExceptionHandler({BadCredentialsException.class, UnauthorizedException.class})
    public ResponseEntity<Object> handleAuthenticationExceptions(Exception ex, WebRequest request) {
        String message = getLocalizedMessage(ex);
        logException("handleAuthenticationExceptions", ex, message);
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        String message = getLocalizedMessage("access.denied");
        logException("handleAccessDeniedException", ex, message);
        return buildErrorResponse(HttpStatus.FORBIDDEN, message);
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<Object> handleTokenNotValidException(TokenNotValidException ex, WebRequest request) {
        String message = getLocalizedMessage("token.not.valid");
        logException("handleTokenNotValidException", ex, message);
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, message);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<ValidationErrorResponseDTO> validationErrors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::mapToErrorMessageDto)
                .collect(Collectors.toList());

        String message = getLocalizedMessage("validation.error");
        logException("handleMethodArgumentNotValid", ex, message);

        ApiResponseDTO<List<ValidationErrorResponseDTO>> apiResponseDTO = ApiResponseDTO.<List<ValidationErrorResponseDTO>>builder()
                .status(HttpServletResponse.SC_NOT_ACCEPTABLE)
                .message(message)
                .result(validationErrors)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponseDTO);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        String message = getLocalizedMessage("request.invalid");
        logException("handleHttpMessageNotReadable", ex, message);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllOtherExceptions(Exception ex, WebRequest request) {
        String message = getLocalizedMessage("internal.server.error");
        logException("handleAllOtherExceptions", ex, message);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof DuplicateEntityException) return HttpStatus.CONFLICT;
        if (ex instanceof EntityNotFoundException) return HttpStatus.NOT_FOUND;
        return HttpStatus.BAD_REQUEST;
    }

    private String getLocalizedMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    private String getLocalizedMessage(Exception ex) {
        return ex.getMessage();
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
        ApiResponseDTO<Object> error = ApiResponseDTO.builder()
                .status(status.value())
                .message(message)
                .build();
        return new ResponseEntity<>(error, status);
    }

    private void logException(String methodName, Exception ex, String message) {
        log.error("{}: {}. Exception: {}", methodName, message, ex.getMessage(), ex);
    }

    private ValidationErrorResponseDTO mapToErrorMessageDto(ObjectError error) {
        String fieldError = "";
        String rejectedValue = "";
        if (error instanceof FieldError) {
            fieldError = ((FieldError) error).getField();
            rejectedValue = String.valueOf(((FieldError) error).getRejectedValue());
        }
        return new ValidationErrorResponseDTO(error.getObjectName(), fieldError, error.getDefaultMessage(), rejectedValue);
    }
}