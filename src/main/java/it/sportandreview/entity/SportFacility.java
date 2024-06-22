package it.sportandreview.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "sport_facility")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "rating")
    private Double rating;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Playground> playgrounds;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Booking> bookings;

    @ManyToMany
    @JoinTable(
            name = "sport_facility_service",
            joinColumns = @JoinColumn(name = "sport_facility_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonManagedReference
    private Set<Service> services;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "facility_open_days", joinColumns = @JoinColumn(name = "facility_id"))
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> openDays;
}
