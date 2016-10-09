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

/**
 * Classe responsavel por ser a entrada e delegar metodos das classes controllers.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Facade {

	private HotelController hotelController;

	public Facade() {
		hotelController = new HotelController();
	}

	public void iniciaSistema() {

	}
	
	/**
	 * Metodo que calcula o valor a ser pago pela conversao de uma quantidade de pontos de fidelidade
	 * passado como parametro, dependendo do tipo de cartao que o hospede possui, seja VIP, Padrao ou Premium.
	 * @param email
	 * @param qtdPontos
	 * @return retorna uma String com o valor a ser descontado dos pontos.
	 * @throws ConsultaException
	 */
	public String convertePontos(String email,int qtdPontos) throws ConsultaException {
		return this.hotelController.convertePontos(email, qtdPontos);
	}

	/**
	 * Metodo que delega o cadastro de hospedes para o hotelController.
	 * @param nome
	 * @param email
	 * @param nascimento
	 * @return retorna uma string contendo o email do hospede.
	 * @throws StringInvalidaException
	 * @throws CadastroException
	 * @throws CadastroHospedeException
	 */
	public String cadastraHospede(String nome, String email, String nascimento)
			throws StringInvalidaException, CadastroException, CadastroHospedeException {
		return hotelController.cadastraHospede(nome, email, nascimento);
	}

	/**
	 * Metodo que delega a remocao hospedes para o hotelController.
	 * @param email
	 * @throws BuscaHospedeException
	 * @throws RemocaoHospedeException
	 */
	public void removeHospede(String email) throws BuscaHospedeException, RemocaoHospedeException {
		hotelController.removeHospede(email);
	}

	/**
	 * Metodo que delega a atualizacao do cadastro de hospedes para o hotelController.
	 * @param id
	 * @param atributo
	 * @param valor
	 * @throws BuscaHospedeException
	 * @throws DadoInvalidoException
	 * @throws AtualizacaoHospedeException
	 * @throws CadastroException
	 */
	public void atualizaCadastro(String id, String atributo, String valor) throws BuscaHospedeException, DadoInvalidoException, AtualizacaoHospedeException, CadastroException {
		hotelController.atualizaCadastro(id, atributo, valor);
	}

	/**
	 * Metodo que delega a busca de informacoes de hospedes para o hotelController.
	 * @param email
	 * @param atributo
	 * @return
	 * @throws ConsultaException
	 * @throws ConsultaHospedeException
	 */
	public String getHospedeInfo(String email, String atributo) throws ConsultaException, ConsultaHospedeException {
		return hotelController.getInfoHospede(email, atributo);
	}
	
	/**
	 * Metodo que delega a busca de informacoes de hospedes para o hotelController.
	 * @param email
	 * @param atributo
	 * @return
	 * @throws ConsultaException
	 * @throws ConsultaHospedeException
	 */
	public String getInfoHospede(String email, String atributo) throws ConsultaException, ConsultaHospedeException {
		return hotelController.getInfoHospede(email, atributo);
	}

	/**
	 * Metodo que delega a realizacao de checkin de hospedes para o hotelController.
	 * @param email
	 * @param dias
	 * @param quarto
	 * @param tipoDeQuarto
	 * @throws StringInvalidaException
	 * @throws IdInvalidoException
	 * @throws CheckinException
	 * @throws ConsultaHospedeException
	 */
	public void realizaCheckin(String email, int dias, String quarto, String tipoDeQuarto)
			throws StringInvalidaException, IdInvalidoException, CheckinException, ConsultaHospedeException {
		hotelController.realizaCheckin(email, dias, quarto, tipoDeQuarto);
	}

	/**
	 * Metodo que delega a busca de informacoes de hospedes para o hotelController.
	 * @param email
	 * @param atributo
	 * @return retorna uma string contendo a informacao solicitada.
	 * @throws Exception
	 */
	public String getInfoHospedagem(String email, String atributo) throws Exception {
		return hotelController.getInfoHospedagem(email, atributo);
	}
	
	/**
	 * Metodo que delega a realizacao de checkout de hospedes para o hotelController.
	 * @param email
	 * @param quarto
	 * @return retorna uma string contendo informacoes a respeito do valor a ser pago pelas estadias.
	 * @throws BuscaHospedeException
	 * @throws CheckoutException
	 * @throws ConsultaException
	 */
	public String realizaCheckout(String email, String quarto) throws BuscaHospedeException, CheckoutException, ConsultaException {
		return hotelController.realizaCheckout(email, quarto);
	}
	
	/**
	 * Metodo que delega a consulta de transacoes a partir do atributo passado para o hotelController.
	 * @param atributo
	 * @return retorna uma string contendo as informacoes requeridas.
	 */
	public String consultaTransacoes(String atributo) {
		return hotelController.consultaTransacoes(atributo);
	}
	
	/**
	 * Metodo que delega a consulta de transacoes a partir do indice passado para o hotelController.
	 * @param atributo
	 * @param indice
	 * @return retorna uma string contendo as informacoes requeridas.
	 * @throws DadoInvalidoException
	 */
	public String consultaTransacoes(String atributo, int indice) throws DadoInvalidoException {
		return hotelController.consultaTransacoes(atributo, indice);
	}
	
	/**
	 * Metodo que delega o cadastro de pratos para o restauranteController.
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws CadastraPratoException
	 * @throws StringInvalidaException
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws CadastraPratoException, StringInvalidaException{
		hotelController.cadastraPrato(nome, preco, descricao);
	}
	
	/**
	 * Metodo que delega a consulta de informacoes de refeicoes para o restauranteController.
	 * @param nome
	 * @param atributo
	 * @return Retorna uma String, contendo uma informacao a partir do preco ou da descricao da refeicao.
	 * @throws ConsultaRestauranteException
	 */
	public String consultaRestaurante(String nome, String atributo) throws ConsultaRestauranteException{
		return hotelController.consultaRestaurante(nome, atributo);
	}
	
	public String consultaMenuRestaurante(){
		return this.hotelController.consultaMenuRestaurante();
	}
	
	/**
	 * Metodo que delega o cadastro de refeicoes para o restauranteController.
	 * @param nome
	 * @param descricao
	 * @param componentes
	 * @throws StringInvalidaException
	 * @throws CadastraRefeicaoException
	 * @throws CadastraRefeicaoCompletaException
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws StringInvalidaException, CadastraRefeicaoException, CadastraRefeicaoCompletaException{
		hotelController.cadastraRefeicao(nome,descricao, componentes);
	}
	
	public void ordenaMenu(String tipoOrdenacao){
		this.hotelController.ordenaMenu(tipoOrdenacao);
	}
	
	public String realizaPedido(String email, String itemMenu) throws ConsultaException, ConsultaRestauranteException, DadoInvalidoException{
		return this.hotelController.realizaPedido(email, itemMenu);
	}
	
	public String imprimirHospedes() {
		return this.hotelController.imprimirHospedes();
	}
	
	public String imprimirCardapio() {
		return this.hotelController.imprimirCardapio();
	}

	public String imprimirTransacoes() {
		return this.hotelController.imprimirTransacoes();
	}
	
	public String relatorioHotel() {
		return this.hotelController.relatorioHotel();
	}
	
	public void fechaSistema(){

	}

}
