import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe que gerencia coleções de autores, livros e empréstimos.
 * Não usa campos estáticos — permite instanciar várias bibliotecas.
 */
public class Biblioteca {
    private final List<Livro> livros = new ArrayList<>();
    private final List<Autor> autores = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    // IDs simples gerados localmente
    private final AtomicInteger nextLivroId = new AtomicInteger(1);
    private final AtomicInteger nextAutorId = new AtomicInteger(1);
    private final AtomicInteger nextEmprestimoId = new AtomicInteger(1);

    // AUTHORS
    public Autor adicionarAutor(String nome, String dataNascimentoStr) {
        Autor a = new Autor(nextAutorId.getAndIncrement(), nome, dataNascimentoStr);
        autores.add(a);
        return a;
    }

    public Optional<Autor> buscarAutorPorId(int id) {
        return autores.stream().filter(a -> a.getId() == id).findFirst();
    }

    public Optional<Autor> buscarAutorPorNome(String nome) {
        return autores.stream().filter(a -> a.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public List<Autor> listarAutores() {
        return List.copyOf(autores);
    }

    // BOOKS
    public Livro adicionarLivro(String titulo, Autor autor) {
        Livro l = new Livro(nextLivroId.getAndIncrement(), titulo, autor);
        livros.add(l);
        return l;
    }

    public Optional<Livro> buscarLivroPorId(int id) {
        return livros.stream().filter(l -> l.getId() == id).findFirst();
    }

    public List<Livro> listarLivros() {
        return List.copyOf(livros);
    }

    public boolean removerLivro(int id) {
        return buscarLivroPorId(id).map(livros::remove).orElse(false);
    }

    // LOANS
    public Emprestimo emprestarLivro(int livroId, String cliente) {
        Livro l = buscarLivroPorId(livroId).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        if (!l.isDisponivel()) throw new IllegalStateException("Livro não disponível");
        l.setDisponivel(false);
        Emprestimo e = new Emprestimo(nextEmprestimoId.getAndIncrement(), l, cliente, LocalDate.now());
        emprestimos.add(e);
        return e;
    }

    public void devolverEmprestimo(int emprestimoId) {
        Emprestimo e = emprestimos.stream().filter(x -> x.getId() == emprestimoId)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));
        if (e.isDevolvido()) throw new IllegalStateException("Já devolvido");
        e.devolver(LocalDate.now());
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) if (!e.isDevolvido()) ativos.add(e);
        return List.copyOf(ativos);
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        return List.copyOf(emprestimos);
    }
}
