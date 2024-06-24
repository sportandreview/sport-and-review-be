package it.sportandreview.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.sportandreview.enums.SurfaceType;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "playground", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "sport_facility_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurfaceType surfaceType;

    @Column(nullable = false)
    private LocalTime openingTime;

    @Column(nullable = false)
    private LocalTime closingTime;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "playground_open_days", joinColumns = @JoinColumn(name = "playground_id"))
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> openDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_facility_id", nullable = false)
    private SportFacility sportFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TimeSlot> timeSlots;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Booking> bookings;
}
