package mainpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class JogoDaForca {
	
	private String[] palavras;
	private String palavraAtual;
	private String[] dicas; // um  array  com  as  n  dicas  (lidas  do  arquivo)
	private int n; // quantidade de palavras lidas do arquivo
	private int sorteio = -1; // �ndice  da  palavra/dica  sorteada  do  jogo
	private int acertos; // total  de  acertos  do  jogo
	private int erros; // total de erros do jogo
	private final int maxErros = 6; //numero maximo de erros (se chegar a 6, perdeu)
	private boolean isPrimeiroSorteio = true;
	
	public JogoDaForca(String nomearquivo) {
		// construtor  que  l�  as  palavras  e  dicas  do  arquivo  texto  e  as
		//coloca  nos  respectivos  arrays  (ver  formato  do  arquivo  em  anexo1).
		try {
			
			Scanner arq = new Scanner(new File(nomearquivo));
			String linha;
			this.n = Integer.parseInt(arq.nextLine()); //quantidade de palavras/dicas
			this.palavras = new String[n]; //definindo o tamanho dos arrays
			this.dicas = new String[n];
			
			for(int i=0; arq.hasNextLine(); i++) {
				linha = arq.nextLine(); //pega a linha
				palavras[i] = linha.split(";")[0]; //pega a palavra
				dicas[i] = linha.split(";")[1]; //pega a dica
			}
			arq.close();
			
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo n�o Encontrado.");
			System.exit(0);
		}
	}
	
	public void inicializar() {
		// sorteia  uma  palavra  (e  dica)  dentre  as  n  existentes
		//e zera  os  acertos  e  erros.
		
		if(isPrimeiroSorteio) {
			isPrimeiroSorteio = false;
		}else {
			palavras[sorteio] = palavraAtual;
		}
		
		sorteio = (int) (Math.random() *n);
		palavraAtual = palavras[sorteio];
		acertos = erros = 0;
	}
	
	public int[] jogar(String palpite) {
		
		String palavra = palavras[sorteio]; //pegando a palavra sorteada
		//DETALHE IMPORTANTE!!!
		//A vari�vel palavra N�O est� recebendo uma c�pia do conte�do de palavras[sorteio].
		//Ela est� apenas apontando pro mesmo lugar que palavras[sorteio].
		//Ou seja, se o conte�do de palavra for alterado, o conte�do de palavras[sorteio] tamb�m ser�.
		//Mas isso s� acontece com objetos.
		String letra;
		
		if(palavra.contains(palpite)) {
			int i, j = 0, ocorrencias = 0; //ocorrencias da letra na palavra
			
			for(i = 0; i<palavra.length(); i++) {
				letra = palavra.substring(i, i+1); //pegando letra por letra
				if(letra.equals(palpite)) //se a letra atual � igual ao palpite
					ocorrencias++;
			}

			int[] indices = new int[ocorrencias]; 
			//array para retornar os indices da palavra em que
			//ouve ocorrencia do palpite
			
			for(i=0; i<palavra.length(); i++) {
				letra = palavra.substring(i, i+1); //pegando letra por letra
				if(letra.equals(palpite))
					indices[j++] = i;
			}
			
			palavra = palavra.replaceAll(palpite, "#"); //substituindo a letra por #
			//o replaceAll cria uma new String e retorna
			palavras[sorteio] = palavra;
			acertos += ocorrencias;
			return indices;
		}else {
			erros++;
			return null;
		}
		//verifica  se  esta  letra  ocorre  dentro  da  palavra  sorteada.  Em  caso  afirmativo, 
		//altera  as  ocorr�ncias  da  letra  na  palavra  para  �#�  (anexo2)  e  retorna  os
		//�ndices  dessas  ocorr�ncias.  Do  contr�rio,  retorna  null.  Al�m  disso,  o  m�todo
		//contabiliza  um  acerto  no  jogo  para  cada  ocorr�ncia  da  letra  encontrada  na
		//palavra  e  contabiliza  um  erro  para  a  inexist�ncia  da  letra.
	}
	
	public boolean advinhar(String palavra) {
		// verifica  se  a  palavra  �  igual  a  sorteada.  Em  caso  afirmativo,  retorna  true
		//e  do  contr�rio,  retorna  false.  Al�m  disso,  o  m�todo  contabiliza  o  m�ximo  de
		//acertos  no  jogo  (caso  true)  ou  contabiliza  o  m�ximo  de  erros  (caso  false).
		if(palavra.equals(palavraAtual)) {
			acertos = palavraAtual.length();
			return true;
		}else {
			erros = maxErros;
			return false;
		}
	}
	
	public int getTamanho() {
		//retorna o tamanho da palavra sorteada
		return palavras[sorteio].length();
	}
	
	public int getAcertos() {
		//retorna  o  total  de  acertos  at�  o  momento
		return acertos;
	}
	
	public int getErros() {
		//retorna  o  total  de  erros  at�  o  momento
		return erros;
	}
	
	public String getDica() {
		//retorna  a  dica  associada  �  palavra  sorteada.
		return dicas[sorteio];
	}
	
	public int getMaxErros() {
		return this.maxErros;
	}
	
	public String getPalavra() {
		return palavraAtual;
	}

}
