package projeto.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;

import projeto.campoMinado.excessao.ExplosaoException;

public class Campo {
	
	private final int linha;
	private final int coluna;
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	
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
	}
	
	public boolean isAberto() {
		return this.aberto;
	}
	
	public boolean isMinado() {
		return this.minado;
	}
	
	public boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunaDiferente = this.coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		
//		if(deltaGeral == 1 && !diagonal) {
		if(deltaGeral == 1 && (linhaDiferente ^ colunaDiferente)) {
			this.vizinhos.add(vizinho);
			return true;
		}else if(deltaGeral == 2 && diagonal) {
			this.vizinhos.add(vizinho);
			return true;
		}else
			return false;
	}
	
	public void marcador() {
		if(!aberto) {
			this.marcado = !this.marcado;
		}
	}
	
	public boolean abrir() {
		if(!this.marcado && !this.aberto) {
			this.aberto = true;
			
			if(this.minado)
				throw new ExplosaoException();
			
			if(this.vizinhancaSegura())
				vizinhos.forEach(v -> v.abrir());
			
			return true;
		}else
			return false;
	}
	
	public boolean vizinhancaSegura() {
		return this.vizinhos.stream().noneMatch(v -> v.minado) && this.vizinhos.stream().noneMatch(v -> v.marcado);
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
		boolean naoAcionado = this.minado && !this.aberto;
		
		return revelado || protegido || naoAcionado;
	}
	
	public long minasAoRedor() {
		return this.vizinhos.stream().filter(v -> v.minado).count();
	}
	
	public void reiniciar() {
		this.aberto = false;
		this.minado = false;
		this.marcado = false;
	}
	
	@Override
	public String toString() {
		if(this.marcado)
			return "X";
		else if(this.aberto && this.minado)
			return "*";
		else if(this.aberto && this.minasAoRedor() > 0)
			return Long.toString(this.minasAoRedor());
		else if(this.aberto)
			return " ";
		else
			return "?";
	}
	
}
