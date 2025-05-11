package com.ada.estacionamento.infraestrutura.repositorios.entidades;

import com.ada.estacionamento.dominio.Estacionamento;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Estacionamento")
@Table(name = "estacionamentos")
public class EstacionamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_referencia")
    private LocalDate dataReferencia;

    @Column(name = "capacidade")
    private Integer capacidade;

    public EstacionamentoEntity() {
    }

    public EstacionamentoEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public static EstacionamentoEntity construir(Estacionamento estacionamento){
        EstacionamentoEntity estacionamentoEntity = new EstacionamentoEntity();
        estacionamentoEntity.setDataReferencia(estacionamento.getDataReferencia());
        estacionamentoEntity.setCapacidade(estacionamento.getCapacidade());
        return estacionamentoEntity;
    }

}
