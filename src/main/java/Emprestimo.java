import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Representa um empréstimo de livro a um cliente.
 */
public class Emprestimo {
    private final int id;
    private final Livro livro;
    private final String cliente;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao; // null enquanto não devolvido

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Emprestimo(int id, Livro livro, String cliente, LocalDate dataEmprestimo) {
        if (livro == null) throw new IllegalArgumentException("Livro não pode ser nulo");
        if (cliente == null || cliente.isBlank()) throw new IllegalArgumentException("Cliente inválido");
        this.id = id;
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo != null ? dataEmprestimo : LocalDate.now();
        this.dataDevolucao = null;
    }

    public int getId() { return id; }
    public Livro getLivro() { return livro; }
    public String getCliente() { return cliente; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public boolean isDevolvido() { return dataDevolucao != null; }

    public void devolver(LocalDate data) {
        if (isDevolvido()) throw new IllegalStateException("Já devolvido");
        this.dataDevolucao = data != null ? data : LocalDate.now();
        this.livro.setDisponivel(true); // libera o livro automaticamente
    }

    @Override
    public String toString() {
        return String.format("Emprestimo[id=%d, livro=%s, cliente=%s, data=%s, devolvido=%s]",
                id,
                livro != null ? livro.getTitulo() : "N/A",
                cliente,
                dataEmprestimo.format(fmt),
                isDevolvido() ? "sim (" + dataDevolucao.format(fmt) + ")" : "não");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emprestimo)) return false;
        Emprestimo that = (Emprestimo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
