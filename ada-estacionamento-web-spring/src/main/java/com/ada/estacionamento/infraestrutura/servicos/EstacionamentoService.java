package com.ada.estacionamento.infraestrutura.servicos;

import com.ada.estacionamento.dominio.Estacionamento;
import com.ada.estacionamento.dominio.Registro;
import com.ada.estacionamento.dominio.veiculos.VeiculoTipo;
import com.ada.estacionamento.infraestrutura.repositorios.EstacionamentoRepository;
import com.ada.estacionamento.infraestrutura.repositorios.RegistroRepository;
import com.ada.estacionamento.infraestrutura.repositorios.entidades.EstacionamentoEntity;
import com.ada.estacionamento.infraestrutura.repositorios.entidades.RegistroEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstacionamentoService {

    private Estacionamento estacionamento;

    private final RegistroRepository registroRepository;
    private final EstacionamentoRepository estacionamentoRepository;

    public EstacionamentoService(RegistroRepository registroRepository, EstacionamentoRepository estacionamentoRepository) {
        this.registroRepository = registroRepository;
        this.estacionamentoRepository = estacionamentoRepository;
    }

    public void iniciar(int capacidade) {
        //Inicia o estacionamento no domínio para o dia atual
        estacionamento = new Estacionamento(LocalDate.now(), capacidade);

        //Salva o estacionamento no repositório
        var entity = EstacionamentoEntity.construir(estacionamento);
        estacionamentoRepository.save(entity);

        //Atualiza o id do estacionamento no domínio
        estacionamento.setId(entity.getId());
    }

    public void atualizar(int capacidade) {

        // Verifica se o estacionamento foi iniciado
        if (estacionamento == null) {
            throw new IllegalArgumentException("Estacionamento não foi iniciado");
        }

        //Localizar o estacionamento pelo dia atual
        EstacionamentoEntity estacionamentoEntity = estacionamentoRepository
                .findByDataReferencia(estacionamento.getDataReferencia())
                .orElseThrow(() -> new IllegalArgumentException("Estacionamento não foi encontrado"));

        //Atualizar a capacidade do estacionamento no domínio
        estacionamento.atualizaCapacidade(capacidade);

        //Atualizar a capacidade do estacionamento no repositório
        estacionamentoEntity.setCapacidade(capacidade);
        estacionamentoRepository.save(estacionamentoEntity);
    }

    public Registro registrarPlaca(String placa, String veiculoTipo) {

        // Verifica se o estacionamento foi iniciado
        if (estacionamento == null) {
            throw new IllegalArgumentException("Estacionamento não foi iniciado");
        }

        // Cria uma instância do veículo com base no tipo informado
        var veiculoTipoEnum = VeiculoTipo.converter(veiculoTipo);
        double valorHora = valorVeiculoHora(veiculoTipoEnum);
        var veiculo = veiculoTipoEnum.criarInstancia(placa, valorHora, valorHora);

        // Registra o veículo no estacionamento
        var registro = estacionamento.registrarVeiculo(veiculo);

        // Salva o registro no repositório
        var registroEntity = RegistroEntity.construir(registro, estacionamento.getId());
        registroRepository.save(registroEntity);

        // Atualiza o id do registro no domínio
        registro.setId(registroEntity.getId());

        return registro;
    }

    public List<Registro> registros() {
        // Verifica se o estacionamento foi iniciado
        if (estacionamento == null) {
            throw new IllegalArgumentException("Estacionamento não foi iniciado");
        }
        return estacionamento.getRegistros();
    }

    public long capacidade() {
        // Verifica se o estacionamento foi iniciado
        if (estacionamento == null) {
            throw new IllegalArgumentException("Estacionamento não foi iniciado");
        }
        return estacionamento.getCapacidade();
    }

    private double valorVeiculoHora(VeiculoTipo veiculoTipo) {
        return switch (veiculoTipo){
            case CARRO -> 5.0; // Buscar valor em um repositório
            case MOTO -> 3.0; // Buscar valor em um repositório
        };
    }
}
