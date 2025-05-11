package com.ada.estacionamento.dominio.veiculos;

import com.ada.estacionamento.dominio.Veiculo;

public class Moto extends Veiculo {

    public Moto(String placa, Double valorHora, Double fracaoHora) {
        super(placa, valorHora, fracaoHora);
    }

    @Override
    public VeiculoTipo getTipo() {
        return VeiculoTipo.CARRO;
    }

}
