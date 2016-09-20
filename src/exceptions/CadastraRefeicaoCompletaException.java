package exceptions;

public class CadastraRefeicaoCompletaException extends Exception {
	
	public CadastraRefeicaoCompletaException(String msg) {
		super("Erro no cadastro de refeicao completa. " + msg);
	}

}
