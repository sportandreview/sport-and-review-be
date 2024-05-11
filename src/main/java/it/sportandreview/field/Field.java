package it.sportandreview.field;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.club.Club;
import it.sportandreview.ground_type.GroundType;
import it.sportandreview.services.Services;
import it.sportandreview.sport.Sport;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "app_field")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Field extends IndexedEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "field_image")
    private String fieldImage;

    @Column(name = "hour_range_start")
    private ZonedDateTime hourRangeStart;

    @Column(name = "hour_range_end")
    private ZonedDateTime hourRangeEnd;

    @Column(name = "price")
    private Double price;

    @Column(name = "covered")
    private Boolean covered;

    @Column(name = "size")
    private Double size;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ground_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private GroundType groundType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ToString.Exclude
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    @ToString.Exclude
    private Sport sport;

    @Column(name = "highlights")
    private Boolean highlights;

    @Column(name = "markerPoint")
    private Boolean markerPoint;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_field_services",
            inverseJoinColumns = @JoinColumn(name = "services_id"),
            joinColumns = @JoinColumn(name = "field_id"))
    private Set<Services> services = new HashSet<>();

}
