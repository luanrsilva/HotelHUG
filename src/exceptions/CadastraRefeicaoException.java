package exceptions;

public class CadastraRefeicaoException extends CadastroException {

	public CadastraRefeicaoException(String msg) {
		super("Erro no cadastro de refeicao completa. " + msg);
	}

}
