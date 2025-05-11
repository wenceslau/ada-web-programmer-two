package com.ada.pedidocompra.dominio.servicos.pedido;

import com.ada.pedidocompra.infraestrutura.repositorios.PedidoRepository;
import com.ada.pedidocompra.infraestrutura.repositorios.ProdutoRepository;
import com.ada.pedidocompra.infraestrutura.repositorios.entidades.ItemPedido;
import com.ada.pedidocompra.infraestrutura.repositorios.entidades.Pedido;
import com.ada.pedidocompra.infraestrutura.repositorios.entidades.Produto;
import com.ada.pedidocompra.infraestrutura.repositorios.entidades.enums.StatusPedidoEnum;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Priority(20)
@ApplicationScoped
public class PedidoBaixarEstoqueService implements ProcessarPedidoService{

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoBaixarEstoqueService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public void processar(Pedido pedido) {

        if (StatusPedidoEnum.CANCELADO.equals(pedido.getStatus())) {
            return;
        }

        // Atualiza o estoque dos produtos do pedido
        pedido.getItens().forEach(this::atualizarEstoque);

        pedidoRepository.persist(pedido);

    }

    private void atualizarEstoque(ItemPedido itemPedido) {
        // Localiza o produto no banco de dados
        Optional<Produto> byId = produtoRepository.findByIdOptional(itemPedido.getProduto().getId());

        // Verifica se o produto existe e atualiza o estoque
        if (byId.isPresent()) {
            Produto produto = byId.get();
            produto.setEstoque(produto.getEstoque() - itemPedido.getQuantidade());
            produtoRepository.persist(produto);
        }
    }
}
