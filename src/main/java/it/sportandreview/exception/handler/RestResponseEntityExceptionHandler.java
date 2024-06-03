package it.sportandreview.exception.handler;

import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.ValidationErrorResponseDTO;
import it.sportandreview.exception.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = "Invalid request: ";
        if (ex.getCause() instanceof com.fasterxml.jackson.databind.exc.MismatchedInputException) {
            message += "JSON parse error - please check the request body.";
        } else if (ex.getCause() instanceof java.io.EOFException) {
            message += "Request body is missing.";
        } else {
            message += ex.getMessage();
        }

        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_BAD_REQUEST)
                .message(message)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiResponseDTO> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_CONFLICT)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponseDTO> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponseDTO> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponseDTO> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .message(ex.getMessage())
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

        ApiResponseDTO<List<ValidationErrorResponseDTO>> apiResponseDTO = ApiResponseDTO.<List<ValidationErrorResponseDTO>>builder()
                .status(status.value())
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

    // Gestore di fallback per tutte le altre eccezioni
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleAllOtherExceptions(Exception ex, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .message("Internal server error: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
