package com.ada.pedidocompra.quarkus.infraestrutura.controladores.response;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Usuario;

public record ClienteCredential(String email,
                                String senha,
                                String tipoUsuario) {

    public static ClienteCredential from(Usuario usuario) {
        return new ClienteCredential(usuario.getEmail(), usuario.getSenha(), usuario.getTipoUsuario().name());
    }
}
