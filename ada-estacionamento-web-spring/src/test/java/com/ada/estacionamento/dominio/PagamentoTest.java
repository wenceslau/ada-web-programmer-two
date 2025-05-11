package com.ada.estacionamento.dominio;

import com.ada.estacionamento.dominio.veiculos.Carro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoTest {

    @Test
    public void menosDeCincoMinutosDeEstacionamentoCusta0Reais() {
        // Arrange
        Pagamento pagamento = new Pagamento();
        Carro carro = new Carro("ABC-1234", 5.0, 5.0);

        // Act
        double valor = pagamento.processarValorPagar(carro, 4L);

        // Assert
        assertEquals(0.0, valor);
    }

    @Test
    public void umaHoraDeEstacionamentoCusta5Reais() {
        // Arrange
        Pagamento pagamento = new Pagamento();
        Carro carro = new Carro("ABC-1234", 5.0, 5.0);

        // Act
        double valor = pagamento.processarValorPagar(carro, 60L);

        // Assert
        assertEquals(5.0, valor);
    }

    @Test
    public void umaHoraUmMinutoDeEstacionamentoCusta10Reais() {
        // Arrange
        Pagamento pagamento = new Pagamento();
        Carro carro = new Carro("ABC-1234", 5.0, 5.0);

        // Act
        double valor = pagamento.processarValorPagar(carro, 61L);

        // Assert
        assertEquals(10.0, valor);
    }

    @Test
    public void centoDezenoveMinutosDeEstacionamentoCusta9Reais() {
        // Arrange
        Pagamento pagamento = new Pagamento();
        Carro carro = new Carro("ABC-1234", 5.0, 4.0);

        // Act
        double valor = pagamento.processarValorPagar(carro, 119L);

        // Assert
        assertEquals(9, valor);
    }

    @Test
    public void centoVinteMinutosDeEstacionamentoCusta10Reais() {
        // Arrange
        Pagamento pagamento = new Pagamento();
        Carro carro = new Carro("ABC-1234", 5.0, 4.0);

        // Act
        double valor = pagamento.processarValorPagar(carro, 120L);

        // Assert
        assertEquals(10, valor);
    }

    @Test
    public void centoVinteUmMinutosDeEstacionamentoCusta14Reais() {
        // Arrange
        Pagamento pagamento = new Pagamento();
        Carro carro = new Carro("ABC-1234", 5.0, 4.0);

        // Act
        double valor = pagamento.processarValorPagar(carro, 121L);

        // Assert
        assertEquals(14, valor);
    }
}
