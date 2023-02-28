package projeto.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import projeto.campoMinado.excessao.ExplosaoException;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	private final List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro() {
	}

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		criarCampos();
		interligarVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linhas, int colunas) {
		try {
		campos.parallelStream()
			.filter(c -> c.getLinha() == linhas && c.getColuna() == colunas)
			.findFirst().ifPresent(c -> c.abrir());
		}catch(ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void marcar(int linhas, int colunas) {
		campos.parallelStream()
			.filter(c -> c.getLinha() == linhas && c.getColuna() == colunas)
			.findFirst().ifPresent(c -> c.marcador());
	}

	private void criarCampos() {
		for (int l = 0; l < this.linhas; l++) {
			for (int c = 0; c < this.colunas; c++) {
				campos.add(new Campo(l, c));
			}
		}
	}
	
	private void interligarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado(); 
		
		do {
			int aleat = (int) (Math.random() * campos.size());
			campos.get(aleat).minarCampo();
			minasArmadas = campos.stream().filter(minado).count();
		}while(minasArmadas < this.minas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		this.sortearMinas();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("  ");
		for (int i = 0; i < this.colunas; i++) {
			sb.append("_");
			sb.append(i);
			sb.append("_");
		}
		
		sb.append("\n");
		
		int i = 0;
		for (int l = 0; l < this.linhas; l++) {
			sb.append(l);
			sb.append("-");
			for (int c = 0; c < this.colunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
