package it.sportandreview.service_type;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "app_services_type")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ServicesType extends IndexedEntity {

    private String description;

}
