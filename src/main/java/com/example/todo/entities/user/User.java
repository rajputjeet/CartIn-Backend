package com.example.todo.entities.user;

import com.example.todo.entities.roles.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // So roles are always loaded with user
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    // Implementing methods for Spring Security (UserDetails)
//    @Override
//    public String getUsername() {
//        return this.email;  // Return the email for authentication
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));  // Modify based on roles
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Can be customized based on your needs
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Can be customized based on your needs
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Can be customized based on your needs
    }

    @Override
    public boolean isEnabled() {
        return true; // Can be customized for account activation
    }
}
