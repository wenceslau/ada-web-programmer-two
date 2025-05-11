package com.ada.pedidocompra.quarkus.infraestrutura.controladores;

import com.ada.pedidocompra.quarkus.dominio.servicos.PedidoService;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.CreatePedidoRequest;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Authenticated
@Path("/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @POST
    @RolesAllowed({"CLIENTE", "ADMIN"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPedido(@Valid CreatePedidoRequest createPedidoRequest) {

        var pedidoId = pedidoService.create(createPedidoRequest);

        return Response
                .status(Response.Status.CREATED)
                .entity(pedidoId)
                .build();
    }

}
