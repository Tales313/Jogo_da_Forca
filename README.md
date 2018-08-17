# Jogo da Forca
Primeiro projeto da disciplina de Programação Orientada a Objetos (Sistemas para Internet, IFPB) feito em 2018.1.

## Descrição
Todo o texto a seguir foi escrito pelo professor da disciplina, Fausto Ayres. O Jogo da Forca é um jogo em que o jogador tem que acertar qual é a palavra proposta, tendo como dica o tema ligado à palavra. Para começar o jogo se exibe todas as letras da palavra com um asterisco. O jogador digita cada letra que pode existir na palavra. Quando ele acerta, a letra é revelada no lugar do * e quando ele erra, é exibida uma parte do corpo do enforcado (iniciando pela cabeça, tronco, braços…). O jogador pode optar a qualquer momento por adivinhar a palavra inteira. Neste caso, se errar ele perde o jogo na hora. O jogo termina com o acerto de todas as letras da palavra ou com o enforcamento (seis erros). Implementação em Java:

1) Crie a classe JogoDaForca, responsável pela lógica do jogo, contendo no mínimo os atributos
```
private String[] palavras; // um array com as n palavras (lidas do arquivo)
private String[] dicas; // um array com as n dicas (lidas do arquivo)
private int n; // quantidade de palavras do arquivo (lido do arquivo)
private int sorteio = -1; // índice da palavra sorteada do jogo
private int acertos; // total de acertos do jogo
private int erros; // total de erros do jogo
```
e os métodos
```
public JogoDaForca(String nomearquivo) // construtor que lê as palavras e dicas do arquivo texto e as coloca nos respectivos arrays (ver formato do arquivo em anexo1).
public void inicializar() // sorteia uma palavra (e dica) dentre as n existentes e zera os acertos e erros.
public int[] jogar(String letra) // verifica se esta letra ocorre dentro da palavra sorteada. Em caso afirmativo, altera as ocorrências da letra na palavra para “#“ e retorna os índices dessas ocorrências. Do contrário, retorna null. Além disso, o método contabiliza um acerto no jogo para cada ocorrência da letra encontrada na palavra e contabiliza um erro para a inexistência da letra.
public boolean advinhar(String palavra) // verifica se a palavra é igual a sorteada. Em caso afirmativo, retorna true e do contrário, retorna false. Além disso, o método contabiliza o máximo de acertos no jogo (caso true) ou contabiliza o máximo de erros (caso false).
public int getTamanho() // retorna o tamanho da palavra sorteada
public int getAcertos() // retorna o total de acertos até o momento
public int getErros() // retorna o total de erros até o momento
public String getDica() // retorna a dica associada à palavra sorteada.
```
Obs: esta classe não poderá ler nenhuma informação do teclado nem escrever na console!
Crie a aplicação JogoDaForcaSwing para instanciar um objeto da classe JogoDaForca e chamar seus métodos de acordo com os eventos da tela:
- inicializar/reinicializar o jogo
- digitar uma letra
- digitar uma palavra

A aplicação deve mostrar na tela os caracteres ocultados (com *) ou revelados da palavra que está sendo jogada, a dica, o total de acertos e de erros e as imagens do enforcado.

O arquivo a ser utilizado no JogoDaForca contém a quantidade palavras do arquivo na primeira linha e as palavras/dicas nas linhas seguintes, como exemplificado abaixo. Essas palavras possuem diferentes tamanhos (entre 6 e 11), mas todas estão em maiúsculas e sem acentuação. Cada palavra está separada da sua dica por um “;”.
Exemplo de arquivo:
```
2
COMPUTADOR;composto por hardware e software
OBJETO;é uma instância da classe
```

## Screenshots

![Screenshot 1](/screenshots/1.png)
![Screenshot 2](/screenshots/2.png)
![Screenshot 3](/screenshots/3.png)
![Screenshot 4](/screenshots/4.png)
