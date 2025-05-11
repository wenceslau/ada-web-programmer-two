package com.ada.pedidocompra.quarkus.infraestrutura.controladores;

import com.ada.pedidocompra.quarkus.dominio.servicos.ProdutoService;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.CreateProdutoRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.ProdutoFilterRequest;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Authenticated
@Path("/produtos")
public class ProdutoResource {

    private final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(CreateProdutoRequest createProdutoRequest) {

        var idResponse = produtoService.criar(createProdutoRequest);

        return Response
                .status(Response.Status.CREATED)
                .entity(idResponse)
                .build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@BeanParam ProdutoFilterRequest produtoFilterRequest) {
        return Response
                .ok(produtoService.listar(produtoFilterRequest))
                .build();
    }

}
