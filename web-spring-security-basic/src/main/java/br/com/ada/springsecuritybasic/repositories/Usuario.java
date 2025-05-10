package br.com.ada.springsecuritybasic.repositories;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    public Usuario() {
    }

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public Usuario setId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario setUsername(String username) {
        this.username = username;
        return this;
    }

    public Usuario setPassword(String password) {
        this.password = password;
        return this;
    }

    public Usuario setRole(String role) {
        this.role = role;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

}
