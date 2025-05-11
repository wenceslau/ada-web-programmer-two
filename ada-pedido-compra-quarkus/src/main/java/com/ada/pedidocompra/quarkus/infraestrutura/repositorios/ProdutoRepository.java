package com.ada.pedidocompra.quarkus.infraestrutura.repositorios;

import com.ada.pedidocompra.quarkus.infraestrutura.repositorios.entidades.Produto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepositoryBase<Produto, Long> {

    public PanacheQuery<Produto> findByDescricaoLikeIgnoreCase(String filtro, Page page) {
        return find("lower(descricao) like lower(concat('%', ?1, '%'))", filtro)
                .page(page);
    }

    public PanacheQuery<Produto> findAllPageable(Page page) {
        return findAll().page(page);
    }
}
