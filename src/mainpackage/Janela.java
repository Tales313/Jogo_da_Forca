package mainpackage;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Janela {

	private JogoDaForca jogo;
	private JanelaForca frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String arq = JOptionPane.showInputDialog("Digite o nome do arquivo (sem extensão)");
		if(arq == null) {
			JOptionPane.showMessageDialog(frame, "Arquivo não encontrado");
			System.exit(0);
		}			
		
		jogo = new JogoDaForca(arq+".txt");
		jogo.inicializar();
		frame = new JanelaForca(jogo);
		
		frame.setBounds(100, 100, 575, 370);
		frame.setDefaultCloseOperation(JanelaForca.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	}
}
