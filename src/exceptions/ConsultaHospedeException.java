package exceptions;

public class ConsultaHospedeException extends Exception {
	
	public ConsultaHospedeException(String msg) {
		super("Erro na consulta de hospede. " + msg);
	}
}
