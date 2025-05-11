package com.ada.estacionamento.infraestrutura.repositorios;

import com.ada.estacionamento.infraestrutura.repositorios.entidades.EstacionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<EstacionamentoEntity, Long> {

    Optional<EstacionamentoEntity> findByDataReferencia(LocalDate dataReferencia);
}
