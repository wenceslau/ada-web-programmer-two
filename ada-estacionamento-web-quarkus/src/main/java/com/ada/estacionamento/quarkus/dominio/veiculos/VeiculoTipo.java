package com.ada.estacionamento.quarkus.dominio.veiculos;


import com.ada.estacionamento.quarkus.dominio.Veiculo;

import java.util.Arrays;

public enum VeiculoTipo {
    CARRO,
    MOTO;

    public Veiculo criarInstancia(String placa, Double valorHora, Double fracaoHora) {
        return switch (this) {
            case CARRO -> new Carro(placa, valorHora, fracaoHora);
            case MOTO -> new Moto(placa, valorHora, fracaoHora);
        };
    }

    public static VeiculoTipo converter(String vehicleType) {
        try {
            return VeiculoTipo.valueOf(vehicleType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo veiculo invalido. Deve ser: " + Arrays.toString(VeiculoTipo.values()));
        }
    }

}
