package it.sportandreview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.sportandreview.enums.SportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sport")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers;

    @Enumerated(EnumType.STRING)
    @Column(name = "sport_type", nullable = false, unique = true)
    private SportType sportType;

    @Transient
    public String getSportDescription() {
        return sportType.getDescription();
    }

    @ManyToMany(mappedBy = "sports")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserEvaluation> evaluations = new HashSet<>();

    public Sport(Integer maxPlayers, SportType sportType) {
        this.maxPlayers = maxPlayers;
        this.sportType = sportType;
    }
}
