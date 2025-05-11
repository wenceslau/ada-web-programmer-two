package com.ada.estacionamento.quarkus.dominio.veiculos;

import com.ada.estacionamento.quarkus.dominio.Veiculo;

public class Carro extends Veiculo {

    public Carro(String placa, Double valorHora, Double fracaoHora) {
        super(placa, valorHora, fracaoHora);
    }

    @Override
    public VeiculoTipo getTipo() {
        return VeiculoTipo.CARRO;
    }

}
