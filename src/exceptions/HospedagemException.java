package exceptions;

public class HospedagemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HospedagemException(String msg){
		super("Erro na consulta de hospedagem. Hospede " + msg + " nao esta hospedado(a).");
	}

}
