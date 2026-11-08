# Banco Digital com Java e POO — DIO

Implementacao do desafio **Criando um Banco Digital com Java e Orientacao a Objetos**, baseada no projeto de referencia da DIO, com melhorias proprias.

## Requisitos atendidos

- Cliente e banco
- Conta corrente e conta poupanca
- Deposito, saque, transferencia e extrato
- Abstracao (`IConta` e `Conta` abstrata)
- Encapsulamento (atributos privados)
- Heranca (`ContaCorrente` e `ContaPoupanca`)
- Polimorfismo (implementacoes de `IConta`)

## Melhorias

- `BigDecimal` para valores monetarios
- Bloqueio de saldo insuficiente e de valores nao positivos
- Validacao de cliente, destino de transferencia e duplicidade de contas
- Colecao de contas somente leitura para consumidores
- 16 verificacoes automatizadas sem dependencias externas

## Estrutura

```text
banco-digital/
├── src/
│   ├── IConta.java
│   ├── Cliente.java
│   ├── Conta.java
│   ├── ContaCorrente.java
│   ├── ContaPoupanca.java
│   ├── Banco.java
│   └── Main.java
├── tests/
│   └── BancoDigitalTest.java
├── .gitignore
└── README.md
```

## Executar (PowerShell)

```powershell
New-Item -ItemType Directory -Force build
javac -d build src/*.java tests/*.java
java -cp build Main
java -cp build BancoDigitalTest
```

Requer JDK 11 ou superior. O projeto usa classes no pacote padrao para facilitar a execucao.

## Exemplo de saldo

Deposito de R$ 1000,00, saque de R$ 200,00 e transferencia de R$ 300,00: conta corrente com R$ 500,00 e poupanca com R$ 300,00.

## Limites

Exemplo educacional em memoria, sem persistencia, autenticacao ou controle de concorrencia. Nao e um sistema bancario para producao.

## Referencia

Projeto de referencia fornecido no desafio: https://github.com/falvojr/dio-live-20210802

## Autor

Fernando Hocevar — https://github.com/fhocevar
