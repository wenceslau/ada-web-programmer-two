package com.ada.estacionamento.infraestrutura.repositorios;

import com.ada.estacionamento.infraestrutura.repositorios.entidades.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsername(String username);

}
