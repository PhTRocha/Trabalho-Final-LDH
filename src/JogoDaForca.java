import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Inicia o jogo da forca, gerencia o fluxo das rodadas e valida se o jogador quer repetir a partida
public class JogoDaForca {

    private static final int MAX_TENTATIVAS = 6;

    public static void main(String[] args) {
        List<String> palavras = lerPalavras("palavras.txt");
        if (palavras.isEmpty()) {
            System.out.println("Erro: nenhuma palavra carregada. Verifique o arquivo palavras.txt");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== JOGO DA FORCA =====");
        System.out.print("Digite o nome do jogador: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            nome = "Jogador";
        }

        Jogador jogador = new Jogador(nome);
        Jogo jogo = new Jogo(jogador, palavras, MAX_TENTATIVAS);
        jogo.iniciarJogo();

        boolean continuar = true;
        while (continuar) {
            while (jogo.getStatusJogo()) {
                jogo.exibirEstadoJogo();
                char letra = lerLetra(scanner);
                jogo.realizarAdivinhacao(letra);
            }
            if (perguntarJogarNovamente(scanner)) {
                jogo.reiniciar();
            } else {
                jogo.sair();
                continuar = false;
            }
        }

        scanner.close();
    }

    // lerPalavras le as palavras presente no arquivo txt, uma por linha.
    private static List<String> lerPalavras(String caminho) {
        List<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    lista.add(linha.trim().toUpperCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return lista;
    }

    private static char lerLetra(Scanner scanner) {
        while (true) {
            System.out.print("\nDigite uma letra: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                return entrada.charAt(0);
            }
            System.out.println(">> Entrada invalida. Digite apenas UMA letra.");
        }
    }

    private static boolean perguntarJogarNovamente(Scanner scanner) {
        while (true) {
            System.out.print("\nDeseja jogar novamente? (S/N): ");
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (resposta.equals("S")) {
                return true;
            } else if (resposta.equals("N")) {
                return false;
            }
            System.out.println(">> Responda com 'S' ou 'N'.");
        }
    }
}
