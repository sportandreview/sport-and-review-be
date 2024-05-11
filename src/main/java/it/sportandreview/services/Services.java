package it.sportandreview.services;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.field_size.FieldSize;
import it.sportandreview.service_type.ServicesType;
import it.sportandreview.sport_equipment.SportEquipment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "app_services")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Services extends IndexedEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private ServicesType serviceType;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_sport_equipment_services",
            inverseJoinColumns = @JoinColumn(name = "sport_equipment_id"),
            joinColumns = @JoinColumn(name = "services_id"))
    private Set<SportEquipment> sportEquipmentSet = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_field_size_services",
            inverseJoinColumns = @JoinColumn(name = "field_size_id"),
            joinColumns = @JoinColumn(name = "services_id"))
    private Set<FieldSize> fieldSizeSet = new HashSet<>();

}
