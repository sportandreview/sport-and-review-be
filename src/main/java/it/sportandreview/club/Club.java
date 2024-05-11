package it.sportandreview.club;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.field.Field;
import it.sportandreview.opening_day.OpeningDay;
import it.sportandreview.owner.Owner;
import it.sportandreview.services.Services;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_club")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Club extends IndexedEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @Column(name = "club_logo")
    private String clubLogo;

    @Column(name = "booking_policy")
    private String bookingPolicy;

    @Column(name = "club_rating")
    private Double clubRating;

    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ToString.Exclude
    private Owner owner;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_club_services",
            inverseJoinColumns = @JoinColumn(name = "services_id"),
            joinColumns = @JoinColumn(name = "club_id"))
    private Set<Services> services = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_club_opening_day",
            inverseJoinColumns = @JoinColumn(name = "opening_day_id"),
            joinColumns = @JoinColumn(name = "club_id"))
    private Set<OpeningDay> openingDays = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Field> fields = new HashSet<>();

    @ManyToMany(mappedBy = "favoriteClubs")
    private Set<User> preferringPlayerUsers = new HashSet<>();

}
