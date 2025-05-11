package com.ada.pedidocompra.quarkus.dominio.servicos;

import com.ada.pedidocompra.quarkus.dominio.servicos.pedido.ProcessarPedidoService;
import com.ada.pedidocompra.quarkus.infraestrutura.configuracao.exceptions.NegocioException;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.CreatePedidoRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.ItemPedidoRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.IdResponse;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.ProdutoRepository;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.UsuarioRepository;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.ItemPedido;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Pedido;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.enums.StatusPedidoEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class PedidoService {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final Instance<ProcessarPedidoService> processarPedidoServiceList;

    public PedidoService(ProdutoRepository produtoRepository,
                         UsuarioRepository usuarioRepository,
                         Instance<ProcessarPedidoService> processarPedidoServiceList
    ) {

        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.processarPedidoServiceList = processarPedidoServiceList;

    }

    public IdResponse create(CreatePedidoRequest createPedidoRequest) {

        var clienteEmail = "mclogsistemas@gmail.com";// JWTService.getUserContext();
        var usuario = usuarioRepository
                .findByEmail(clienteEmail)
                .orElseThrow(() -> new NegocioException("Usuário não encontrado! Email: " + clienteEmail));

        var pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedidoEnum.NOVO);
        pedido.setMensagemStatus("");
        pedido.setUsuario(usuario);

        var itens = createPedidoRequest
                .itens()
                .stream()
                .map(itemPedidoRequest -> criarItemPedido(itemPedidoRequest, pedido))
                .toList();

        pedido.setItens(itens);

        processarPedidoServiceList.forEach(processarPedidoService -> processarPedidoService.processar(pedido));

        if (pedido.getStatus().equals(StatusPedidoEnum.CANCELADO)) {
            throw new NegocioException("Pedido não pode ser realizado! " + pedido.getMensagemStatus());
        }

        return new IdResponse(pedido.getId());

    }

    private ItemPedido criarItemPedido(ItemPedidoRequest itemPedidoRequest, Pedido pedido) {

        var produto = produtoRepository
                .findByIdOptional(itemPedidoRequest.produtoId())
                .orElseThrow(() -> new NegocioException("Produto nao encontrado! Id: " + itemPedidoRequest.produtoId()));

        var itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setDesconto(BigDecimal.ZERO);
        itemPedido.setQuantidade(itemPedidoRequest.quantidade());
        itemPedido.setPedido(pedido);
        itemPedido.setPreco(produto.getPreco());

        return itemPedido;
    }


}
