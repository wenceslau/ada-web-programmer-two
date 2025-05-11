package com.ada.pedidocompra.quarkus.infraestrutura.servicos;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Logger;

@ApplicationScoped
public class SendEmailService implements EmailService {

    private final Mailer mailer;
    private final Logger log = Logger.getLogger(SendEmailService.class.getName());

    public SendEmailService(Mailer mailer) {
        this.mailer = mailer;
    }

    public void sendEmail(String email, String assunto, String texto) {

        Uni.createFrom()
                .item(new EmailDTO(email, assunto, texto))
                .emitOn(Infrastructure.getDefaultWorkerPool())
                .subscribe()
                .with(this::sendMailAsync, Throwable::printStackTrace);



    }

    private void sendMailAsync(final EmailDTO emailDTO) {

        final Mail mail = Mail.withText(
                emailDTO.email(),
                emailDTO.assunto(),
                emailDTO.corpo()
        );

        try {
            mailer.send(mail);
            log.info("Email enviado com sucesso para: %s. Ambiente Dev".formatted(emailDTO.email()));

        } catch (Exception e) {
            log.warning("Erro ao enviar email para: %s. Erro: %s".formatted(emailDTO.email(), e.getMessage()));
        }
    }
}
