package it.sportandreview.sport_equipment;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.brand.Brand;
import it.sportandreview.sport_equipment_type.SportEquipmentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "app_sport_equipment")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SportEquipment extends IndexedEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "dimension")
    private String dimension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @ToString.Exclude
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_equipment_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private SportEquipmentType sportEquipmentType;


}
