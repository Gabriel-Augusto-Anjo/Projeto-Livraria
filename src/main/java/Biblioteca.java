import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Biblioteca extends Emprestimo{
    private static List<Livro> livros = new ArrayList<>();
    private static List<Autor> autores = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();

    Emprestimo emprestimo = new Emprestimo();

    static {
        Livro l1 = new Livro(1, "Harry Potter 1", "Gabriel", "sim", "11/11/2025", "11/11/2025");
        Livro l2 = new Livro(2, "Harry Potter 2", "Gabriel", "sim", "11/11/2025", "11/11/2025");
        Livro l3 = new Livro(3, "Harry Potter 3", "Gabriel", "sim", "11/11/2025", "11/11/2025");

        Autor a1 = new Autor(1, "Gabriel", "11/02/2004");
        Autor a2 = new Autor(2, "Roberto", "10/02/2006");
        Autor a3 = new Autor(3, "Miguel", "11/09/2001");

        livros.add(l1);
        livros.add(l2);
        livros.add(l3);

        autores.add(a1);
        autores.add(a2);
        autores.add(a3);
    }


    public void adicionarLivro (Livro livro) {
        livros.add(livro);
    }

    public void listarLivros () {
        if (livros.isEmpty()) {
            System.out.println("nenhum livro disponivel");
        } else {
            System.out.println("Os seguintes livros estão disponiveis:");
            for (Livro l : livros) {
                if (l.disponivel == "sim") {
                    System.out.println(l.titulo + " id : " + l.id);
                } else {
                    continue;
                }
            }
        }
    }

    public void emprestar () {
        for (Livro l : livros) {
            if (l.id == emprestimo.getId()) {
                l.disponivel = "não";
                Emprestimo emp1 = new Emprestimo();
                emp1.setNomeCliente(getNomeCliente());
            }
        }
    }
}
