package com.ada.estacionamento.quarkus.infraestrutura.repositorios;

import com.ada.estacionamento.quarkus.infraestrutura.repositorios.entidades.EstacionamentoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class EstacionamentoRepository implements PanacheRepositoryBase<EstacionamentoEntity, Long> {

    public Optional<EstacionamentoEntity> findByDataReferencia(LocalDate dataReferencia) {
        return find("dataReferencia", dataReferencia).firstResultOptional();
    }
}
