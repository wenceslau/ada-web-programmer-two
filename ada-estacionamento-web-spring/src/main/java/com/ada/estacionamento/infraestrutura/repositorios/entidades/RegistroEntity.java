package com.ada.estacionamento.infraestrutura.repositorios.entidades;

import com.ada.estacionamento.dominio.Registro;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Registro")
@Table(name = "registros")
public class RegistroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "tipo_veiculo")
    private String tipoVeiculo;

    @Column(name = "hora_entrada")
    private LocalDateTime horaEntrada;

    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @Column(name = "valor_pago")
    private Double valorPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacionamento_id")
    private EstacionamentoEntity estacionamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public EstacionamentoEntity getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(EstacionamentoEntity estacionamento) {
        this.estacionamento = estacionamento;
    }

    public static RegistroEntity construir(Registro registro, Long estacionamentoId) {
        RegistroEntity registroEntity = new RegistroEntity();
        registroEntity.setId(registro.getId());
        registroEntity.setPlaca(registro.getVeiculo().getPlaca());
        registroEntity.setTipoVeiculo(registro.getVeiculo().getTipo().name());
        registroEntity.setHoraEntrada(registro.getHoraEntrada());
        registroEntity.setHoraSaida(registro.getHoraSaida());
        registroEntity.setValorPago(registro.getValorPago());
        registroEntity.setEstacionamento(new EstacionamentoEntity(estacionamentoId));
        return registroEntity;
    }
}
