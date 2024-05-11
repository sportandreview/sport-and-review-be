package it.sportandreview.highlight;

import it.sportandreview.game_match.GameMatchMapper;
import it.sportandreview.player_user.PlayerUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={Highlight.class, GameMatchMapper.class, PlayerUserMapper.class})
public interface HighlightMapper {

    HighlightDTO toDto(Highlight highlight);
    Highlight toEntity(HighlightDTO highlightDto);

}
