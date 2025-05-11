package com.ada.estacionamento.dominio;

public class Pagamento {

    public Double processarValorPagar(Veiculo veiculo, Long minutos) {

        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo");
        }

        if (minutos == null || minutos < 0) {
            throw new IllegalArgumentException("Minutos não pode ser nulo ou negativo");
        }


        if (minutos <= 5){
            return  0d;
        } else if (minutos <= 60) {
            return veiculo.getValorHora();
        } else {

            double valor = veiculo.getValorHora();
            double valorFracao = veiculo.getFracaoHora();

            // hora cheia
            if (minutos % 60 == 0) {
                return valor * (minutos / 60.0);
            }

            // hora cheia + fração
            valor =  valor *  Math.ceil((minutos - 60) / 60.0);
            return  valor + valorFracao;
        }

    }

}
