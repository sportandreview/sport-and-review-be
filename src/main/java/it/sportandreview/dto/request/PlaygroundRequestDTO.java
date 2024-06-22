package it.sportandreview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaygroundRequestDTO {
    @NotBlank(message = "{validation.name.required}")
    private String name;

    private Double rating;

    @NotBlank(message = "{validation.surfaceType.required}")
    private String surfaceType;

    @NotNull(message = "{validation.sportId.required}")
    private Long sportId;
}
