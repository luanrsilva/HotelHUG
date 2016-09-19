package restaurante;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroException;
import exceptions.StringInvalidaException;

public class Restaurante {

	private List<Refeicao> cardapio;

	public Restaurante() {
		this.cardapio = new ArrayList<Refeicao>();
	}

	public void cadastraPrato(String nome, double preco, String descricao)
			throws CadastraPratoException, StringInvalidaException {
		Prato novoPrato;
		try {
			novoPrato = new Prato(nome, preco, descricao);
			cardapio.add(novoPrato);
		} catch (Exception e) {
			throw new CadastraPratoException(e.getMessage());
		}
	}

	public Refeicao buscaRefeicao(String nome) {
		for (Refeicao prato : cardapio) {
			if (nome.equalsIgnoreCase(prato.getNome())) {
				return prato;
			}
		}
		return null;
	}

	public String consultaRestaurante(String nome, String atributo) {
		Refeicao refeicao = buscaRefeicao(nome);
		String info = "";
		switch (atributo.toUpperCase()) {
		case "PRECO":
			DecimalFormat df = new DecimalFormat("#0.00");
			info += "R$" + df.format(refeicao.calculaPreco());
			info = info.replace('.', ',');
			break;
		case "DESCRICAO":
			info += refeicao.getDescricao();
		}
		return info;
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws CadastraRefeicaoException {
		RefeicaoCompleta novaRefeicaoCompleta;
		try {
			novaRefeicaoCompleta = new RefeicaoCompleta(nome, descricao);
			String[] igredientes = componentes.split(";");
			for (String comp : igredientes) {
				Refeicao refeicao = buscaRefeicao(comp);
				if (!(refeicao == null)) {
					novaRefeicaoCompleta.adicionaPrato((Prato) refeicao);
				}
			}
			cardapio.add(novaRefeicaoCompleta);
		} catch (StringInvalidaException e) {
			throw new CadastraRefeicaoException(nome);
		}
	}

}
