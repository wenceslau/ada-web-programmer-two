package com.ada.pedidocompra.quarkus.infraestrutura.controladores.response;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Usuario;

public record ClienteResponse(Long id,
                              String nome,
                              String cpf,
                              String email) {

    public static ClienteResponse from(Usuario usuario) {
        return new ClienteResponse(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail());
    }
}
