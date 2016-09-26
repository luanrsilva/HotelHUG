package restaurante;

import java.util.ArrayList;
import java.util.List;

import exceptions.StringInvalidaException;


/**
 * Classe que representa uma abstracao de uma refeicao.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class RefeicaoCompleta extends Refeicao {

	private List<Prato> pratos;

	public RefeicaoCompleta(String nome, String descricao) throws StringInvalidaException {
		super(nome, descricao);
		this.pratos = new ArrayList<Prato>();
	}
	
	public void adicionaPrato(Prato prato){
		pratos.add(prato);
	}

	/**
	 * Este metodo calcula o preco de todos os pratos inclusos na refeicao.
	 * @return Retorna um double com o preco.
	 */
	@Override
	public double calculaPreco() {
		double total = 0.0;
		for (Prato prato : pratos) {
			total += prato.calculaPreco();
		}
		total = total * 0.9;
		return total;
	}
	
	/**
	 * Metodo que informa a descricao de cada prato da lista de pratos.
	 * @return retorna uma string contendo informacoes sobre a descricao de cada prato.
	 */
	@Override
	public String getDescricao() {
		String descricao = super.getDescricao();
		descricao += " Serao servidos:";
		for (int i = 0; i < (pratos.size()-1); i++) {
			descricao += " (" + (i+1) + ") " + pratos.get(i).getNome() +  ",";
		}
		descricao += " (" + (pratos.size()) + ") " + pratos.get(pratos.size()-1).getNome() + ".";
		return descricao;
	}

	

	

}
