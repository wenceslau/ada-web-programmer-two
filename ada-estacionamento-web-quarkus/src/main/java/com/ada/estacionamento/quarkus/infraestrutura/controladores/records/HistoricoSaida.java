package com.ada.estacionamento.quarkus.infraestrutura.controladores.records;

import java.time.LocalDateTime;

public record HistoricoSaida(
        String placa,
        String tipoVeiculo,
        LocalDateTime horaEntrada,
        LocalDateTime horaSaida,
        Long duracao,
        Double valorPagar) {
}
