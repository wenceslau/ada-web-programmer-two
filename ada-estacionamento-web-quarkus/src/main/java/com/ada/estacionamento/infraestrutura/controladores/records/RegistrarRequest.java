package com.ada.estacionamento.infraestrutura.controladores.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrarRequest(
        @NotBlank(message = "A placa do veículo é obrigatória")
        @Size(min = 8, max = 8, message = "A placa do veículo deve ter 8 caracteres")
        String placa,
        @NotBlank(message = "O tipo do veículo é obrigatório")
        String tipoVeiculo) {
}
