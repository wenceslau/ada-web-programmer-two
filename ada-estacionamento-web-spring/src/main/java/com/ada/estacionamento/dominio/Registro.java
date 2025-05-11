package com.ada.estacionamento.dominio;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {

    private Long id;
    private final Veiculo veiculo;
    private final LocalDateTime horaEntrada;

    private LocalDateTime horaSaida;
    private Duration duracao;
    private Double valorPago;

    public Registro(Veiculo veiculo, LocalDateTime horaEntrada) {
        this.veiculo = veiculo;
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {

        if (horaSaida == null){
            throw new IllegalArgumentException("Hora de saída é obrigatória");
        }
        if (horaSaida.isBefore(horaEntrada)){
            throw new IllegalArgumentException("Hora de saída deve ser após a hora de entrada");
        }

        this.horaSaida = horaSaida;
        this.duracao = Duration.between(horaEntrada, horaSaida);
    }

    public void setValorPago(Double valorPago) {
        if (valorPago == null) {
            throw new IllegalArgumentException("Valor pago é obrigatório");
        }
        this.valorPago = valorPago;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
