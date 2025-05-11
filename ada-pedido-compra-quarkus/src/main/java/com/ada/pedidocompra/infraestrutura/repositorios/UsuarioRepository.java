package com.ada.pedidocompra.infraestrutura.repositorios;

import com.ada.pedidocompra.infraestrutura.repositorios.entidades.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;


@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email) {
        return find("email", email)
                .firstResultOptional();
    }

}
