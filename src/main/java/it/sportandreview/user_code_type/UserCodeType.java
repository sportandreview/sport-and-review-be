package it.sportandreview.user_code_type;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_user_code_type")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserCodeType extends IndexedEntity {

    @Column(name = "description")
    private String description;

}
