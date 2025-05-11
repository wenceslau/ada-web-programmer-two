package com.ada.estacionamento.dominio;

import com.ada.estacionamento.dominio.veiculos.VeiculoTipo;

public abstract class Veiculo {

    private final String placa;
    private final Double valorHora;
    private final Double fracaoHora;

    public Veiculo(String placa, Double valorHora, Double fracaoHora) {
        this.placa = placa;
        this.valorHora = valorHora;
        this.fracaoHora = fracaoHora;
    }

    public String getPlaca() {
        return placa;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public Double getFracaoHora() {
        return fracaoHora;
    }

    public abstract VeiculoTipo getTipo();

}
