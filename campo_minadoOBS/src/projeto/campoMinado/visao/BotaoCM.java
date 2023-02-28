package projeto.campoMinado.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import projeto.campoMinado.modelo.Campo;
import projeto.campoMinado.modelo.CampoEvento;
import projeto.campoMinado.modelo.CampoObserver;

@SuppressWarnings("serial")
public class BotaoCM extends JButton implements CampoObserver, MeuMouse{

	private final Color PADRAO = new Color(184, 184, 184);
	private final Color MARCAR = new Color(8, 179, 247);
	private final Color EXPLODIR = new Color(189, 66, 68);
	private final Color VERDE = new Color(30, 130, 30);
	private final Color AMARELO = new Color(255, 255, 100);
	private final Color LARANJA = new Color(200, 150, 30);
	private final Color ROSA = new Color(205, 125, 125);
	
	private Campo campo;
	
	public BotaoCM(Campo campo) {
		this.campo = campo;
		setBackground(PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	public void eventoOcorreu(Campo camp, CampoEvento event) {
		switch(event) {
		case ABRIR:
			visualizacaoAbrir();
			break;
		case MARCAR:
			visualizacaoMarcar();
			break;
		case EXPLODIR:
			visualizacaoExplodir();
			break;
		default:
			visualizacaoPadrao();
		}
//	Força a repintura. Usar se der erro ou para garantir o funcionameno correto.
//	Não é obrigatório se estiver funcionando normalmente.		
//		SwingUtilities.invokeLater(() -> {
//			repaint();
//			validate();
//		});
	}

	private void visualizacaoPadrao() {
		setBackground(PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void visualizacaoExplodir() {
		setBackground(EXPLODIR);
		setForeground(Color.BLACK);
		setText("X");
	}

	private void visualizacaoMarcar() {
		setBackground(MARCAR);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void visualizacaoAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(this.campo.isMinado()) {
	//		setBackground(EXPLODIR);
			setForeground(Color.BLACK);
			setText("x");
			return;
		}
		
		setBackground(PADRAO);
		
		switch (campo.minasAoRedor()) {
		case 1: 
			setForeground(Color.BLUE);
			break;
		case 2: 
			setForeground(VERDE);
			break;
		case 3: 
			setForeground(AMARELO);
			break;
		case 4: 
			setForeground(LARANJA);
			break;
		case 5:
			setForeground(Color.BLACK);
			break;
		case 6:
			setForeground(ROSA);
			break;
		default:
			setForeground(Color.RED);
			
		}
		String valor = !this.campo.vizinhancaSegura() ? this.campo.minasAoRedor() + "" : "";
		setText(valor);
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)
			this.campo.abrir();
		else
			this.campo.marcador();
	}

}
