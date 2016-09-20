package exceptions;

public class CheckoutException extends Exception{
	
	public CheckoutException(String msg) {
		super("Erro ao realizar checkout. " + msg);
	}
}
