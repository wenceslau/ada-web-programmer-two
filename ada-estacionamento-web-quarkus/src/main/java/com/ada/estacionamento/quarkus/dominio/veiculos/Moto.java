package com.ada.estacionamento.quarkus.dominio.veiculos;

import com.ada.estacionamento.quarkus.dominio.Veiculo;

public class Moto extends Veiculo {

    public Moto(String placa, Double valorHora, Double fracaoHora) {
        super(placa, valorHora, fracaoHora);
    }

    @Override
    public VeiculoTipo getTipo() {
        return VeiculoTipo.CARRO;
    }

}
