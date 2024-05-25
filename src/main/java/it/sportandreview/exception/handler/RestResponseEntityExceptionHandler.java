package it.sportandreview.exception.handler;

import it.sportandreview.dto.ApiResponseDTO;
import it.sportandreview.dto.ValidationErrorDTO;
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

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponseDTO> userAlreadyExistException (UserAlreadyExistException userAlreadyExistException, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_CONFLICT)
                .message(userAlreadyExistException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ValidationErrorDTO> validationErrorDetails = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> mapToErrorMessageDto(error))
                .collect(Collectors.toList());

        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(status.value())
                .result(validationErrorDetails)
                .build();
        return new ResponseEntity<>(apiResponseDTO,status);
    }


    private ValidationErrorDTO mapToErrorMessageDto(ObjectError error) {
        String fieldError = "";
        String rejectedValue = "";
        if(error instanceof org.springframework.validation.FieldError) {
            fieldError = ((FieldError) error).getField();
            rejectedValue = (String)((FieldError) error).getRejectedValue();
        }
        return new ValidationErrorDTO(error.getObjectName(),fieldError,error.getDefaultMessage(),rejectedValue);
    }
}
