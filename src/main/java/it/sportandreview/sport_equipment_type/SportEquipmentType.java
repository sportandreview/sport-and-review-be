package it.sportandreview.sport_equipment_type;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.brand.Brand;
import it.sportandreview.services.Services;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_sport_equipment_type")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SportEquipmentType extends IndexedEntity {

    @Column(name = "description")
    private String description;
}
