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

	@Override
	public int compareTo(Refeicao o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String FIM_DE_LINHA = System.lineSeparator();
		sb.append("Nome: " + this.getNome() + " Preco: " + this.formataValor(this.calculaPreco()) + FIM_DE_LINHA
		+ "Descricao: " + this.getDescricao() + FIM_DE_LINHA);
		
		return sb.toString();
	}

}
