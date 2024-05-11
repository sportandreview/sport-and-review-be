package it.sportandreview.join_request_state;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_join_request_state")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class JoinRequestState extends IndexedEntity {

    @Column(name = "description")
    private String description;

}
