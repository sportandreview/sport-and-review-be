package it.sportandreview.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponseDTO<T> {
    private int status;
    private String message;
    private T result;

    public ApiResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
