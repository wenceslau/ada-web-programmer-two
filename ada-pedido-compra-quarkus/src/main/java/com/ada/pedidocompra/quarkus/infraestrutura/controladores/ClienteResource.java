package com.ada.pedidocompra.quarkus.infraestrutura.controladores;

import com.ada.pedidocompra.quarkus.dominio.servicos.ClienteService;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.CreateClienteRequest;
import com.ada.pedidocompra.quarkus.infraestrutura.controladores.request.UpdateClienteRequest;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Authenticated
@Path("/clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    @PermitAll  //Endpoint público
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid CreateClienteRequest createClienteRequest) {

        var clienteResponse = clienteService.create(createClienteRequest);

        return Response
                .ok(clienteResponse)
                .build();

    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("clienteId") Long id) {

        var clienteResponse = clienteService.findById(id);

        return Response
                .ok(clienteResponse)
                .build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        var clienteList = clienteService.findAll();

        return Response
                .ok(clienteList)
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UpdateClienteRequest updateClienteRequest) {

        clienteService.update(id, updateClienteRequest);

        return Response
                .noContent()
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id) {

        clienteService.deleteById(id);

        return Response
                .noContent()
                .build();
    }

}
