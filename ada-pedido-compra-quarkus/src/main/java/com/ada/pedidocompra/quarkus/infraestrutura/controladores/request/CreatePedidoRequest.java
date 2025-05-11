package com.ada.pedidocompra.quarkus.infraestrutura.controladores.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreatePedidoRequest(@NotNull List<ItemPedidoRequest> itens) {
}
