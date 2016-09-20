package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import restaurante.Prato;
import restaurante.Refeicao;
import restaurante.RefeicaoCompleta;
import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroException;
import exceptions.StringInvalidaException;

public class RestauranteController {

	private List<Refeicao> cardapio;

	public RestauranteController() {
		this.cardapio = new ArrayList<Refeicao>();
	}

	/**
	 * Metodo que cadastra/adiciona o prato no cardapio(Lista de pratos) do restaurante.
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws CadastraPratoException
	 * @throws StringInvalidaException
	 */
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

	/**
	 * Metodo que busca um prato no cardapio a partir de seu nome.
	 * @param nome
	 * @return Retorna o prato caso exista ou nulo se nao existir.
	 */
	private Refeicao buscaRefeicao(String nome) {
		for (Refeicao prato : cardapio) {
			if (nome.equalsIgnoreCase(prato.getNome())) {
				return prato;
			}
		}
		return null;
	}

	/**
	 * Metodo que busca e retorna uma informacao de um atributo passado, para o prato passado.
	 * @param nome
	 * @param atributo
	 * @return Retorna uma String, contendo uma informacao a partir do preco ou da descricao da refeicao.
	 */
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
