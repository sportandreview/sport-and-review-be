package it.sportandreview.invitation_state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationStateRepository extends JpaRepository<InvitationState, Long> {

}
