package exceptions;

public class CadastraPratoException extends CadastroException {

	public CadastraPratoException(String msg) {
		super("Erro no cadastro do prato. " + msg);
	}

}
