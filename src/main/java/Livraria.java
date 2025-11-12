import java.util.Locale;
import java.util.Scanner;

public class Livraria {
    static void main() {

        String resposta;
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        Biblioteca biblioteca = new Biblioteca();

        System.out.println("Você deseja ver a lista de livros ? responda com sim ou não");
        resposta = scanner.next();

        switch (resposta) {
            case "sim" :
                biblioteca.listarLivros();

                System.out.print("Escolha um livro para emprestimo atraves do id : ");
                int idEscolhido = scanner.nextInt();
            case "não" :
                System.out.println("Execução finalizada com sucesso");
        }
    }
}
