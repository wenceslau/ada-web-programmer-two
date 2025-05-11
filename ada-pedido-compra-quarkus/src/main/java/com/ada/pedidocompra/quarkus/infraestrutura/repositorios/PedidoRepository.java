package com.ada.pedidocompra.quarkus.infraestrutura.repositorios;


import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<Pedido, Long> {


}
