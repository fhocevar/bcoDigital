public class Cliente {
    private final String nome;

    public Cliente(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente obrigatorio.");
        }
        this.nome = nome.trim();
    }

    public String getNome() { return nome; }
}
