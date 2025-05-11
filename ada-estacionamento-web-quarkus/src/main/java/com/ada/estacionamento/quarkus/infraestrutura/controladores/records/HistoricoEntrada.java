package com.ada.estacionamento.quarkus.infraestrutura.controladores.records;

import java.time.LocalDateTime;

public record HistoricoEntrada(
        String placa,
        String tipoVeiculo,
        LocalDateTime horaEntrada) {
}
