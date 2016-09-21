package restaurante;

import exceptions.StringInvalidaException;

/**
 * Classe que representa uma abstracao de um prato.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Prato extends Refeicao {

	private double preco;

	public Prato(String nome, double preco, String descricao) throws StringInvalidaException {
		super(nome, descricao);
		this.preco = preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public double calculaPreco() {
		return this.preco;
	}

}
