package com.ada.pedidocompra.quarkus.infraestrutura.servicos;

public interface EmailService {

    void sendEmail(String email, String assunto, String texto);

}
