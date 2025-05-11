package com.ada.estacionamento.infraestrutura.controladores.records;

public record StatusResponse(
        long capacidade,
        long ocupacao,
        long livres
) {
}
