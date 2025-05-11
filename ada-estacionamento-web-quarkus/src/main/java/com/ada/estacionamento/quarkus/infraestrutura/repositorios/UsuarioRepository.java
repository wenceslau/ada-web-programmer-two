package com.ada.estacionamento.quarkus.infraestrutura.repositorios;

import com.ada.estacionamento.quarkus.infraestrutura.repositorios.entidades.UsuarioEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<UsuarioEntity, Long> {

    public Optional<UsuarioEntity> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

}
