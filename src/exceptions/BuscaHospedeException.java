package exceptions;

public class BuscaHospedeException extends ConsultaHospedeException {

	public BuscaHospedeException(String email) {
		super("Hospede de email " + email + " nao foi cadastrado(a).");
	}

}
