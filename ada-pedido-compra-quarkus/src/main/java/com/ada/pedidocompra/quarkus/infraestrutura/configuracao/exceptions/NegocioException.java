package com.ada.pedidocompra.quarkus.infraestrutura.configuracao.exceptions;

public class NegocioException extends RuntimeException {

    public NegocioException(String msg) {
        super(msg);
    }

}
