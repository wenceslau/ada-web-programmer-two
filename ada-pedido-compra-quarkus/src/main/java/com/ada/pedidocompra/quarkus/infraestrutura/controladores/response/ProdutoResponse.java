package com.ada.pedidocompra.quarkus.infraestrutura.controladores.response;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(Long id,
                              String descricao,
                              BigDecimal preco,
                              Integer estoque,
                              String foto) {

    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(produto.getId(),
                            produto.getDescricao(),
                            produto.getPreco(),
                            produto.getEstoque(),
                            produto.getFoto());
    }

}
