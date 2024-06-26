package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {
    private int status;
    private String message;
    private T result;

    public ApiResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }
}