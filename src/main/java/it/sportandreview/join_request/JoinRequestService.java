package it.sportandreview.join_request;


import java.util.List;

public interface JoinRequestService {
        Long create(JoinRequestDTO joinRequestDTO);

        List<JoinRequestDTO> findAll();

        JoinRequestDTO findById(Long joinRequestId);


}
