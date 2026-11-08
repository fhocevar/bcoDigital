import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital DIO");
        Cliente cliente = new Cliente("Fernando");
        ContaCorrente corrente = new ContaCorrente(cliente);
        ContaPoupanca poupanca = new ContaPoupanca(cliente);
        banco.adicionarConta(corrente);
        banco.adicionarConta(poupanca);

        corrente.depositar(new BigDecimal("1000.00"));
        corrente.sacar(new BigDecimal("200.00"));
        corrente.transferir(new BigDecimal("300.00"), poupanca);

        System.out.println(banco.getNome());
        corrente.imprimirExtrato();
        poupanca.imprimirExtrato();

        try {
            corrente.sacar(new BigDecimal("2000.00"));
        } catch (IllegalStateException ex) {
            System.out.println("Operacao negada: " + ex.getMessage());
        }
        System.out.println("Total de contas: " + banco.getContas().size());
    }
}
