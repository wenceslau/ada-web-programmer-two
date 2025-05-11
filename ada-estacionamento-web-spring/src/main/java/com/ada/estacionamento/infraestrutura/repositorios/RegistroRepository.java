package com.ada.estacionamento.infraestrutura.repositorios;

import com.ada.estacionamento.infraestrutura.repositorios.entidades.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
}
