package facade;

import controller.HotelController;
import controller.RestauranteController;
import exceptions.AtualizacaoHospedeException;
import exceptions.BuscaHospedeException;
import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoCompletaException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroException;
import exceptions.CadastroHospedeException;
import exceptions.ChecarHospedagemException;
import exceptions.CheckinException;
import exceptions.CheckoutException;
import exceptions.ConsultaException;
import exceptions.ConsultaHospedeException;
import exceptions.ConsultaRestauranteException;
import exceptions.DadoInvalidoException;
import exceptions.HospedagemException;
import exceptions.IdInvalidoException;
import exceptions.RemocaoHospedeException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;

public class Facade {

	private HotelController hotelController;
	private RestauranteController restauranteController;

	public Facade() {
		hotelController = new HotelController();
		restauranteController = new RestauranteController();
	}

	public void iniciaSistema() {

	}

	public String cadastraHospede(String nome, String email, String nascimento)
			throws StringInvalidaException, CadastroException, CadastroHospedeException {
		return hotelController.cadastraHospede(nome, email, nascimento);
	}

	public void removeHospede(String email) throws BuscaHospedeException, RemocaoHospedeException {
		hotelController.removeHospede(email);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws BuscaHospedeException, DadoInvalidoException, AtualizacaoHospedeException, CadastroException {
		hotelController.atualizaCadastro(id, atributo, valor);
	}

	public String getInfoHospede(String email, String atributo) throws ConsultaException, ConsultaHospedeException {
		return hotelController.getInfoHospede(email, atributo);
	}

	public void realizaCheckin(String email, int dias, String quarto, String tipoDeQuarto)
			throws ValorInvalidoException, StringInvalidaException, IdInvalidoException, CheckinException, ConsultaHospedeException {
		hotelController.realizaCheckin(email, dias, quarto, tipoDeQuarto);
	}

	public String getInfoHospedagem(String email, String atributo) throws Exception {
		return hotelController.getInfoHospedagem(email, atributo);
	}
	
	public String realizaCheckout(String email, String quarto) throws BuscaHospedeException, CheckoutException, ConsultaException {
		return hotelController.realizaCheckout(email, quarto);
	}
	
	public String consultaTransacoes(String atributo) {
		return hotelController.consultaTransacoes(atributo);
	}
	
	public String consultaTransacoes(String atributo, int indice) throws DadoInvalidoException {
		return hotelController.consultaTransacoes(atributo, indice);
	}
	
	public void cadastraPrato(String nome, double preco, String descricao) throws CadastraPratoException, StringInvalidaException{
		restauranteController.cadastraPrato(nome, preco, descricao);
	}
	
	public String consultaRestaurante(String nome, String atributo) throws ConsultaRestauranteException{
		return restauranteController.consultaRestaurante(nome, atributo);
	}
	
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws StringInvalidaException, CadastraRefeicaoException, CadastraRefeicaoCompletaException{
		restauranteController.cadastraRefeicao(nome,descricao, componentes);
	}

	public void fechaSistema(){

	}

}
