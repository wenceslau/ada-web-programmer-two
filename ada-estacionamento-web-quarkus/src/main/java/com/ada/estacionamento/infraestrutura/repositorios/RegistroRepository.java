package com.ada.estacionamento.infraestrutura.repositorios;

import com.ada.estacionamento.infraestrutura.repositorios.entidades.RegistroEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroRepository implements PanacheRepositoryBase<RegistroEntity, Long> {
}
