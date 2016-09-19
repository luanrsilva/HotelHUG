package restaurante;

import exceptions.StringInvalidaException;
import util.ValidacaoDeDados;

public abstract class Refeicao {

	private String nome;
	private String descricao;

	public Refeicao(String nome, String descricao) throws StringInvalidaException {
		verificaNome(nome);
		verificaDescricao(descricao);
		this.nome = nome;
		this.descricao = descricao;
	}

	private void verificaDescricao(String descricao) throws StringInvalidaException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new StringInvalidaException("Descricao do prato esta vazia.");
		}
	}

	private void verificaNome(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome do prato esta vazio.");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws StringInvalidaException {
		verificaNome(nome);
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws StringInvalidaException {
		verificaDescricao(descricao);
		this.descricao = descricao;
	}

	public abstract double calculaPreco();

}
