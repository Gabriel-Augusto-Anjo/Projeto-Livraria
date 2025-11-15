import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Representa um autor.
 */
public class Autor {
    private final int id;
    private String nome;
    private LocalDate dataNascimento;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Autor(int id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Autor(int id, String nome, String dataNascimentoStr) {
        this(id, nome, parse(dataNascimentoStr));
    }

    private static LocalDate parse(String s) {
        if (s == null || s.isBlank()) return null;
        return LocalDate.parse(s, fmt);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome inválido");
        this.nome = nome;
    }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setDataNascimento(String dataNascimentoStr) { this.dataNascimento = parse(dataNascimentoStr); }

    public String getDataNascimentoFormatada() {
        return dataNascimento == null ? "" : dataNascimento.format(fmt);
    }

    @Override
    public String toString() {
        return String.format("Autor[id=%d, nome=%s%s]",
                id,
                nome,
                dataNascimento != null ? ", nascido em " + getDataNascimentoFormatada() : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
