package it.sportandreview.join_request_state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRequestStateRepository extends JpaRepository<JoinRequestState, Long> {

}
