package exceptions;

public class CheckinException extends Exception{
	
	public CheckinException(String msg) {
		super("Erro ao realizar checkin. " + msg);
	}
}
