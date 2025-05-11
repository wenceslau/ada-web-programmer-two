package com.ada.pedidocompra.quarkus.infraestrutura.controladores.request;

public record ItemPedidoRequest(Long produtoId, Integer quantidade) {
}
