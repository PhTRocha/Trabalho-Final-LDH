import java.util.ArrayList;
import java.util.List;

// jogador: guarda o nome e as letras que ja foram tentadas.
public class Jogador {

    private String nome;
    private List<Character> letrasInformadas;

    public Jogador(String nome) {
        this.nome = nome;
        this.letrasInformadas = new ArrayList<>();
    }

    // registra uma letra tentada.
    public void informarLetra(char letra) {
        letrasInformadas.add(letra);
    }

    // retorna true se a letra ja foi tentada antes (letra repetida).
    public boolean jaInformouLetra(char letra) {
        return letrasInformadas.contains(letra);
    }

    public List<Character> getLetrasInformadas() {
        return letrasInformadas;
    }

    public String getNome() {
        return nome;
    }

    // limpa o historico para um novo jogo.
    public void limparLetras() {
        letrasInformadas.clear();
    }
}
