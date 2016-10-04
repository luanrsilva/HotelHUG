package restaurante;

import exceptions.StringInvalidaException;
import util.ValidacaoDeDados;


/**
 * Classe que representa uma abstracao de uma refeicao.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public abstract class Refeicao implements Comparable<Refeicao>{

	private String nome;
	private String descricao;

	public Refeicao(String nome, String descricao) throws StringInvalidaException {
		verificaNome(nome);
		verificaDescricao(descricao);
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * Metodo que verifica se a descricao do prato passada eh valida, se nao for, lanca uma excessao.
	 * @param descricao
	 * @throws StringInvalidaException
	 */
	private void verificaDescricao(String descricao) throws StringInvalidaException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new StringInvalidaException("Descricao do prato esta vazia.");
		}
	}

	/**
	 * Metodo que verifica se o nome do prato passado eh valido, se nao for, lanca uma excessao.
	 * @param nome
	 * @throws StringInvalidaException
	 */
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
	
	/**
	 * Este metodo calcula o preco de todos os pratos inclusos na refeicao.
	 * @return Retorna um double com o preco.
	 */
	public abstract double calculaPreco();
	

}
