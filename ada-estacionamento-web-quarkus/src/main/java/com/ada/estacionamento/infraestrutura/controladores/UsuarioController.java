package com.ada.estacionamento.infraestrutura.controladores;

import com.ada.estacionamento.infraestrutura.config.PasswordUtils;
import com.ada.estacionamento.infraestrutura.controladores.records.UsuarioRequest;
import com.ada.estacionamento.infraestrutura.repositorios.UsuarioRepository;
import com.ada.estacionamento.infraestrutura.repositorios.entidades.UsuarioEntity;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Authenticated
@Path("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @POST
    @PermitAll  //Endpoint público
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(@Valid UsuarioRequest usuarioRequest) {

        try {

            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setUsername(usuarioRequest.username());
            usuarioEntity.setPassword(PasswordUtils.encode(usuarioRequest.senha()));
            usuarioRepository.persist(usuarioEntity);

            return Response
                    .ok("Usuário criado com sucesso")
                    .build();


        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

}
