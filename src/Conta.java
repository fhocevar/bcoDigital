import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int sequencial = 1;

    private final int agencia;
    private final int numero;
    private final Cliente cliente;
    private BigDecimal saldo = new BigDecimal("0.00");

    protected Conta(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente obrigatorio.");
        this.agencia = AGENCIA_PADRAO;
        this.numero = sequencial++;
        this.cliente = cliente;
    }

    private static BigDecimal validarValor(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        return valor.setScale(2, RoundingMode.UNNECESSARY);
    }

    @Override
    public void sacar(BigDecimal valor) {
        valor = validarValor(valor);
        if (saldo.compareTo(valor) < 0) throw new IllegalStateException("Saldo insuficiente.");
        saldo = saldo.subtract(valor);
    }

    @Override
    public void depositar(BigDecimal valor) {
        saldo = saldo.add(validarValor(valor));
    }

    @Override
    public void transferir(BigDecimal valor, IConta destino) {
        valor = validarValor(valor);
        if (destino == null || destino == this || !(destino instanceof Conta)) {
            throw new IllegalArgumentException("Conta de destino invalida.");
        }
        if (saldo.compareTo(valor) < 0) throw new IllegalStateException("Saldo insuficiente.");
        // Transferencia demonstrativa em memoria, sem suporte a concorrencia.
        destino.depositar(valor);
        saldo = saldo.subtract(valor);
    }

    public int getAgencia() { return agencia; }
    public int getNumero() { return numero; }
    public BigDecimal getSaldo() { return saldo; }
    public Cliente getCliente() { return cliente; }

    protected void imprimirInfosComuns() {
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Agencia: " + agencia);
        System.out.println("Numero: " + numero);
        System.out.println("Saldo: R$ " + saldo);
    }
}
