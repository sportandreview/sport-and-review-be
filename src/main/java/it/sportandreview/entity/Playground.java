package it.sportandreview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.sportandreview.enums.SurfaceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "playground")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_facility_id", nullable = false)
    @JsonBackReference
    private SportFacility sportFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", nullable = false)
    @JsonBackReference
    private Sport sport;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "surface_type", nullable = false)
    private SurfaceType surfaceType;

    @Column(name = "rating")
    private Double rating;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<TimeSlot> timeSlots;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Booking> bookings;
}
