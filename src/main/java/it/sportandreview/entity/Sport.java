package it.sportandreview.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.sportandreview.enums.SportType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer maxPlayers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private SportType sportType;

    @Column(nullable = false)
    private int slotDurationMinutes;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Playground> playgrounds;

    @ManyToMany(mappedBy = "sportSet")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SportAssessment> sportAssessmentSet;

    @Transient
    public String getSportDescription() {
        return sportType.getDescription();
    }

    public Sport(Integer maxPlayers, SportType sportType, int slotDurationMinutes) {
        this.maxPlayers = maxPlayers;
        this.sportType = sportType;
        this.slotDurationMinutes = slotDurationMinutes;
    }
}
