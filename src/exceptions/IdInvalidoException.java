package exceptions;

public class IdInvalidoException extends DadoInvalidoException {

	public IdInvalidoException(String msg) {
		super("Id nulo ou vazio." + msg);
	}
}
