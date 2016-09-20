package exceptions;

public class ConsultaException extends Exception {
	
	public ConsultaException(String email) {
		super("Hospede de email " + email + " nao foi cadastrado(a).");
	}

}
