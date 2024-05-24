package it.sportandreview.exception;

import it.sportandreview.dto.GenericResponseDTO;
import it.sportandreview.exception.specific.UserAlreadyExistsException;
import it.sportandreview.exception.specific.CreateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(GenericResponseDTO.<Map<String, String>>builder()
                .message("Errore di validazione")
                .data(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GenericResponseDTO<String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(GenericResponseDTO.<String>builder()
                .message(ex.getErrorCode().getMessage())
                .data(null)
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CreateEntityException.class)
    public ResponseEntity<GenericResponseDTO<String>> handleCreateEntityException(CreateEntityException ex) {
        return new ResponseEntity<>(GenericResponseDTO.<String>builder()
                .message(ex.getErrorCode().getMessage())
                .data(null)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<GenericResponseDTO<String>> handleApiException(ApiException ex) {
        return new ResponseEntity<>(GenericResponseDTO.<String>builder()
                .message(ex.getErrorCode().getMessage())
                .data(null)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDTO<String>> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(GenericResponseDTO.<String>builder()
                .message("Interal Server Error")
                .data(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
