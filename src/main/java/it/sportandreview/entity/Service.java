package it.sportandreview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @ManyToMany(mappedBy = "services")
    @JsonBackReference
    private Set<SportFacility> sportFacilities = new HashSet<>();

    @ManyToMany(mappedBy = "additionalServices")
    @JsonBackReference
    private Set<Booking> bookings = new HashSet<>();
}
