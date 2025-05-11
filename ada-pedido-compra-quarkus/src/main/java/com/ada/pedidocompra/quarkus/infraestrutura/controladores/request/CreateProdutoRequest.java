package com.ada.pedidocompra.quarkus.infraestrutura.controladores.request;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Produto;

import java.math.BigDecimal;

public record CreateProdutoRequest(String descricao,
                                   BigDecimal preco,
                                   Integer estoque,
                                   String foto) {

    public Produto toProduto() {
        var produto = new Produto();
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setEstoque(estoque);
        produto.setFoto(foto);
        return produto;
    }
}
