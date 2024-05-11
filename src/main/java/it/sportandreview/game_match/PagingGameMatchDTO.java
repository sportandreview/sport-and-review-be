package it.sportandreview.game_match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PagingGameMatchDTO {
    Integer pageSize;
    List<GameMatchDTO> gameMatches = new ArrayList<>();

}
