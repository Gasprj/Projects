package projeto.campoMinado.excessao;

public class ExplosaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "BOOOMMMMM!!!";
	}

}
