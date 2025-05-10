package br.com.ada.springsecurityjwt.repositories;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String senha;
    private String perfil;

    public Usuario() {
    }

    public Usuario(String usuario, String senha, String perfil) {
        this.usuario = usuario;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public Usuario setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public Usuario setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getPerfil() {
        return perfil;
    }

    public Usuario setPerfil(String perfil) {
        this.perfil = perfil;
        return this;
    }
}
