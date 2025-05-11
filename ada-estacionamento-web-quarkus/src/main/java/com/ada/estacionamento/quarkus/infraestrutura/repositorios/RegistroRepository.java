package com.ada.estacionamento.quarkus.infraestrutura.repositorios;

import com.ada.estacionamento.quarkus.infraestrutura.repositorios.entidades.RegistroEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroRepository implements PanacheRepositoryBase<RegistroEntity, Long> {
}
