package exceptions;

public class RemocaoHospedeException extends Exception {

	public RemocaoHospedeException(String msg) {
		super("Erro na remocao do Hospede. " + msg);
	}
}
