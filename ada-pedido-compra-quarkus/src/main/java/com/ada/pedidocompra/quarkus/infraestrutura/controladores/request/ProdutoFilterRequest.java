package com.ada.pedidocompra.quarkus.infraestrutura.controladores.request;

import jakarta.ws.rs.QueryParam;

public class ProdutoFilterRequest {

    @QueryParam("page")
    private int page;
    @QueryParam("size")
    private int size;
    @QueryParam("filter")
    private String filter;

    public ProdutoFilterRequest(int page, int size, String filter) {
        this.page = page;
        this.size = size;
        this.filter = filter;
    }

    public ProdutoFilterRequest() {
    }

    public int getPage() {
        return page;
    }

    public ProdutoFilterRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        if (size == 0) {
            size = 10;
        }
        return size;
    }

    public ProdutoFilterRequest setSize(int size) {
        this.size = size;
        return this;
    }

    public String getFilter() {
        return filter;
    }

    public ProdutoFilterRequest setFilter(String filter) {
        this.filter = filter;
        return this;
    }
}
