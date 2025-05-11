package com.ada.estacionamento.infraestrutura.controladores;

import com.ada.estacionamento.infraestrutura.servicos.EstacionamentoService;
import com.ada.estacionamento.dominio.Registro;
import com.ada.estacionamento.infraestrutura.controladores.records.*;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.annotations.Pos;

import java.util.ArrayList;

@Authenticated
@Path("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @POST
    @Path("/iniciar/{capacidade}")
    @PermitAll  // TODO remover quando a autenticação estiver implementada
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciar(@PathParam("capacidade") int capacidade) {

        try {
            estacionamentoService.iniciar(capacidade);

            return Response
                    .noContent()
                    .build();

        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @POST
    @Path("/registrar")
    @PermitAll  // TODO remover quando a autenticação estiver implementada
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarPlaca(@Valid RegistrarRequest request) {

        try {
           var registro = estacionamentoService.registrarPlaca(request.placa(), request.tipoVeiculo());
           var response = RegistroResponse.construir(registro);

            return Response
                    .ok(response)
                    .build();

        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @GET
    @Path("/relatorio")
    @PermitAll  // TODO remover quando a autenticação estiver implementada
    @Produces(MediaType.APPLICATION_JSON)
    public Response relatorio() {

        try {
            var registros = estacionamentoService.registros();

            var entradas = new ArrayList<HistoricoEntrada>();
            for (Registro registro : registros) {
                if (registro.getHoraSaida() == null) {
                    var placa = registro.getVeiculo().getPlaca();
                    var tipo = registro.getVeiculo().getTipo().name();
                    var horaEntrada = registro.getHoraEntrada();
                    entradas.add(new HistoricoEntrada(placa, tipo, horaEntrada));
                }
            }

            var saidas = new ArrayList<HistoricoSaida>();
            for (Registro registro : registros) {
                if (registro.getHoraSaida() != null) {
                    var placa = registro.getVeiculo().getPlaca();
                    var tipo = registro.getVeiculo().getTipo().name();
                    var horaEntrada = registro.getHoraEntrada();
                    var horaSaida = registro.getHoraSaida();
                    var duracao = registro.getDuracao().toMinutes();
                    var valorPago = registro.getValorPago();
                    saidas.add(new HistoricoSaida(placa, tipo, horaEntrada, horaSaida, duracao, valorPago));
                }
            }

            var relatorioResponse = new RelatorioResponse(entradas, saidas);

            return Response
                    .ok(relatorioResponse)
                    .build();

        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @GET
    @Path("/status")
    @PermitAll  // TODO remover quando a autenticação estiver implementada
    @Produces(MediaType.APPLICATION_JSON)
    public Response status() {
        try {
            long capacidade = estacionamentoService.capacidade();
            long ocupacao = estacionamentoService.registros()
                    .stream()
                    .filter(r -> r.getHoraSaida() == null)
                    .count();
            long livres = capacidade - ocupacao;

            var response = new StatusResponse(capacidade, ocupacao, livres);

            return Response
                    .ok(response)
                    .build();

        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/{capacidade}")
    @PermitAll  // TODO remover quando a autenticação estiver implementada
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("capacidade") int capacidade) {

        try {
            estacionamentoService.atualizar(capacidade);

            return Response
                    .noContent()
                    .build();

        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }


}

