package projeto.campoMinado.modelo;

public class ResultadoJogo {
	
	private final boolean ganhou;

	public ResultadoJogo(boolean ganhou) {
		this.ganhou = ganhou;
	}

	public boolean isGanhou() {
		return ganhou;
	}
	
}
