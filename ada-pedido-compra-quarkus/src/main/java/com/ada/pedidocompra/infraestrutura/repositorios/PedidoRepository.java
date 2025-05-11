package com.ada.pedidocompra.infraestrutura.repositorios;


import com.ada.pedidocompra.infraestrutura.repositorios.entidades.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<Pedido, Long> {


}
