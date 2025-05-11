package com.ada.estacionamento.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Estacionamento {

    private Long id;
    private Integer capacidade;
    private final LocalDate dataReferencia;
    private final List<Registro> registros;
    private final Pagamento pagamento;

    public Estacionamento(LocalDate dataReferencia, Integer capacidade) {
        if (capacidade <= 0){
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        this.dataReferencia = dataReferencia;
        this.capacidade = capacidade;
        this.registros = new ArrayList<>();
        this.pagamento = new Pagamento();
    }

    public void atualizaCapacidade(Integer capacidade) {
        if (capacidade == null || capacidade <= 0){
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        this.capacidade = capacidade;
    }

    public Registro registrarVeiculo(Veiculo veiculo) {

        if (veiculo == null){
            throw new IllegalArgumentException("Veiculo não pode ser nulo");
        }

        if (capacidade == null){
            throw new IllegalArgumentException("O estacionamento não foi inicializado");
        }

        var optional = procurarRegistro(veiculo);

        if (optional.isEmpty()) {
            return registrarEntrada(veiculo);
        }else {
            Registro registro = optional.get();
            return registrarSaida(registro);
        }
    }

    public Optional<Registro> procurarRegistro(Veiculo veiculo) {
        return registros.stream()
                .filter(r -> r.getVeiculo().getPlaca().equals(veiculo.getPlaca()))
                .filter(r -> r.getHoraSaida() == null)
                .findFirst();
    }

    private Registro registrarEntrada(Veiculo veiculo) {
        int count = registros.stream()
                .filter(r -> r.getHoraSaida() == null)
                .mapToInt(r -> 1)
                .sum();

        if (count == capacidade) {
            throw new IllegalStateException("Estacionamento lotado");
        }

        var registro = new Registro(veiculo, LocalDateTime.now());
        registros.add(registro);
        return registro;
    }

    private Registro registrarSaida(Registro registro) {

        registro.setHoraSaida(LocalDateTime.now());

        double valorPago = pagamento.processarValorPagar(registro.getVeiculo(),
                registro.getDuracao().toMinutes());

        registro.setValorPago(valorPago);

        return registro;
    }

    // Getters e Setters

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

}
