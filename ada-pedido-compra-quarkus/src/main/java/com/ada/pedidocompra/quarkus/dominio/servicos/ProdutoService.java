package com.ada.pedidocompra.quarkus.dominio.servicos;

import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.CreateProdutoRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.ProdutoFilterRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.IdResponse;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.ProdutoResponse;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.response.ProdutoResponsePage;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.ProdutoRepository;
import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Produto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public IdResponse criar(CreateProdutoRequest createProdutoRequest) {
        var produto = createProdutoRequest.toProduto();
        produtoRepository.persist(produto);
        return new IdResponse(produto.getId());
    }

    public ProdutoResponsePage listar(ProdutoFilterRequest produtoFilterRequest) {

        var filtro = produtoFilterRequest.getFilter();
        var page = Page.of(produtoFilterRequest.getPage(), produtoFilterRequest.getSize());

        PanacheQuery<Produto> produtosPanacheQuery;

        if (filtro == null || filtro.isBlank()) {
            produtosPanacheQuery = produtoRepository.findAllPageable(page);
        }else{
            produtosPanacheQuery = produtoRepository.findByDescricaoLikeIgnoreCase(
                    filtro,
                    page
            );
        }

        var produtosContent = produtosPanacheQuery.list()
                .stream()
                .map(ProdutoResponse::from)
                .toList();

        return new ProdutoResponsePage(
                produtosContent.size(),
                produtosPanacheQuery.pageCount(),
                produtosContent
        );
    }

}
