package com.ada.estacionamento.infraestrutura.controladores;

import com.ada.estacionamento.infraestrutura.servicos.EstacionamentoService;
import com.ada.estacionamento.dominio.Registro;
import com.ada.estacionamento.infraestrutura.controladores.records.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @PostMapping("/iniciar/{capacidade}")
    public ResponseEntity<?> iniciar(@PathVariable int capacidade) {

        try {
            estacionamentoService.iniciar(capacidade);

            return ResponseEntity
                    .noContent()
                    .build();

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPlaca(@RequestBody @Valid RegistrarRequest request) {

        try {
           var registro = estacionamentoService.registrarPlaca(request.placa(), request.tipoVeiculo());
           var response = RegistroResponse.construir(registro);

            return ResponseEntity
                    .ok()
                    .body(response);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/relatorio")
    public ResponseEntity<?> relatorio() {

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

            return ResponseEntity
                    .ok()
                    .body(relatorioResponse);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        try {
            long capacidade = estacionamentoService.capacidade();
            long ocupacao = estacionamentoService.registros()
                    .stream()
                    .filter(r -> r.getHoraSaida() == null)
                    .count();
            long livres = capacidade - ocupacao;

            var response = new StatusResponse(capacidade, ocupacao, livres);

            return ResponseEntity
                    .ok()
                    .body(response);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{capacidade}")
    public ResponseEntity<?> atualizar(@PathVariable int capacidade) {

        try {
            estacionamentoService.atualizar(capacidade);

            return ResponseEntity
                    .noContent()
                    .build();

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


}

