import java.time.LocalDate;

public class Livro {
    int id;
    String titulo;
    String autor;
    String disponivel;
    String dataCadastro;
    String dataAtualizacao;

    public Livro(int id, String titulo, String autor, String disponivel, String dataCadastro, String dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
    }
}
