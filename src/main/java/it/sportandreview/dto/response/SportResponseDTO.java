package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SportResponseDTO {
    private Long id;
    private Integer maxPlayers;
    private String description;
}
