package restaurante;

import exceptions.StringInvalidaException;

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
