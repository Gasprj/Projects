package projeto.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class Tabuleiro implements CampoObserver{
	
	private final int linhas;
	private final int colunas;
	private final int minas;
	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<ResultadoJogo>> observadores = new ArrayList<>();
	
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		criarCampos();
		interligarVizinhos();
		sortearMinas();
	}
	
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public int getMinas() {
		return minas;
	}
	
	public void paraCada(Consumer<Campo> funcao) {
		this.campos.forEach(funcao);
	}
	
	public void registrarObservador(Consumer<ResultadoJogo> observ) {
		this.observadores.add(observ);
	}
	
	public void avisarObservadores(boolean result) {
		this.observadores.stream().forEach(o -> o.accept(new ResultadoJogo(result)));
	}
	
	public void abrir(int linhas, int colunas) {
		campos.parallelStream()
			.filter(c -> c.getLinha() == linhas && c.getColuna() == colunas)
			.findFirst().ifPresent(c -> c.abrir());
	}
	
	
	public void marcar(int linhas, int colunas) {
		campos.parallelStream()
			.filter(c -> c.getLinha() == linhas && c.getColuna() == colunas)
			.findFirst().ifPresent(c -> c.marcador());
	}

	private void criarCampos() {
		for (int l = 0; l < this.linhas; l++) {
			for (int c = 0; c < this.colunas; c++) {
				Campo campo = new Campo(l, c);
				campo.registrarObservador(this);
				campos.add(campo);
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

	public void eventoOcorreu(Campo camp, CampoEvento event) {
		if(event == CampoEvento.EXPLODIR) {
			this.exibirMinas();
			this.avisarObservadores(false);
		}else if(this.objetivoAlcancado()) {
			this.avisarObservadores(true);
		}
	}
	
	private void exibirMinas() {
		campos.stream()
		.filter(c -> c.isMinado())
		.filter(c -> !c.isMarcado())
		.forEach(c -> c.setAberto(true));
	}
	
}
