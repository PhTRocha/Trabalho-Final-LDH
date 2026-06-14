import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// jogo: motor do jogo. controla as regras e o estado da partida.
public class Jogo {

    private Jogador jogador;
    private Palavra palavraSecreta;
    private int quantidadeTentativas;
    private int tentativasRestantes;
    private List<Character> letrasCorretas;
    private List<Character> letrasIncorretas;
    private boolean statusJogo;

    private List<String> bancoDePalavras;
    private Random random;

    public Jogo(Jogador jogador, List<String> bancoDePalavras, int quantidadeTentativas) {
        this.jogador = jogador;
        this.bancoDePalavras = bancoDePalavras;
        this.quantidadeTentativas = quantidadeTentativas;
        this.letrasCorretas = new ArrayList<>();
        this.letrasIncorretas = new ArrayList<>();
        this.random = new Random();
    }

    // sorteia uma palavra e prepara o jogo.
    public void iniciarJogo() {
        String sorteada = bancoDePalavras.get(random.nextInt(bancoDePalavras.size()));
        palavraSecreta = new Palavra(sorteada);
        tentativasRestantes = quantidadeTentativas;
        letrasCorretas.clear();
        letrasIncorretas.clear();
        statusJogo = true;
    }

    // processa uma letra: ignora repetida, marca acerto/erro e checa fim de jogo.
    public void realizarAdivinhacao(char letra) {
        if (jogador.jaInformouLetra(letra)) {
            System.out.println(">> A letra '" + letra + "' ja foi tentada. Escolha outra.");
            return;
        }

        jogador.informarLetra(letra);

        if (palavraSecreta.verificarPalavra(letra)) {
            letrasCorretas.add(letra);
            System.out.println(">> Boa! A letra '" + letra + "' esta na palavra.");
        } else {
            letrasIncorretas.add(letra);
            tentativasRestantes--;
            System.out.println(">> A letra '" + letra + "' nao esta na palavra. Voce perdeu uma tentativa.");
        }

        if (verificarVitoria()) {
            exibirEstadoJogo();
            System.out.println("\n*** PARABENS, " + jogador.getNome() + "! VOCE VENCEU! ***");
            System.out.println("A palavra era: " + palavraSecreta.getTexto());
            statusJogo = false;
        } else if (verificarDerrota()) {
            System.out.println("\n*** QUE PENA, " + jogador.getNome() + "! VOCE PERDEU! ***");
            System.out.println("A palavra era: " + palavraSecreta.getTexto());
            statusJogo = false;
        }
    }

    // vitoria: todas as letras foram acertadas.
    public boolean verificarVitoria() {
        return palavraSecreta.verificarPalavraCompleta(letrasCorretas);
    }

    // derrota: acabaram as tentativas.
    public boolean verificarDerrota() {
        return tentativasRestantes <= 0;
    }

    // mstra o estado atual da partida.
    public void exibirEstadoJogo() {
        System.out.println("\n==================================================");
        System.out.println("Jogador: " + jogador.getNome());
        System.out.println("Tamanho da palavra: " + palavraSecreta.getTexto().length() + " letra(s)");
        System.out.println("Palavra: " + palavraSecreta.exibirPalavraParcial(letrasCorretas));
        System.out.println("Letras incorretas: " + letrasIncorretas);
        System.out.println("Tentativas restantes: " + tentativasRestantes);
        System.out.println("==================================================");
    }

    // reinicia para uma nova partida.
    public void reiniciar() {
        jogador.limparLetras();
        iniciarJogo();
        System.out.println("\n--- Novo jogo iniciado! ---");
    }

    // encerra o jogo.
    public void sair() {
        statusJogo = false;
        System.out.println("\nObrigado por jogar, " + jogador.getNome() + "!");
    }

    public boolean getStatusJogo() {
        return statusJogo;
    }
}
