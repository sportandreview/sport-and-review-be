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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = messageSource.getMessage("request.invalid", null, LocaleContextHolder.getLocale());
        log.error("handleHttpMessageNotReadable: {}", message, ex);
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_BAD_REQUEST)
                .message(message)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponseDTO> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        String message = messageSource.getMessage("user.already.exists", null, LocaleContextHolder.getLocale());
        log.error("handleUserAlreadyExistException: {}", message, ex);
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_CONFLICT)
                .message(message)
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        String message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
        log.error("handleUserNotFoundException: {}", message, ex);
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_NOT_FOUND)
                .message(message)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponseDTO> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        String message = messageSource.getMessage("user.unauthorized", null, LocaleContextHolder.getLocale());
        log.error("handleUnauthorizedException: {}", message, ex);
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .message(message)
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDTO> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        String message = messageSource.getMessage("user.authenticate.failure", null, LocaleContextHolder.getLocale());
        log.error("handleBadCredentialsException: {}", message, ex);
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .message(message)
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ValidationErrorResponseDTO> validationErrorDetails = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::mapToErrorMessageDto)
                .collect(Collectors.toList());

        String message = messageSource.getMessage("validation.error", null, LocaleContextHolder.getLocale());
        log.error("handleMethodArgumentNotValid: {}", message, ex);
        ApiResponseDTO<List<ValidationErrorResponseDTO>> apiResponseDTO = ApiResponseDTO.<List<ValidationErrorResponseDTO>>builder()
                .status(status.value())
                .message(message)
                .result(validationErrorDetails)
                .build();
        return new ResponseEntity<>(apiResponseDTO, status);
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleAllOtherExceptions(Exception ex, WebRequest request) {
        log.error("Unhandled exception: ", ex);
        String message = messageSource.getMessage("internal.server.error", null, LocaleContextHolder.getLocale());
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .message(message + ": " + ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
