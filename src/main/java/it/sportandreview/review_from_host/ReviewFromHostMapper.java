package it.sportandreview.review_from_host;

import it.sportandreview.owner.OwnerMapper;
import it.sportandreview.player_user.PlayerUserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={ReviewFromHost.class, PlayerUserMapper.class, OwnerMapper.class})
public interface ReviewFromHostMapper {

    ReviewFromHostDTO toDto(ReviewFromHost reviewFromHost);
    ReviewFromHost toEntity(ReviewFromHostDTO reviewFromHostDto);

}
