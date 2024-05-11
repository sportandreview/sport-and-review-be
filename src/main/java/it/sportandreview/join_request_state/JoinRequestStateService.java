package it.sportandreview.join_request_state;


import java.util.List;

public interface JoinRequestStateService {

    Long create(JoinRequestStateDTO joinRequestStateDTO);

    List<JoinRequestStateDTO> findAll();

    JoinRequestStateDTO findById(Long joinRequestStateId);
}
