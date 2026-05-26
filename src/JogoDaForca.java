import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogoDaForca {

    public static void main(String[] args) {
        // Nome do arquivo de texto que contém as palavras (uma por linha)
        String caminhoArquivo = "palavras.txt";

        List<String> palavras = lerPalavras(caminhoArquivo);

        // Verificação de segurança caso o arquivo não exista ou esteja vazio
        if (palavras.isEmpty()) {
            System.out.println("Erro: Nenhuma palavra carregada. Verifique o arquivo " + caminhoArquivo);
            return; // Encerra a execução precocemente
        }

        String palavraOculta = sortearPalavra(palavras);

        // Exibindo apenas para você testar se a lógica funcionou.
        // No jogo final, obviamente, não vamos imprimir isso!
        System.out.println("Palavra sorteada com sucesso: " + palavraOculta);

        // TODO: Inicializar a classe Jogador e começar o loop principal do jogo (while)
    }

    // Método para ler o arquivo e retornar uma lista de strings
    private static List<String> lerPalavras(String caminho) {
        List<String> lista = new ArrayList<>();

        // O bloco try-with-resources (os parênteses após o try) garante que o
        // arquivo seja fechado automaticamente no final, liberando o recurso do SO.
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Remove espaços extras e padroniza para maiúsculo para facilitar a comparação depois
                if (!linha.trim().isEmpty()) {
                    lista.add(linha.trim().toUpperCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao tentar ler o arquivo: " + e.getMessage());
        }

        return lista;
    }

    // Método para escolher um índice aleatório dentro do tamanho da lista
    private static String sortearPalavra(List<String> palavras) {
        Random random = new Random();
        int indice = random.nextInt(palavras.size());
        return palavras.get(indice);
    }
}