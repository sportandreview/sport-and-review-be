package it.sportandreview.user;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.club.Club;
import it.sportandreview.enums.Role;
import it.sportandreview.field.Field;
import it.sportandreview.gender_type.GenderType;
import it.sportandreview.highlight.Highlight;
import it.sportandreview.sport.Sport;
import it.sportandreview.team.Team;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "app_user")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends IndexedEntity implements UserDetails {

    //Common attributes
    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "mobile_enabled")
    private Boolean mobileEnabled;

    @Column(name = "city")
    private String city;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cap")
    private String cap;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "mobile_phone_check")
    private boolean mobilePhoneCheck;

    @Column(name = "email_check")
    private boolean emailCheck;

    @Enumerated(EnumType.STRING)
    private Role role;

    //Player user attributes
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "physical_level")
    private Double physicalLevel;

    @Column(name = "technical_level")
    private Double technicalLevel;

    @Column(name = "tactical_level")
    private Double tacticalLevel;

    @Column(name = "behavior")
    private Double behavior;

    @Column(name = "technical_ability")
    private Double technicalAbility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private GenderType genderType;

    @Column(name = "birth_date")
    private ZonedDateTime birthDate;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_favorite_clubs_user",
            inverseJoinColumns = @JoinColumn(name = "club_id"),
            joinColumns = @JoinColumn(name = "player_user_id"))
    private Set<Club> favoriteClubs = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_favorite_fields_user",
            inverseJoinColumns = @JoinColumn(name = "field_id"),
            joinColumns = @JoinColumn(name = "player_user_id"))
    private Set<Field> favoriteFields = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_friends",
            inverseJoinColumns = @JoinColumn(name = "player_user_2_id"),
            joinColumns = @JoinColumn(name = "player_user_1_id"))
    private Set<User> friends = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_favorite_highlights_user",
            inverseJoinColumns = @JoinColumn(name = "highlight_id"),
            joinColumns = @JoinColumn(name = "player_user_id"))
    private Set<Highlight> favoriteHighlights = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_team_user",
            inverseJoinColumns = @JoinColumn(name = "player_user_id"),
            joinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams = new HashSet<>();

    //Admin user attributes
    @Column(name = "name_structure")
    private String nameStructure;

    @Column(name = "address_structure")
    private String addressStructure;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_sport_user",
            inverseJoinColumns = @JoinColumn(name = "sport_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<Sport> sports = new HashSet<>();

    @Column(name = "number_fields")
    private Integer numberFields;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean checkMobilePhone() {
        if (Objects.nonNull(mobilePhone)) {
            String regexPattern = "^\\+39\\d{7,}$";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(mobilePhone);
            return matcher.matches();
        }
        return false;
    }

}
