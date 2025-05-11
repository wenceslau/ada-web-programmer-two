package com.ada.pedidocompra.quarkus.infraestrutura.controladores.response;

import java.util.List;

public record ProdutoResponsePage(long total,
                                  int pages,
                                  List<ProdutoResponse> content) {

}
