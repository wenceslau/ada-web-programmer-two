package com.ada.pedidocompra.quarkus.dominio.servicos.pedido;

import com.ada.pedidocompra.quarkus.infraestrutura.configuracao.exceptions.NegocioException;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.ProdutoRepository;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.ItemPedido;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Pedido;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Produto;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.enums.StatusPedidoEnum;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Priority(30)
@ApplicationScoped
public class PedidoValidarEstoqueService implements ProcessarPedidoService {

    private final ProdutoRepository produtoRepository;

    public PedidoValidarEstoqueService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public void processar(Pedido pedido) {

        pedido.getItens().forEach(itemPedido -> validarEstoque(pedido, itemPedido));

    }

    private void validarEstoque(Pedido pedido, ItemPedido itemPedido) {
        Optional<Produto> byId = produtoRepository.findByIdOptional(itemPedido.getProduto().getId());

        if (byId.isEmpty()) {
          throw new NegocioException("Produto %s não encontrado".formatted(itemPedido.getProduto().getId()));
        }

        Produto produto = byId.get();
        if (produto.getEstoque() < itemPedido.getQuantidade()) {
            String mensagem = "Estoque Produto %s não disponível. Pedido cancelado!".formatted(produto.getDescricao());
            pedido.setStatus(StatusPedidoEnum.CANCELADO);
            pedido.setMensagemStatus(mensagem);
        }

    }

}
