package controller;

import exceptions.AtualizacaoHospedeException;
import exceptions.BuscaHospedeException;
import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroException;
import exceptions.CadastroHospedeException;
import exceptions.DadoInvalidoException;
import exceptions.EmailInvalidoException;
import exceptions.HospedagemException;
import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;
import hotel.Hotel;
import quarto.Quarto;
import restaurante.Restaurante;
import util.ValidacaoDeDados;

public class Controller {

	private Hotel hotel;

	public Controller() {
		this.hotel = new Hotel();
	}

	public String cadastraHospede(String nome, String email, String nascimento)
			throws StringInvalidaException, CadastroException, CadastroHospedeException {
		return hotel.cadastraHospede(nome, email, nascimento);
	}

	public void removeHospede(String email) throws BuscaHospedeException {
		hotel.removeHospede(email);
	}

	public void atualizaCadastro(String email, String atributo, String valor)
			throws BuscaHospedeException, DadoInvalidoException, AtualizacaoHospedeException {
		hotel.atualizaCadastro(email, atributo, valor);
	}

	public String getinfoHospede(String email, String atributo) throws BuscaHospedeException {
		return hotel.getInfoHospede(email, atributo);
	}

	public void realizaCheckin(String email, int dias, String quarto, String tipoDeQuarto)
			throws BuscaHospedeException, ValorInvalidoException, StringInvalidaException, IdInvalidoException {
		hotel.realizaCheckin(email, dias, quarto, tipoDeQuarto);
	}

	public String getInfoHospedagem(String email, String atributo) throws BuscaHospedeException, HospedagemException {
		return hotel.getInfoHospedagem(email, atributo);
	}

	public String realizaCheckout(String email, String quarto) throws BuscaHospedeException {
		return hotel.realizaCheckout(email, quarto);
	}

	public String consultaTransacoes(String atributo) {
		return hotel.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice) {
		return hotel.consultaTransacoes(atributo, indice);
	}

	public String consultaRestaurante(String nome, String atributo) {
		return hotel.consultaRestaurante(nome, atributo);
	}

	public void cadastraPrato(String nome, double preco, String descricao)
			throws CadastraPratoException, StringInvalidaException {
		hotel.cadastraPrato(nome, preco, descricao);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes)
			throws StringInvalidaException, CadastraRefeicaoException {
		hotel.cadastraRefeicao(nome, descricao, componentes);
	}

}
