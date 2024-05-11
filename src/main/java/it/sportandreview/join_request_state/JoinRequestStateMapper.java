package it.sportandreview.join_request_state;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={JoinRequestState.class})
public interface JoinRequestStateMapper {

    JoinRequestStateDTO toDto(JoinRequestState joinRequestState);
    JoinRequestState toEntity(JoinRequestStateDTO joinRequestStateDto);

}
