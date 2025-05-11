package com.ada.estacionamento.infraestrutura.controladores.records;

import java.time.LocalDateTime;

public record HistoricoEntrada(
        String placa,
        String tipoVeiculo,
        LocalDateTime horaEntrada) {
}
