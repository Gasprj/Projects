package projeto.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	
	private final int linha;
	private final int coluna;
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	private List<CampoObserver> observadores = new ArrayList<CampoObserver>();
//	private List<BiConsumer<Campo, CampoEvento>> observadores = new ArrayList<>();
	
	public Campo (int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isMarcado() {
		return this.marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if(aberto)
			this.avisarObservadores(CampoEvento.ABRIR);
	}
	
	public boolean isAberto() {
		return this.aberto;
	}
	
	public boolean isMinado() {
		return this.minado;
	}
	
	public void registrarObservador(CampoObserver obs) {
		this.observadores.add(obs);
	}
	
	private void avisarObservadores(CampoEvento event) {
		this.observadores.stream().forEach(o -> o.eventoOcorreu(this, event));
	}
	
	public boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else
			return false;
	}
	
	public void marcador() {
		if(!aberto) {
			this.marcado = !this.marcado;
			
			if(this.marcado)
				this.avisarObservadores(CampoEvento.MARCAR);
			else
				this.avisarObservadores(CampoEvento.DESMARCAR);
		}
	}
	
	public boolean abrir() {
		if(!this.marcado && !this.aberto) {
			if(this.minado) {
				this.avisarObservadores(CampoEvento.EXPLODIR);
				return true;
			}
			
			this.setAberto(true);
			
			if(this.vizinhancaSegura())
				vizinhos.forEach(v -> v.abrir());
			
			return true;
		}else
			return false;
	}
	
	public boolean vizinhancaSegura() {
		return this.vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	public boolean minarCampo() {
		if(!this.minado) {
			this.minado = true;
			return true;
		}else
			return false;
	}
	
	public boolean objetivoAlcancado() {
		boolean revelado = !this.minado && this.aberto;
		boolean protegido = this.minado && this.marcado;
		
		return revelado || protegido;
	}
	
	public int minasAoRedor() {
		return (int) this.vizinhos.stream().filter(v -> v.minado).count();
	}
	
	public void reiniciar() {
		this.aberto = false;
		this.minado = false;
		this.marcado = false;
		this.avisarObservadores(CampoEvento.REINICIAR);
	}
	
}
