package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import restaurante.Refeicao;
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
import exceptions.EmailInvalidoException;
import exceptions.HospedagemException;
import exceptions.IdInvalidoException;
import exceptions.RemocaoHospedeException;
import exceptions.StringInvalidaException;
import hotel.Estadia;
import hotel.Hospede;
import hotel.Transacao;
import util.BancoDeDados;
import util.ValidacaoDeDados;

/**
 * Classe responsavel controlar tudo o que acontece com o hospedes e suas
 * estadias.
 * 
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class HotelController {

	private Map<String, Hospede> hospedes;
	private ValidacaoDeDados validacao;
	private List<Transacao> transacoes;
	private RestauranteController restauranteController;
	private BancoDeDados bd;
	private String FIM_DE_LINHA;

	public HotelController() {
		this.hospedes = new HashMap<String, Hospede>();
		this.validacao = new ValidacaoDeDados();
		this.transacoes = new ArrayList<Transacao>();
		this.restauranteController = new RestauranteController();
		this.bd = new BancoDeDados();
		this.FIM_DE_LINHA = System.lineSeparator();
	}

	/**
	 * Metodo para salvar os objetos do programa em formato toString em
	 * arquivos.
	 * 
	 */
	public void salvarTexto() {
		try {
			this.bd.salvaTexto(this.imprimirHospedes(), "arquivos_sistemas/relatorios/cad_hospedes.txt");
			this.bd.salvaTexto(this.imprimirCardapio(), "arquivos_sistemas/relatorios/cad_restaurante.txt");
			this.bd.salvaTexto(this.imprimirTransacoes(), "arquivos_sistemas/relatorios/cad_transacoes.txt");
			this.bd.salvaTexto(this.relatorioHotel(), "arquivos_sistemas/relatorios/hotel_principal.txt");
		} catch (IOException e) {
			e.getMessage();
		}

	}

	/**
	 * Metodo para ler os objetos do programa em formato toString em arquivos.
	 * 
	 */
	public void carregarTexto() {
		try {
			this.bd.carregaTexto("arquivos_sistemas/relatorios/cad_hospedes.txt");
			this.bd.carregaTexto("arquivos_sistemas/relatorios/cad_restaurante.txt");
			this.bd.carregaTexto("arquivos_sistemas/relatorios/cad_transacoes.txt");
			this.bd.carregaTexto("arquivos_sistemas/relatorios/hotel_principal.txt");
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * Metodo que calcula o valor a ser pago pela conversao de uma quantidade de
	 * pontos de fidelidade passado como parametro, dependendo do tipo de cartao
	 * que o hospede possui, seja VIP, Padrao ou Premium.
	 * 
	 * @param email
	 * @param qtdPontos
	 * @return retorna uma String com o valor a ser descontado dos pontos.
	 * @throws ConsultaException
	 */
	public String convertePontos(String email, int qtdPontos) throws ConsultaException {
		Hospede hospede = this.buscaHospede(email);
		double valor = hospede.getCartao().pagaDividasGastos(qtdPontos);
		String info = this.formataValor(valor);
		hospede.getCartao().setPontos(hospede.getCartao().getPontos() - qtdPontos);

		return info;

	}

	/**
	 * Metodo que adiciona o hospede novo no mapa de hospedes, passa seus
	 * parametros por uma serie de verificacoes que examinam se os dados
	 * passados estao corretos.
	 * 
	 * @param nome
	 * @param email
	 * @param nascimento
	 * @return retorna uma string contendo o email do hospede.
	 * @throws StringInvalidaException
	 * @throws CadastroHospedeException
	 */
	public String cadastraHospede(String nome, String email, String nascimento)
			throws StringInvalidaException, CadastroHospedeException {

		try {
			validacao.verificaNome(nome);
			validacao.verificaNomeInvalido(nome);
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoCadastro(email);
			validacao.verificaDataDeNascimento(nascimento);
			validacao.verificaDataInvalida(nascimento);
			validacao.verificaIdadeInvalida(nascimento);
			Hospede hospede = new Hospede(nome, email, nascimento);
			this.hospedes.put(email, hospede);
			return email;

		} catch (DadoInvalidoException e) {
			throw new CadastroHospedeException(e.getMessage());
		}

	}

	/**
	 * Metodo que remove o hospede do mapa de hospedes, passa seus parametros
	 * por uma serie de verificacoes que examinam se os dados passados estao
	 * corretos.
	 * 
	 * @param email
	 * @return retorna um booleano true para o caso da remocao do hospede e
	 *         outro false para a nao remocao.
	 * @throws BuscaHospedeException
	 * @throws RemocaoHospedeException
	 */
	public boolean removeHospede(String email) throws BuscaHospedeException, RemocaoHospedeException {
		try {
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoRemocao(email);
		} catch (EmailInvalidoException e) {
			throw new RemocaoHospedeException(e.getMessage());
		} catch (StringInvalidaException e) {
			throw new RemocaoHospedeException(e.getMessage());
		}

		if (hospedes.containsKey(email)) {
			hospedes.remove(email);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Metodo que modifica um dos atributos do hospede, dependendo da
	 * necessidade do usuario, passa seus parametros por uma serie de
	 * verificacoes que examinam se os dados passados estao corretos.
	 * 
	 * @param email
	 * @param atributo
	 * @param valor
	 * @throws DadoInvalidoException
	 * @throws AtualizacaoHospedeException
	 * @throws CadastroException
	 */
	public void atualizaCadastro(String email, String atributo, String valor)
			throws DadoInvalidoException, AtualizacaoHospedeException, CadastroException {
		Hospede hospede = this.hospedes.get(email);

		try {
			switch (atributo.toUpperCase()) {
			case "NOME":
				validacao.verificaNome(valor);
				validacao.verificaNomeInvalido(valor);
				hospede.setNome(valor);
				break;
			case "EMAIL":
				validacao.verificaEmail(valor);
				validacao.verificaEmailInvalidoCadastro(valor);
				hospede.setEmail(valor);
				this.hospedes.put(valor, hospede);
				this.hospedes.remove(email);
				break;
			case "DATA DE NASCIMENTO":
				validacao.verificaDataDeNascimento(valor);
				validacao.verificaDataInvalida(valor);
				validacao.verificaIdadeInvalida(valor);
				hospede.setDataNascimento(valor);
				break;
			}
		} catch (StringInvalidaException e) {
			throw new AtualizacaoHospedeException(e.getMessage());
		} catch (EmailInvalidoException e) {
			throw new AtualizacaoHospedeException(e.getMessage());
		} catch (DadoInvalidoException e) {
			throw new AtualizacaoHospedeException(e.getMessage());
		}
	}

	/**
	 * Metodo que informa ao usuario um dado do hospede solicitada, passado como
	 * atrubuto, passa seus parametros por uma serie de verificacoes que
	 * examinam se os dados passados estao corretos.
	 * 
	 * @param email
	 * @param atributo
	 * @return retorna uma string contendo a informacao sobre o hospede.
	 * @throws ConsultaException
	 * @throws ConsultaHospedeException
	 */
	public String getInfoHospede(String email, String atributo) throws ConsultaException, ConsultaHospedeException {
		String info = "";

		try {
			Hospede hospede = buscaHospede(email);

			switch (atributo.toUpperCase()) {
			case "NOME":
				info += hospede.getNome();
				break;
			case "EMAIL":
				info += hospede.getEmail();
				break;
			case "DATA DE NASCIMENTO":
				info += hospede.getDataNascimento();
				break;
			case "PONTOS":
				info += hospede.getPontos();
				break;
			}
		} catch (ConsultaException e) {
			throw new ConsultaHospedeException(e.getMessage());
		}
		return info;
	}

	/**
	 * Metodo que busca o hospede a partir de seu email.
	 * 
	 * @param email
	 * @return retorna um Hospede
	 * @throws ConsultaException
	 */
	public Hospede buscaHospede(String email) throws ConsultaException {
		if (hospedes.containsKey(email)) {
			return hospedes.get(email);
		} else {
			throw new ConsultaException(email);
		}

	}

	/**
	 * Metodo que verifica se algum hospede possui o quarto com o respectivo ID
	 * em suas estadias.
	 * 
	 * @param quartoId
	 * @return retorna um booleano true para o caso de possuir o quarto e false
	 *         caso contrario.
	 */
	private boolean verificaQuarto(String quartoId) {
		for (Hospede hospede : this.hospedes.values()) {
			if (hospede.contemEstadia(quartoId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que realiza o checkin do hospede no hotel e ainda cria uma estadia
	 * associada ao hospede, passa seus parametros por uma serie de verificacoes
	 * que examinam se os dados passados estao corretos.
	 * 
	 * @param email
	 * @param dias
	 * @param quarto
	 * @param tipoQuarto
	 * @throws BuscaHospedeException
	 * @throws CheckinException
	 */
	public void realizaCheckin(String email, int dias, String quarto, String tipoQuarto)
			throws BuscaHospedeException, CheckinException {
		try {
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoCadastro(email);
			validacao.verificaId(quarto);
			validacao.verficaTipoQuarto(tipoQuarto);
			validacao.verificaQuartoIDValido(quarto);
			validacao.verificaDiasValidos(dias);
			verificaQuartoOcupado(quarto);

			Hospede hospede = buscaHospede(email);

			if (!verificaQuarto(quarto)) {
				hospede.criaEstadia(dias, quarto, tipoQuarto);
			}
		} catch (ConsultaException bh) {
			throw new CheckinException(bh.getMessage());
		} catch (StringInvalidaException si) {
			throw new CheckinException(si.getMessage());
		} catch (EmailInvalidoException ei) {
			throw new CheckinException(ei.getMessage());
		} catch (DadoInvalidoException de) {
			throw new CheckinException(de.getMessage());
		}

	}

	/**
	 * Metodo que verifica se o quarto de respectivo ID esta sendo utilizado, no
	 * caso associado a algum hospede.
	 * 
	 * @param quarto
	 * @throws StringInvalidaException
	 */
	private void verificaQuartoOcupado(String quarto) throws StringInvalidaException {
		if (verificaQuarto(quarto)) {
			throw new StringInvalidaException("Quarto " + quarto + " ja esta ocupado.");
		}
	}

	/**
	 * Metodo que informa ao usuario um dado passado como atributo a respeito da
	 * hospedagem do hospede, passa seus parametros por uma serie de
	 * verificacoes que examinam se os dados passados estao corretos.
	 * 
	 * @param email
	 * @param atributo
	 * @return retorna uma string contendo a informacao solicitada.
	 * @throws ConsultaException
	 * @throws HospedagemException
	 * @throws ChecarHospedagemException
	 */
	public String getInfoHospedagem(String email, String atributo)
			throws ConsultaException, HospedagemException, ChecarHospedagemException {
		try {
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoCadastro(email);

			Hospede hospede = buscaHospede(email);

			if (hospede.getEstadias().size() == 0) {
				throw new HospedagemException(hospede.getNome());

			}

			String info = "";
			switch (atributo.toUpperCase()) {
			case "HOSPEDAGENS ATIVAS":
				info += hospede.getEstadias().size();
				break;
			case "QUARTO":
				ArrayList<Estadia> estadias = hospede.getEstadias();
				info += estadias.get(0).getQuarto().getId();

				for (int i = 1; i < estadias.size(); i++) {
					info += "," + estadias.get(i).getQuarto().getId();
				}

				break;
			case "TOTAL":
				info += "R$" + (int) hospede.totalPagar() + ",00";
				break;
			}

			return info;
		} catch (StringInvalidaException e) {
			throw new ChecarHospedagemException(e.getMessage());
		} catch (EmailInvalidoException e) {
			throw new ChecarHospedagemException(e.getMessage());
		}
	}

	/**
	 * Metodo que realiza o checkout do hospede no hotel removendo-o das suas
	 * estadias e adicionando na lista de checkouts, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao
	 * corretos.
	 * 
	 * @param email
	 * @param quarto
	 * @return retorna uma string contendo informacoes a respeito do valor a ser
	 *         pago pelas estadias.
	 * @throws BuscaHospedeException
	 * @throws CheckoutException
	 * @throws ConsultaException
	 */
	public String realizaCheckout(String email, String quarto)
			throws BuscaHospedeException, CheckoutException, ConsultaException {
		try {
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoCadastro(email);
			validacao.verificaQuartoIDValido(quarto);
			Hospede hospede = buscaHospede(email);
			double valor = hospede.estadiaQuarto(quarto);
			double valorComDesconto = hospede.getCartao().aplicaDescontoGastos(valor);
			//valorComDesconto =  this.arredondamento(valorComDesconto);
			Transacao transacao = new Transacao(hospede.getNome(), valorComDesconto, quarto);
			transacoes.add(transacao);
			hospede.desativaEstadia(quarto);
			hospede.getCartao().adicionaPontos(valor);
			String info = formataValor(valorComDesconto);
			return info;
		} catch (StringInvalidaException e) {
			throw new CheckoutException(e.getMessage());
		} catch (EmailInvalidoException e) {
			throw new CheckoutException(e.getMessage());
		}

	}

	private String formataValor(double valor) {
		String info = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		info += "R$" + df.format(valor);
		info = info.replace('.', ',');
		return info;
	}

	/*private double arredondamento(double valor) {
		
		//BigDecimal arredondado =  new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
		//return arredondado.doubleValue();
		
		
		valor *= 100;
		valor = Math.ceil(valor);
		valor /= 100;
		return valor;
	}*/

	/**
	 * Metodo que informa dados sobre o atributo passado a respeito da
	 * quantidade de checkouts realizados, a respeito do valor de todos os
	 * checkouts realizados e dos nomes dos hospedes que fizeram checkout.
	 * 
	 * @param atributo
	 * @return retorna uma string contendo as informacoes requeridas.
	 */
	public String consultaTransacoes(String atributo) {
		String info = "";
		switch (atributo.toUpperCase()) {
		case "QUANTIDADE":
			info += transacoes.size();
			break;
		case "TOTAL":
			double total = 0.0;
			DecimalFormat df = new DecimalFormat("#0.00");
			total = this.totalTransacoes();
			info += "R$" + df.format(total);
			info = info.replace('.', ',');
			break;
		case "NOME":
			info += transacoes.get(0).getNome();
			for (int i = 1; i < transacoes.size(); i++) {
				info += ";" + transacoes.get(i).getNome();
			}
			break;
		}
		return info;
	}

	/**
	 * Metodo que informa o valor total de um checkout realizado ou o nome de um
	 * hospede da lista de checkout, tudo isso a partir do indice informado,
	 * passa seus parametros por uma serie de verificacoes que examinam se os
	 * dados passados estao corretos.
	 * 
	 * @param atributo
	 * @param indice
	 * @return retorna uma string contendo as informacoes requeridas.
	 * @throws DadoInvalidoException
	 */
	public String consultaTransacoes(String atributo, int indice) throws DadoInvalidoException {
		String info = "";
		try {
			validacao.verificaIndiceValido(indice);
			switch (atributo.toUpperCase()) {
			case "TOTAL":
				DecimalFormat df = new DecimalFormat("#0.00");
				info += "R$" + df.format(transacoes.get(indice).getValor());
				info = info.replace('.', ',');
				break;
			case "NOME":
				info += transacoes.get(indice).getNome();
				break;
			case "DETALHES":
				info += transacoes.get(indice).getTipo();
			}
		} catch (DadoInvalidoException di) {
			throw new DadoInvalidoException(di.getMessage());
		}
		return info;
	}

	private double totalTransacoes() {
		double valor = 0.0;
		for (Transacao transacao : transacoes) {
			valor += transacao.getValor();
		}

		return valor;
	}

	public String realizaPedido(String email, String nomeRefeicao)
			throws ConsultaException, ConsultaRestauranteException, DadoInvalidoException {
		Hospede hospede = this.buscaHospede(email);
		double valor = restauranteController.realizaPedido(hospede.getNome(), nomeRefeicao);
		double valorComDesconto = hospede.getCartao().aplicaDescontoGastos(valor);
		//valorComDesconto = this.arredondamento(valorComDesconto);
		Transacao transacao = new Transacao(hospede.getNome(), valorComDesconto, nomeRefeicao);
		transacoes.add(transacao);
		hospede.getCartao().adicionaPontos(valor);
		return formataValor(valorComDesconto);
	}

	/**
	 * Metodo que delega o cadastro de pratos para o restauranteController.
	 * 
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws CadastraPratoException
	 * @throws StringInvalidaException
	 */
	public void cadastraPrato(String nome, double preco, String descricao)
			throws CadastraPratoException, StringInvalidaException {
		restauranteController.cadastraPrato(nome, preco, descricao);
	}

	/**
	 * Metodo que delega a consulta de informacoes de refeicoes para o
	 * restauranteController.
	 * 
	 * @param nome
	 * @param atributo
	 * @return Retorna uma String, contendo uma informacao a partir do preco ou
	 *         da descricao da refeicao.
	 * @throws ConsultaRestauranteException
	 */
	public String consultaRestaurante(String nome, String atributo) throws ConsultaRestauranteException {
		return restauranteController.consultaRestaurante(nome, atributo);
	}

	public String consultaMenuRestaurante() {
		return this.restauranteController.consultaMenuRestaurante();
	}

	/**
	 * Metodo que delega o cadastro de refeicoes para o restauranteController.
	 * 
	 * @param nome
	 * @param descricao
	 * @param componentes
	 * @throws StringInvalidaException
	 * @throws CadastraRefeicaoException
	 * @throws CadastraRefeicaoCompletaException
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes)
			throws StringInvalidaException, CadastraRefeicaoException, CadastraRefeicaoCompletaException {
		restauranteController.cadastraRefeicao(nome, descricao, componentes);
	}

	public void ordenaMenu(String tipoOrdenacao) {
		this.restauranteController.ordenaMenu(tipoOrdenacao);
	}

	public String imprimirHospedes() {
		int contador = 1;
		StringBuilder sb = new StringBuilder();
		sb.append("Cadastro de Hospedes: " + this.hospedes.size() + " hospedes registrados" + FIM_DE_LINHA);
		for (Entry<String, Hospede> hospedeEntry : this.hospedes.entrySet()) {
			sb.append(FIM_DE_LINHA + "==> Hospede " + contador + ":" + FIM_DE_LINHA);
			Hospede hospede = hospedeEntry.getValue();
			sb.append(hospede.toString() + FIM_DE_LINHA);
			contador++;
		}
		return sb.toString();
	}

	public String imprimirCardapio() {
		return this.restauranteController.imprimirCardipio();
	}

	public String imprimirTransacoes() {
		StringBuilder sb = new StringBuilder();
		sb.append("Historico de Transacoes:" + FIM_DE_LINHA);
		for (Transacao transacao : transacoes) {
			sb.append(transacao.toString());
		}
		sb.append("===== Resumo de transacoes =====" + FIM_DE_LINHA);
		sb.append("Lucro total:" + this.consultaTransacoes("TOTAL") + FIM_DE_LINHA);
		sb.append("Total de transacoes:" + this.consultaTransacoes("QUANTIDADE") + FIM_DE_LINHA);
		double valor = this.totalTransacoes() / Integer.parseInt(this.consultaTransacoes("QUANTIDADE"));
		sb.append("Lucro medio por transacao: " + this.formataValor(valor));
		return sb.toString();
	}

	public String relatorioHotel() {
		StringBuilder sb = new StringBuilder();
		sb.append("======================================================" + FIM_DE_LINHA);
		sb.append(this.imprimirHospedes() + FIM_DE_LINHA);
		sb.append("======================================================" + FIM_DE_LINHA);
		sb.append(this.imprimirCardapio() + FIM_DE_LINHA);
		sb.append("======================================================" + FIM_DE_LINHA);
		sb.append(this.imprimirTransacoes() + FIM_DE_LINHA);
		return sb.toString();
	}

	public void salvaHospede(String email) {
		try {
			Hospede buscado = buscaHospede(email);
			this.bd.salvaTexto(buscado.toString(), "arquivos_sistemas/relatorios/cad_hospedes.txt");
		} catch (ConsultaException | IOException e) {
			e.getMessage();
		}
	}
}