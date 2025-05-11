classDiagram
direction BT
class Carro {
  + Carro(String, double, double) 
  - double fracaoHora
  - double valorHora
  + getTipo() VeiculoTipo
  + valorFracaoHora() double
  + valorHora() double
}
class Estacionamento {
  + Estacionamento(LocalDate, Integer) 
  - Integer capacidade
  - LocalDate dataReferencia
  - Long id
  - Pagamento pagamento
  - List~Registro~ registros
  + atualizaCapacidade(Integer) void
  + getCapacidade() Integer
  + getDataReferencia() LocalDate
  + getId() long
  + getRegistros() List~Registro~
  + procurarRegistro(Veiculo) Optional~Registro~
  - registrarEntrada(Veiculo) Registro
  - registrarSaida(Registro) Registro
  + registrarVeiculo(Veiculo) Registro
  + setId(Long) void
}
class Moto {
  + Moto(String, double, double) 
  - double fracaoHora
  - double valorHora
  + getTipo() VeiculoTipo
  + valorFracaoHora() double
  + valorHora() double
}
class Pagamento {
  + Pagamento() 
  + processarValorPagar(Veiculo, Long) double
}
class Registro {
  + Registro(Veiculo, LocalDateTime) 
  - Duration duracao
  - LocalDateTime horaEntrada
  - LocalDateTime horaSaida
  - Long id
  - Double valorPago
  - Veiculo veiculo
  + getDuracao() Duration
  + getHoraEntrada() LocalDateTime
  + getHoraSaida() LocalDateTime
  + getId() Long
  + getValorPago() Double
  + getVeiculo() Veiculo
  + setHoraSaida(LocalDateTime) void
  + setId(Long) void
  + setValorPago(Double) void
}
class Veiculo {
  + Veiculo(String) 
  - String placa
  + getPlaca() String
  + getTipo() VeiculoTipo
  + valorFracaoHora() double
  + valorHora() double
}
class VeiculoTipo {
<<enumeration>>
  + VeiculoTipo() 
  +  CARRO
  +  MOTO
  + converter(String) VeiculoTipo
  + criarInstancia(String, double, double) Veiculo
  + valueOf(String) VeiculoTipo
  + values() VeiculoTipo[]
}

Carro  -->  Veiculo 
Moto  -->  Veiculo 
