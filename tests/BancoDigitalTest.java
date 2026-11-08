import java.math.BigDecimal;

public class BancoDigitalTest {
    private static int testes = 0;

    private static void verificar(boolean condicao, String mensagem) {
        testes++;
        if (!condicao) throw new AssertionError(mensagem);
    }

    private static void esperarErro(Class<? extends RuntimeException> tipo, Runnable acao) {
        testes++;
        try {
            acao.run();
        } catch (RuntimeException ex) {
            if (tipo.isInstance(ex)) return;
            throw new AssertionError("Excecao inesperada: " + ex);
        }
        throw new AssertionError("Excecao esperada: " + tipo.getSimpleName());
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Teste");
        ContaCorrente corrente = new ContaCorrente(cliente);
        ContaPoupanca poupanca = new ContaPoupanca(cliente);
        Banco banco = new Banco("DIO");
        banco.adicionarConta(corrente);
        banco.adicionarConta(poupanca);

        verificar(banco.getContas().size() == 2, "Cadastro de contas");
        verificar(corrente.getNumero() != poupanca.getNumero(), "Numeros unicos");
        corrente.depositar(new BigDecimal("100.00"));
        verificar(corrente.getSaldo().compareTo(new BigDecimal("100.00")) == 0, "Deposito");
        corrente.sacar(new BigDecimal("25.00"));
        verificar(corrente.getSaldo().compareTo(new BigDecimal("75.00")) == 0, "Saque");
        corrente.transferir(new BigDecimal("30.00"), poupanca);
        verificar(corrente.getSaldo().compareTo(new BigDecimal("45.00")) == 0, "Debito transferencia");
        verificar(poupanca.getSaldo().compareTo(new BigDecimal("30.00")) == 0, "Credito transferencia");
        esperarErro(IllegalStateException.class, () -> corrente.sacar(new BigDecimal("50.00")));
        esperarErro(IllegalArgumentException.class, () -> corrente.depositar(BigDecimal.ZERO));
        esperarErro(IllegalArgumentException.class, () -> corrente.depositar(new BigDecimal("-1.00")));
        esperarErro(IllegalArgumentException.class, () -> corrente.transferir(BigDecimal.ONE, corrente));
        esperarErro(IllegalArgumentException.class, () -> corrente.transferir(BigDecimal.ONE, null));
        esperarErro(IllegalStateException.class, () -> corrente.transferir(new BigDecimal("50.00"), poupanca));
        verificar(corrente.getSaldo().compareTo(new BigDecimal("45.00")) == 0, "Saldo preservado");
        esperarErro(IllegalArgumentException.class, () -> new Cliente(" "));
        esperarErro(UnsupportedOperationException.class, () -> banco.getContas().clear());
        esperarErro(IllegalArgumentException.class, () -> banco.adicionarConta(corrente));
        System.out.println("OK: " + testes + " testes passaram.");
    }
}
