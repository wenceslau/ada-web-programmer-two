package com.ada.estacionamento.quarkus.infraestrutura.controladores.records;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank(message = "O campo username é obrigatório")
        String username,
        @NotBlank(message = "O campo senha é obrigatório")
        String senha) {
}
