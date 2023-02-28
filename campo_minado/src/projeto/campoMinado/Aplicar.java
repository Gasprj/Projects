package projeto.campoMinado;

import projeto.campoMinado.modelo.Tabuleiro;
import projeto.campoMinado.visao.TabuleiroConsole;

public class Aplicar {
	
	public static void main(String[] args) {
		
		Tabuleiro tab = new Tabuleiro(6, 6, 4);
		new TabuleiroConsole(tab);
		
	}

}
