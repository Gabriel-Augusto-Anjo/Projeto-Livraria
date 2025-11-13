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
                try {
                    biblioteca.listarLivros();

                    System.out.print("Escolha um livro para emprestimo atraves do id : ");
                    biblioteca.setId(scanner.nextInt());

                    System.out.println("Insira seu nome para emprestimo");
                    biblioteca.setNomeCliente(scanner.next());

                    biblioteca.emprestar();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            case "não" :
                System.out.println("Execução finalizada com sucesso");
        }
    }
}
