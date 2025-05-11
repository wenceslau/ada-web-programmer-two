package com.ada.estacionamento.quarkus.infraestrutura.controladores.records;

public record StatusResponse(
        long capacidade,
        long ocupacao,
        long livres
) {
}
