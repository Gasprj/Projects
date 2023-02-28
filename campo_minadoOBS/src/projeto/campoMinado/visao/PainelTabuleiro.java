package projeto.campoMinado.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projeto.campoMinado.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel{

	public PainelTabuleiro(Tabuleiro tab) {
		setLayout(new GridLayout(tab.getLinhas(), tab.getColunas()));
		
		tab.paraCada(c -> add(new BotaoCM(c)));
		
		tab.registrarObservador(e -> {
		// Atrasa a execussão. Permite que o resto do algoritimo execute primeiro.
		// Recebe uma interface "Runable".
			SwingUtilities.invokeLater(() -> {
				if(e.isGanhou()) 
					JOptionPane.showMessageDialog(this, "Ganhou!!!  :)");
				else
					JOptionPane.showMessageDialog(this, "Perdeu...  :(");
				
				tab.reiniciar();
			});
		});
		
//		int total = tab.getLinhas() * tab.getColunas();
//		
//		for (int i = 0; i < total; i++) {
//			add(new BotaoCM(null));
//		}
	}

}
