package exceptions;

public class BuscaHospedeException extends Exception {
	private static final long serialVersionUID = 1L;

	public BuscaHospedeException(String email) {
		super("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
	}

}
