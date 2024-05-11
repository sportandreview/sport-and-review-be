package it.sportandreview.team;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "app_team")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Team extends IndexedEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_team_user",
            inverseJoinColumns = @JoinColumn(name = "player_user_id"),
            joinColumns = @JoinColumn(name = "team_id"))
    private Set<User> players = new HashSet<>();

}
