package mainpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JanelaForca extends JFrame {
	
	private String alfabeto[] = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D",
								"F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	private JButton keyboard[] = new JButton[26]; //array de botoes do swing
	
	private JLabel forcaImg = new JLabel(""); //imagem do boneco na forca
	JPanel pan_dica = new JPanel();
	private JLabel lbl_dica = new JLabel("Dica: "); //label para mostrar a dica
	private JLabel lbl_palavra = new JLabel(""); //label com * para ir mostrando a palavra
	
	private JLabel lbl_vitorias = new JLabel();
	private int vitorias = 0;
	private JLabel lbl_derrotas = new JLabel();
	private int derrotas = 0;
	
	private JLabel lbl_acertos = new JLabel();
	private JLabel lbl_erros = new JLabel();
	
	private Color blue_color = new Color(35, 165, 190);
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	private JButton btn_nova_palavra = new JButton("Nova Palavra");
	private JButton btn_adivinhar = new JButton("Adivinhar");
	
	private final int keyButtonWidth = 50; //largura em pixels do botão do teclado
	private final int keyButtonHeight = 35; //altura em pixels do botão do teclado
	private int eixoX = 30;
	private int eixoY = 210;
	
	public JanelaForca(JogoDaForca jog) {

		setTitle("Jogo da Forca POO - Tales Medeiros e Marlon Fernando");
		//setResizable(false);
		setDica(jog.getDica());
		trocarImg(0);
		forcaImg.setBounds(10, 35, 150, 150);
		getContentPane().add(forcaImg);
		
		pan_dica.setBounds(0, 0, 575, 30);
		pan_dica.add(lbl_dica);
		getContentPane().add(pan_dica);
		
		setLblPalavra(jog.getTamanho());
		lbl_palavra.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lbl_palavra.setBounds(180, 55, 350, 25);
		getContentPane().add(lbl_palavra);
		
		Color green = new Color(10, 195, 130);
		Color red = new Color(175, 10, 10);
		
		lbl_vitorias.setBounds(450, 115, 80, 20);
		lbl_vitorias.setForeground(green);
		lbl_vitorias.setText("Vitórias: " + vitorias);
		getContentPane().add(lbl_vitorias);
		
		lbl_derrotas.setBounds(450, 140, 80, 20);
		lbl_derrotas.setForeground(red);
		lbl_derrotas.setText("Derrotas: " + derrotas);
		getContentPane().add(lbl_derrotas);
		
		lbl_acertos.setBounds(280, 160, 100, 20);
		lbl_acertos.setForeground(green);
		lbl_acertos.setText("Acertos: " + jog.getAcertos() + "/" + jog.getTamanho());
		getContentPane().add(lbl_acertos);
		
		lbl_erros.setBounds(280, 180, 100, 30);
		lbl_erros.setForeground(red);
		lbl_erros.setText("Erros: " + jog.getErros() + "/" + jog.getMaxErros());
		getContentPane().add(lbl_erros);
		
		btn_nova_palavra.setBounds(420, 170, 120, 30);
		btn_nova_palavra.setEnabled(false);
		getContentPane().add(btn_nova_palavra);
		
		btn_adivinhar.setBounds(150, 170, 100, 30);
		getContentPane().add(btn_adivinhar);
		
		int i = 0;
		i = criarTeclado(i, 10, jog);
		
		eixoX = 31 + (keyButtonWidth/2);
		eixoY += keyButtonHeight + 1;		
		i = criarTeclado(i, 19, jog);
		
		eixoX = 32 + (keyButtonWidth+(keyButtonWidth/2));
		eixoY += keyButtonHeight + 1;	
		criarTeclado(i, 26, jog);
		
		btn_nova_palavra.setBorder(border);
		btn_nova_palavra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jog.inicializar();
				trocarImg(0);
				setDica(jog.getDica());
				setLblPalavra(jog.getTamanho());
				lbl_erros.setText("Erros: 0" + "/" + jog.getMaxErros());
				lbl_acertos.setText("Acertos: 0" + "/" + jog.getTamanho());
				habilitarTeclado(true);
				btn_nova_palavra.setEnabled(false);
				btn_adivinhar.setEnabled(true);
			}
		});
		
		btn_adivinhar.setBorder(border);
		btn_adivinhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String palpite = JOptionPane.showInputDialog(rootPane, "Digite o palpite: ");
				if(palpite == null)
					return;
				palpite = palpite.toUpperCase();
				if(!jog.advinhar(palpite)) {
					lbl_erros.setText("Erros: " + jog.getErros() + "/" + jog.getMaxErros());
					perdeu();
				}else {
					getLblPalavra().setText(jog.getPalavra());
					lbl_acertos.setText("Acertos: " + jog.getAcertos() + "/" + jog.getTamanho());
					ganhou();
				}
			}
		});
	}
	
	private int criarTeclado(int i, int qtdLinha, JogoDaForca jog) {
		
		for(; i<qtdLinha; i++) {
			keyboard[i] = new JButton(alfabeto[i]);
			keyboard[i].setBounds(eixoX, eixoY, keyButtonWidth, keyButtonHeight);
			keyboard[i].setBackground(blue_color);
			keyboard[i].setForeground(Color.WHITE);
			keyboard[i].setBorder(border);
			keyboard[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JButton botao = (JButton) arg0.getSource();
					botao.setEnabled(false);
					botao.setBackground(null);
					String palpite = botao.getText();
					int[] indices = jog.jogar(palpite);
					if(indices != null) { //se acertou pelo menos uma letra
						lbl_acertos.setText("Acertos: " + jog.getAcertos() + "/" + jog.getTamanho());
						String str = getLblPalavra().getText();
						String array[] = tiraEspacos(str);
						//int j=0;
						
						for(int i=0; i<indices.length; i++)
							//j = indices[i];
							array[indices[i]] = palpite;
						
						str = String.join(" ", array);
						getLblPalavra().setText(str);
						
						if(jog.getAcertos() == jog.getTamanho()) //se ganhou o jogo
							ganhou();
					}else {
						lbl_erros.setText("Erros: " + jog.getErros() + "/" + jog.getMaxErros());
						trocarImg(jog.getErros());
						if(jog.getErros() == jog.getMaxErros()) //se perdeu o jogo
							perdeu();
					}
				}
			});
			this.getContentPane().add(keyboard[i]);
			eixoX += keyButtonWidth + 1;
		}
		return i;
	}
	
	private void ganhou() {
		vitorias++;
		lbl_vitorias.setText("Vitórias: " + vitorias);
		habilitarTeclado(false);
		btn_nova_palavra.setEnabled(true);
		btn_adivinhar.setEnabled(false);
		JOptionPane.showMessageDialog(rootPane, "Você Ganhou!");
	}
	
	private void perdeu() {
		derrotas++;
		lbl_derrotas.setText("Derrotas: " + derrotas);
		habilitarTeclado(false);
		btn_nova_palavra.setEnabled(true);
		btn_adivinhar.setEnabled(false);
		JOptionPane.showMessageDialog(rootPane, "Você Perdeu!");
	}
	
	private String[] tiraEspacos(String s) {
		// pega a string s, tira os espacos dela e retorna
		// um array com os caracteres que sobraram
		String novo[], letra;
		int j=0, tam=0;
		
		for(int i=0; i<s.length(); i++) {
			letra = s.substring(i, i+1);
			if(!letra.equals(" "))
				tam++;
		}
		
		novo = new String[tam];
		
		for(int i=0; i<s.length(); i++) {
			letra = s.substring(i, i+1);
			if(!letra.equals(" "))
				novo[j++] = letra;
		}
		return novo;
	}
	
	private JLabel getLblPalavra() {
		return lbl_palavra;
	}
	
	private void setLblPalavra(int tam) {
		String s = "";
		for(int i=0; i<tam; i++)
			s += "* ";
		lbl_palavra.setText(s);
	}
	
	private void setDica(String dica) {
		lbl_dica.setText("Dica: " + dica);
	}
	
	private void trocarImg(int erros) { //esse parâmetro tem que ser um numero de 0 a 6
		if(erros == 0)
			forcaImg.setIcon(new ImageIcon(Janela.class.getResource("/imgs/forca.png")));
		else
			forcaImg.setIcon(new ImageIcon(Janela.class.getResource("/imgs/"+erros+".png")));
	}
	
	private void habilitarTeclado(boolean x) {
		for(int i=0; i<26; i++) {
			if(x) {
				keyboard[i].setBackground(blue_color);
				keyboard[i].setEnabled(true);
			}else {
				keyboard[i].setBackground(null);
				keyboard[i].setEnabled(false);	
			}
		}
	}

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
}
