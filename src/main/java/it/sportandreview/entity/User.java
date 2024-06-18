package it.sportandreview.entity;

import it.sportandreview.enums.GenderType;
import it.sportandreview.enums.PhysicalStructure;
import it.sportandreview.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birth_date", nullable = false)
    private ZonedDateTime birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_type", nullable = false)
    private GenderType genderType;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType roleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "physical_structure")
    private PhysicalStructure physicalStructure;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_sport",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    @JsonManagedReference
    private Set<Sport> sports = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<UserEvaluation> evaluations = new HashSet<>();

    @Column(name = "mobile_phone_check")
    private boolean mobilePhoneCheck;

    @Column(name = "email_check")
    private boolean emailCheck;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
}
