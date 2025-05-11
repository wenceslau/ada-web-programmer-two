package com.ada.pedidocompra.quarkus.infraestrutura.configuracao.seguranca;



import com.ada.pedidocompra.quarkus.dominio.servicos.ClienteService;
import com.ada.pedidocompra.quarkus.infraestrutura.configuracao.seguranca.jwt.JWTService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Objects;
import java.util.Set;


@Path("/login")
public class LoginResource {

    private final ClienteService clienteService;

    public LoginResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequest loginDto) {

        var credential = clienteService.findByEmail(loginDto.email());

        if (Objects.isNull(credential) || !credential.senha().equals(PasswordUtils.encode(loginDto.senha()))) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        String token = JWTService.createToken(credential.email(), Set.of(credential.tipoUsuario()));

        return Response.ok(new LoginResponse(token)).build();
    }

}
