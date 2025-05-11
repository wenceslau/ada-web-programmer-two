package com.ada.estacionamento.infraestrutura.controladores.records;

import java.util.List;

public record RelatorioResponse(
        List<HistoricoEntrada> historicoEntradas,
        List<HistoricoSaida> historicoSaidas) {
}
