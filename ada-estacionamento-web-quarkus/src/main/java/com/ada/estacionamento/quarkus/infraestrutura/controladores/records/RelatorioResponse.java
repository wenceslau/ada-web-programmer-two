package com.ada.estacionamento.quarkus.infraestrutura.controladores.records;

import java.util.List;

public record RelatorioResponse(
        List<HistoricoEntrada> historicoEntradas,
        List<HistoricoSaida> historicoSaidas) {
}
