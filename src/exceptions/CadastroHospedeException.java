package exceptions;

public class CadastroHospedeException extends Exception {

	public CadastroHospedeException(String msg) {
		super("Erro no cadastro de Hospede. " + msg);
	}

}
