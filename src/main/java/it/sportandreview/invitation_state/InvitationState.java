package it.sportandreview.invitation_state;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_invitation_state")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InvitationState extends IndexedEntity {

    @Column(name = "description")
    private String description;

}
