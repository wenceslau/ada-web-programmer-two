package com.ada.pedidocompra.infraestrutura.controladores.response;

import com.ada.pedidocompra.infraestrutura.repositorios.entidades.Usuario;

public record ClienteCredential(String email,
                                String senha,
                                String tipoUsuario) {

    public static ClienteCredential from(Usuario usuario) {
        return new ClienteCredential(usuario.getEmail(), usuario.getSenha(), usuario.getTipoUsuario().name());
    }
}
