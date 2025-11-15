import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Aplicação simples de console para interagir com a Biblioteca.
 * Mantive tudo simples para facilitar entendimento e testes.
 */
public class Livraria {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Biblioteca biblioteca = new Biblioteca();
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        seedDummyData(); // opcional
        while (true) {
            mostrarMenu();
            int opc = lerInt("Escolha uma opção: ");
            try {
                switch (opc) {
                    case 0: System.out.println("Saindo..."); return;
                    case 1: cadastrarAutor(); break;
                    case 2: listarAutores(); break;
                    case 3: cadastrarLivro(); break;
                    case 4: listarLivros(); break;
                    case 5: emprestarLivro(); break;
                    case 6: listarEmprestimosAtivos(); break;
                    case 7: devolverEmprestimo(); break;
                    default: System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Livraria ===");
        System.out.println("1 - Cadastrar autor");
        System.out.println("2 - Listar autores");
        System.out.println("3 - Cadastrar livro");
        System.out.println("4 - Listar livros");
        System.out.println("5 - Emprestar livro");
        System.out.println("6 - Listar empréstimos ativos");
        System.out.println("7 - Devolver empréstimo");
        System.out.println("0 - Sair");
    }

    private static void cadastrarAutor() {
        System.out.println("-- Cadastrar Autor --");
        String nome = lerStr("Nome: ");
        String data = lerStr("Data nascimento (dd/MM/yyyy) ou Enter vazio: ");
        biblioteca.adicionarAutor(nome, data.isBlank() ? null : data);
        System.out.println("Autor cadastrado.");
    }

    private static void listarAutores() {
        System.out.println("-- Autores --");
        List<Autor> autores = biblioteca.listarAutores();
        if (autores.isEmpty()) System.out.println("Nenhum autor cadastrado.");
        else autores.forEach(a -> System.out.println(a));
    }

    private static void cadastrarLivro() {
        System.out.println("-- Cadastrar Livro --");
        String titulo = lerStr("Título: ");
        String autorNome = lerStr("Nome do autor (se existir, será usado; senão será criado): ");
        Optional<Autor> opt = biblioteca.buscarAutorPorNome(autorNome);
        Autor autor = opt.orElseGet(() -> biblioteca.adicionarAutor(autorNome, null));
        Livro l = biblioteca.adicionarLivro(titulo, autor);
        System.out.println("Livro cadastrado: " + l);
    }

    private static void listarLivros() {
        System.out.println("-- Livros --");
        List<Livro> livros = biblioteca.listarLivros();
        if (livros.isEmpty()) System.out.println("Nenhum livro.");
        else livros.forEach(System.out::println);
    }

    private static void emprestarLivro() {
        System.out.println("-- Emprestar Livro --");
        int id = lerInt("ID do livro: ");
        String cliente = lerStr("Nome do cliente: ");
        Emprestimo e = biblioteca.emprestarLivro(id, cliente);
        System.out.println("Emprestado: " + e);
    }

    private static void listarEmprestimosAtivos() {
        System.out.println("-- Empréstimos Ativos --");
        var list = biblioteca.listarEmprestimosAtivos();
        if (list.isEmpty()) System.out.println("Nenhum empréstimo ativo.");
        else list.forEach(System.out::println);
    }

    private static void devolverEmprestimo() {
        System.out.println("-- Devolver Empréstimo --");
        int id = lerInt("ID do empréstimo: ");
        biblioteca.devolverEmprestimo(id);
        System.out.println("Devolução registrada.");
    }

    // helpers
    private static int lerInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número: ");
            scanner.next();
        }
        int v = scanner.nextInt();
        scanner.nextLine(); // consumir newline
        return v;
    }

    private static String lerStr(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static void seedDummyData() {
        // opcional: adiciona alguns autores/livros para testar rapidamente
        var a1 = biblioteca.adicionarAutor("J. K. Rowling", "31/07/1965");
        var a2 = biblioteca.adicionarAutor("George Orwell", "25/06/1903");
        biblioteca.adicionarLivro("Harry Potter e a Pedra Filosofal", a1);
        biblioteca.adicionarLivro("1984", a2);
    }
}
