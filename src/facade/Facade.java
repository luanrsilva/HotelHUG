package facade;

import controller.Controller;
import exceptions.AtualizacaoHospedeException;
import exceptions.BuscaHospedeException;
import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroException;
import exceptions.CadastroHospedeException;
import exceptions.DadoInvalidoException;
import exceptions.HospedagemException;
import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;

public class Facade {

	private Controller controller;

	public Facade() {
		controller = new Controller();
	}

	public void iniciaSistema() {

	}

	public String cadastraHospede(String nome, String email, String nascimento)
			throws StringInvalidaException, CadastroException, CadastroHospedeException {
		return controller.cadastraHospede(nome, email, nascimento);
	}

	public void removeHospede(String email) throws BuscaHospedeException {
		controller.removeHospede(email);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws BuscaHospedeException, DadoInvalidoException, AtualizacaoHospedeException {
		controller.atualizaCadastro(id, atributo, valor);
	}

	public String getInfoHospede(String email, String atributo) throws BuscaHospedeException {
		return controller.getinfoHospede(email, atributo);
	}

	public void realizaCheckin(String email, int dias, String quarto, String tipoDeQuarto)
			throws BuscaHospedeException, ValorInvalidoException, StringInvalidaException, IdInvalidoException {
		controller.realizaCheckin(email, dias, quarto, tipoDeQuarto);
	}

	public String getInfoHospedagem(String email, String atributo) throws BuscaHospedeException, HospedagemException {
		return controller.getInfoHospedagem(email, atributo);
	}
	
	public String realizaCheckout(String email, String quarto) throws BuscaHospedeException {
		return controller.realizaCheckout(email, quarto);
	}
	
	public String consultaTransacoes(String atributo) {
		return controller.consultaTransacoes(atributo);
	}
	
	public String consultaTransacoes(String atributo, int indice) {
		return controller.consultaTransacoes(atributo, indice);
	}
	
	public void cadastraPrato(String nome, double preco, String descricao) throws CadastraPratoException, StringInvalidaException{
		controller.cadastraPrato(nome, preco, descricao);
	}
	
	public String consultaRestaurante(String nome, String atributo){
		return controller.consultaRestaurante(nome, atributo);
	}
	
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws StringInvalidaException, CadastraRefeicaoException{
		controller.cadastraRefeicao(nome,descricao, componentes);
	}

	public void fechaSistema(){

	}

}
