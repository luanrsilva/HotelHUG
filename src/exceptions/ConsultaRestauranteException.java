package exceptions;

public class ConsultaRestauranteException extends Exception {
	public ConsultaRestauranteException(String msg) {
		super("Erro na consulta do restaurante. " + msg);
	}
}
