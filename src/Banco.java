import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {
    private final String nome;
    private final List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do banco obrigatorio.");
        }
        this.nome = nome.trim();
    }

    public String getNome() { return nome; }

    public void adicionarConta(Conta conta) {
        if (conta == null || contas.contains(conta)) {
            throw new IllegalArgumentException("Conta invalida ou ja cadastrada.");
        }
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }
}
