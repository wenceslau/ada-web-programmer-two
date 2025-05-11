package com.ada.estacionamento.infraestrutura.controladores.records;

import com.ada.estacionamento.dominio.Registro;

import java.time.LocalDateTime;

public record RegistroResponse(
        String placa,
        String tipoVeiculo,
        LocalDateTime entrada,
        Double valorHora,
        Double valorHoraAdicional,
        LocalDateTime saida,
        Long duracao,
        Double valorPagar
) {

    public static RegistroResponse construir(Registro registro){
        Long duracao = null;
        if (registro.getHoraSaida() != null) {
            duracao = registro.getDuracao().toMinutes();
        }
        return new RegistroResponse(
                registro.getVeiculo().getPlaca(),
                registro.getVeiculo().getTipo().name(),
                registro.getHoraEntrada(),
                registro.getVeiculo().getValorHora(),
                registro.getVeiculo().getFracaoHora(),
                registro.getHoraSaida(),
                duracao,
                registro.getValorPago()
        );
    }
}
