package exceptions;

public class AtualizacaoHospedeException extends Exception {
	
	public AtualizacaoHospedeException(String msg) {
		super("Erro na atualizacao do cadastro de Hospede. " + msg);
	}
}
