package exceptions;

public class ChecarHospedagemException extends Exception{
	
	public ChecarHospedagemException(String msg) {
		super("Erro ao checar hospedagem ativa. " + msg);
	}
}
