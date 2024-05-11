package it.sportandreview.owner;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_owner")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Owner extends IndexedEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "partita_iva")
    private String partitaIva;
}
