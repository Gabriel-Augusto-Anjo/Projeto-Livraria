import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Representa um livro.
 */
public class Livro {
    private final int id;
    private String titulo;
    private Autor autor;           // relacionamento forte para Autor
    private boolean disponivel;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Livro(int id, String titulo, Autor autor) {
        this(id, titulo, autor, true, LocalDate.now(), null);
    }

    public Livro(int id, String titulo, Autor autor, boolean disponivel,
                 LocalDate dataCadastro, LocalDate dataAtualizacao) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título inválido");
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.dataCadastro = dataCadastro != null ? dataCadastro : LocalDate.now();
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("Título inválido");
        this.titulo = titulo;
        touch();
    }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) {
        this.autor = autor;
        touch();
    }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
        touch();
    }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public LocalDate getDataAtualizacao() { return dataAtualizacao; }
    private void touch() { this.dataAtualizacao = LocalDate.now(); }

    public String getDataCadastroFormatada() { return dataCadastro.format(fmt); }
    public String getDataAtualizacaoFormatada() {
        return dataAtualizacao == null ? "" : dataAtualizacao.format(fmt);
    }

    @Override
    public String toString() {
        return String.format("Livro[id=%d, titulo=%s, autor=%s, disponivel=%s]",
                id, titulo, (autor == null ? "N/A" : autor.getNome()), disponivel ? "sim" : "não");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;
        return id == livro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
