package projeto.campoMinado.visao;

import javax.swing.JFrame;

import projeto.campoMinado.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal() {
		Tabuleiro tab = new Tabuleiro(16, 30, 60);
		add(new PainelTabuleiro(tab));
		
		setTitle("Campo Minado");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new TelaPrincipal();
		
	}

}
