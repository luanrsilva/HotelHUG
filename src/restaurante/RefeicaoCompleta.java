package restaurante;

import java.util.ArrayList;
import java.util.List;

import exceptions.StringInvalidaException;

public class RefeicaoCompleta extends Refeicao {

	private List<Prato> pratos;

	public RefeicaoCompleta(String nome, String descricao) throws StringInvalidaException {
		super(nome, descricao);
		this.pratos = new ArrayList<Prato>();
	}
	
	public void adicionaPrato(Prato prato){
		pratos.add(prato);
	}
	
	
	@Override
	public double calculaPreco() {
		double total = 0.0;
		for (Prato prato : pratos) {
			total += prato.calculaPreco();
		}
		total = total * 0.9;
		return total;
	}
	
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
