import java.util.List;

// palavra: representa a palavra secreta do jogo.
public class Palavra {

    private String texto;

    public Palavra(String texto) {
        this.texto = texto;
    }

    // retorna true se a letra existe na palavra.
    public boolean verificarPalavra(char letra) {
        return texto.indexOf(letra) >= 0;
    }

    // monta a palavra mostrando as letras acertadas e "_" no resto.
    public String exibirPalavraParcial(List<Character> letrasCorretas) {
        String resultado = "";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (letrasCorretas.contains(c)) {
                resultado += c + " ";
            } else {
                resultado += "_ ";
            }
        }
        return resultado.trim();
    }

    // retorna true quando todas as letras já foram acertadas (vitoria).
    public boolean verificarPalavraCompleta(List<Character> letrasCorretas) {
        for (int i = 0; i < texto.length(); i++) {
            if (!letrasCorretas.contains(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String getTexto() {
        return texto;
    }
}
