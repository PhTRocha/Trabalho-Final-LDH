# Manual do Usuário — Jogo da Forca

**Disciplina:** 36811 – Modelagem e Programação de Sistemas Computacionais
**Curso:** Engenharia da Informação — UNOESC Chapecó

> Espaço para a equipe: preencher com o nome de todos os integrantes antes da entrega.
>
> - Integrante 1: ______________________________
> - Integrante 2: ______________________________
> - Integrante 3: ______________________________

---

## 1. Sobre o sistema

O **Jogo da Forca** é uma aplicação de linha de comando (CLI) desenvolvida em Java. O objetivo do jogador é descobrir uma palavra secreta, sorteada aleatoriamente de uma lista, adivinhando uma letra de cada vez antes que as tentativas se esgotem.

O sistema foi construído seguindo os princípios de Programação Orientada a Objetos e é composto por quatro classes principais:

- **Jogo** — controla as regras e o estado da partida (motor do jogo).
- **Jogador** — guarda o nome e o histórico de letras já tentadas.
- **Palavra** — representa a palavra secreta e verifica acertos.
- **JogoDaForca** — interface de linha de comando e ponto de entrada do programa.

A lista de palavras fica em um arquivo de texto (`palavras.txt`), o que caracteriza a persistência de dados em arquivo.

---

## 2. Requisitos

- **Java JDK 11 ou superior** instalado (o projeto foi testado com o JDK 21).
- **IntelliJ IDEA** (Community ou Ultimate) ou qualquer ambiente capaz de compilar e executar Java.
- Um terminal/console com suporte a UTF-8 (já é o padrão do IntelliJ).

---

## 3. Estrutura dos arquivos

```
Trabalho-Final-LDH/
├── palavras.txt          (lista de palavras — fica na RAIZ do projeto)
└── src/
    ├── JogoDaForca.java   (interface CLI / classe com o main)
    ├── Jogo.java          (motor do jogo)
    ├── Jogador.java
    └── Palavra.java
```

> **Importante:** o arquivo `palavras.txt` deve ficar na **raiz do projeto**, e não dentro da pasta `src`. O programa o lê pelo caminho relativo `"palavras.txt"`, e o IntelliJ usa a raiz do projeto como diretório de trabalho ao executar.

---

## 4. Como executar no IntelliJ IDEA

1. Abra o IntelliJ e selecione **Open**, escolhendo a pasta do projeto.
2. Verifique se a pasta `src` está marcada como *Sources Root* (clique com o botão direito sobre ela → **Mark Directory as → Sources Root**, caso não esteja).
3. Confirme que o `palavras.txt` está na raiz do projeto.
4. Abra o arquivo `JogoDaForca.java`.
5. Clique no botão verde de **Run** (▶) ao lado do método `main`, ou pressione **Shift + F10**.
6. O jogo será iniciado no painel de console do IntelliJ, onde você digita as letras.

---

## 5. Como jogar (passo a passo)

### 5.1. Início da partida

Ao iniciar, o programa pede o nome do jogador:

```
==================================================
              JOGO DA FORCA - UNOESC
==================================================
Digite o nome do jogador: Maria

Jogo iniciado! Você tem 6 tentativas. Boa sorte, Maria!
```

Em seguida, o estado do jogo é exibido.

### 5.2. Entendendo a tela de estado

A cada rodada, o sistema mostra um painel com todas as informações da partida:

```
==================================================
Jogador: Maria
Tamanho da palavra: 9 letra(s)
Palavra: _ _ _ _ _ _ _ _ _
Letras incorretas: (nenhuma)
Tentativas usadas: 0 de 6
Tentativas restantes: 6
==================================================
```

| Linha | Significado |
|-------|-------------|
| **Jogador** | Nome informado no início. |
| **Tamanho da palavra** | Quantidade de letras da palavra secreta. |
| **Palavra** | A palavra com as letras já acertadas reveladas; `_` indica posições ainda não descobertas. |
| **Letras incorretas** | Letras já tentadas que **não** existem na palavra. |
| **Tentativas usadas** | Quantos erros já foram cometidos. |
| **Tentativas restantes** | Quantos erros ainda são permitidos antes de perder. |

### 5.3. Fazendo uma adivinhação

Digite **uma única letra** e pressione Enter:

- **Acerto:** a letra é revelada na palavra; o número de tentativas **não** diminui.
  ```
  Digite uma letra: a
  >> Boa! A letra 'A' está na palavra.
  ```
- **Erro:** a letra entra na lista de incorretas e você perde uma tentativa.
  ```
  Digite uma letra: i
  >> A letra 'I' não está na palavra. Você perdeu uma tentativa.
  ```
- **Letra repetida:** se você digitar uma letra que já tentou, o sistema apenas avisa e pede outra, **sem** descontar tentativa.
  ```
  Digite uma letra: a
  >> A letra 'A' já foi tentada. Escolha outra.
  ```
- **Entrada inválida:** se você digitar mais de uma letra, um número ou nada, o sistema pede novamente.
  ```
  Digite uma letra: 12
  >> Entrada inválida. Digite apenas UMA letra.
  ```

> Não é preciso se preocupar com maiúsculas/minúsculas ou acentos: o sistema converte tudo automaticamente (ex.: `a` é igual a `A`).

### 5.4. Fim da partida

A partida termina em duas situações:

- **Vitória** — todas as letras da palavra foram descobertas:
  ```
  *** PARABÉNS, MARIA! VOCÊ VENCEU! ***
  A palavra era: BORBOLETA
  ```
- **Derrota** — as tentativas se esgotaram:
  ```
  *** QUE PENA, JOÃO! VOCÊ PERDEU! ***
  A palavra era: INCRIVEL
  ```

### 5.5. Jogar novamente ou sair

Ao final, o sistema oferece a opção de recomeçar:

```
Deseja jogar novamente? (S/N): S
--- Novo jogo iniciado! Boa sorte, Maria! ---
```

- Digite **S** para iniciar uma nova partida com uma nova palavra sorteada (mantendo o mesmo jogador).
- Digite **N** para encerrar:
  ```
  Obrigado por jogar, Maria! Até a próxima.
  ```

---

## 6. Personalizando a lista de palavras

A lista de palavras pode ser editada livremente. Basta abrir o arquivo `palavras.txt` e escrever **uma palavra por linha**. Por exemplo:

```
ABACAXIS
BORBOLETA
TECNOLOGIA
```

Na próxima execução, o sistema passará a sortear também as novas palavras. Não é necessário alterar o código.

---

## 7. Solução de problemas

| Problema | Causa provável | Solução |
|----------|----------------|---------|
| `Erro: Nenhuma palavra carregada` | O arquivo `palavras.txt` não foi encontrado ou está vazio. | Confirme que o arquivo está na raiz do projeto e contém ao menos uma palavra. |
| Acentos aparecem como `?` no console | Codificação do console não está em UTF-8. | No IntelliJ: **Help → Edit Custom VM Options** e adicione a linha `-Dfile.encoding=UTF-8`, depois reinicie. |
| O programa não acha o `main` | A pasta `src` não está marcada como *Sources Root*. | Botão direito na pasta `src` → **Mark Directory as → Sources Root**. |

---

## 8. Regras resumidas

1. O sistema sorteia uma palavra aleatória da lista.
2. O jogador tem **6 tentativas** (erros permitidos).
3. Cada letra correta é revelada; cada letra errada custa uma tentativa.
4. Letras repetidas são ignoradas e não custam tentativa.
5. Vence quem completar a palavra antes de esgotar as tentativas.
6. Ao final, é possível jogar de novo ou sair.

Bom jogo!
