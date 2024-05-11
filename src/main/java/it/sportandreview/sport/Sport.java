package it.sportandreview.sport;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.services.Services;
import it.sportandreview.sport_point.SportPoint;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "app_sport")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Sport extends IndexedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggested_service_id", referencedColumnName = "id")
    private Services service;

    @Column(name = "max_player")
    private Integer maxPlayer;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_sport_sport_point",
            inverseJoinColumns = @JoinColumn(name = "sport_point_id"),
            joinColumns = @JoinColumn(name = "sport_id"))
    private Set<SportPoint> sportPoints = new HashSet<>();
    
}
