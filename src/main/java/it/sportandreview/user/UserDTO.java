package it.sportandreview.user;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.enums.GenderType;
import it.sportandreview.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UserDTO extends BaseDTO implements UserDetails {

    private String mobilePhone;
    private Boolean mobileEnabled;
    private String city;
    @NotBlank(message = "Campo nickname obbligatorio!")
    private String nickname;
    @NotBlank(message = "Campo nome obbligatorio!")
    private String name;
    @NotBlank(message = "Campo cognome obbligatorio!")
    private String surname;
    @NotNull(message = "Campo genere obbligatorio!")
    private GenderType genderType;
    private ZonedDateTime birthDate;
    private String phone;
    @NotBlank(message = "Campo email obbligatorio!")
    @Email(message = "Formato mail non valido!")
    private String email;
    @NotBlank(message = "Campo password obbligatorio!")
    private String password;
    private String cap;
    private String address;
    private String profileImage;
    private boolean mobilePhoneCheck;
    private boolean emailCheck;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Role getRole() {
        return role;
    }
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
        return nickname;
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
