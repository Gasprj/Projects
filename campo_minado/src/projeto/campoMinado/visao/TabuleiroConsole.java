package projeto.campoMinado.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import projeto.campoMinado.excessao.ExplosaoException;
import projeto.campoMinado.excessao.SairException;
import projeto.campoMinado.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tab;
	private Scanner ent = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tab) {
		this.tab = tab;
		
		this.executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				loopJogo();
				
				System.out.println("Nova partida? s/n ");
				String resposta = ent.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) 
					continuar = false;
				else
					this.tab.reiniciar();
			}
		}catch(SairException e){
			
		}finally {
			System.out.println("Até breve!");
			ent.close();
		}
	}
/*
 * ".split()" => Transforma a String em Array.
 * ".trim()" => Retira os espaços vazios.
*/	private void loopJogo() {
		try {
			while(!this.tab.objetivoAlcancado()) {
				System.out.println(this.tab);
				String digit = this.pegarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digit.split(","))
					.map(e ->Integer.parseInt(e.trim())).iterator();
				
				digit = this.pegarValorDigitado("1 - Abrir \n2 - (Des)Marcar: ");
				
				if("1".equals(digit))
					this.tab.abrir(xy.next(), xy.next());
				else if("2".equals(digit))
					this.tab.marcar(xy.next(), xy.next());
			}
			
			System.out.println(this.tab);
			System.out.println("Vitória!");
		}catch(ExplosaoException e) {
			System.out.println(this.tab);
			System.out.println("Derrota...");
		}
	}
	
	private String pegarValorDigitado(String text) {
		System.out.print(text);
		String digit = ent.nextLine();
		
		if("sair".equalsIgnoreCase(digit))
			throw new SairException();
		
		return digit;
	}

}
