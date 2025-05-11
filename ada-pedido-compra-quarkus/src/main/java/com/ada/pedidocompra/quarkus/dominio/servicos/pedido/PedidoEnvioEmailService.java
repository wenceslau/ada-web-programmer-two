package com.ada.pedidocompra.quarkus.dominio.servicos.pedido;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Pedido;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.enums.StatusPedidoEnum;
import com.ada.pedidocompra.quarkus.infraestrutura.servicos.EmailService;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;

@Priority(10)
@ApplicationScoped
public class PedidoEnvioEmailService implements ProcessarPedidoService {

    private final Logger log = LoggerFactory.getLogger(PedidoEnvioEmailService.class);
    private final EmailService emailService;

    public PedidoEnvioEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void processar(Pedido pedido) {

        var mensagem = criarMensagem(pedido);
        var assunto = "Pedido " + pedido.getId() + " - " + pedido.getStatus();

        log.info("Enviando email para o cliente: {} com assunto: {}", pedido.getUsuario().getEmail(), assunto);
        emailService.sendEmail(
                pedido.getUsuario().getEmail(),
                assunto,
                mensagem
        );

    }

    private String criarMensagem(final Pedido pedido) {
        var cliente = pedido.getUsuario();

        var dataPedido = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(pedido.getDataPedido());

        final StringBuilder mensagem = new StringBuilder();
        mensagem.append(cliente.getNome())
                .append(", ")
                .append("\n");

        mensagem.append("Data: ")
                .append(dataPedido)
                .append("\n");

        mensagem.append("Produtos: ")
                .append("\n");

        pedido.getItens().forEach(item -> mensagem
                .append(item.getQuantidade()).append("x ")
                .append(item.getProduto().getDescricao())
                .append("\n")
        );

        mensagem.append("Status: ")
                .append(pedido.getStatus())
                .append("\n");

        if (StatusPedidoEnum.CANCELADO.equals(pedido.getStatus())) {
            mensagem.append(pedido.getMensagemStatus())
                    .append("\n");
        }

        return mensagem.toString();
    }

}
