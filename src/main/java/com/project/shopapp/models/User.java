package com.project.shopapp.models;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.models.base.BaseModel;
import com.project.shopapp.dtos.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(length = 200)
    private String address;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "facebook_account_id")
    private Integer facebookAccountId;

    @Column(name = "google_account_id")
    private Integer googleAccountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(UserRequestDto userRequestDto) {
        this.fullName = userRequestDto.getFullName();
        this.phoneNumber = userRequestDto.getPhoneNumber();
        this.address = userRequestDto.getAddress();
        this.dateOfBirth = userRequestDto.getDateOfBirth();
        this.facebookAccountId = userRequestDto.getFacebookAccountId();
        this.googleAccountId = userRequestDto.getGoogleAccountId();
    }

    public boolean isActive() {
        return Objects.equals(this.getRecordStatus(), RECORD_STATUS.ACTIVE);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getRole().getName()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
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
