package controller;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.AtualizacaoHospedeException;
import exceptions.BuscaHospedeException;
import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroException;
import exceptions.CadastroHospedeException;
import exceptions.ChecarHospedagemException;
import exceptions.CheckinException;
import exceptions.CheckoutException;
import exceptions.ConsultaException;
import exceptions.ConsultaHospedeException;
import exceptions.DadoInvalidoException;
import exceptions.EmailInvalidoException;
import exceptions.HospedagemException;
import exceptions.IdInvalidoException;
import exceptions.RemocaoHospedeException;
import exceptions.StringInvalidaException;
import hotel.Estadia;
import hotel.Hospede;
import util.ValidacaoDeDados;

/**
 * Classe responsavel controlar tudo o que acontece com o hospedes e suas estadias.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class HotelController {

	private Map<String, Hospede> hospedes;
	private List<Hospede> checkoutRealizados;
	private ValidacaoDeDados validacao;

	
	public HotelController() {
		this.hospedes = new HashMap<String, Hospede>();
		this.checkoutRealizados = new ArrayList<Hospede>();
		this.validacao = new ValidacaoDeDados();
	}

	/**
	 * Metodo que adiciona o hospede novo no mapa de hospedes, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao corretos.
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
	 * Metodo que remove o hospede do mapa de hospedes, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao corretos.
	 * @param email
	 * @return retorna um booleano true para o caso da remocao do hospede e outro false para a nao remocao.
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
	 * Metodo que modifica um dos atributos do hospede, dependendo da necessidade do usuario, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao corretos.
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
	 * Metodo que informa ao usuario um dado do hospede solicitada, passado como atrubuto, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao corretos.
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
			}
		} catch (ConsultaException e) {
			throw new ConsultaHospedeException(e.getMessage());
		}
		return info;
	}

	/**
	 * Metodo que busca o hospede a partir de seu email. 
	 * @param email
	 * @return retorna um Hospede
	 * @throws ConsultaException
	 */
	public Hospede buscaHospede(String email) throws ConsultaException{
		if (hospedes.containsKey(email)) {
			return hospedes.get(email);
		} else {
			throw new ConsultaException(email);
		}

	}

	/**
	 * Metodo que verifica se algum hospede possui o quarto com o respectivo ID em suas estadias.
	 * @param quartoId
	 * @return retorna um booleano true para o caso de possuir o quarto e false caso contrario.
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
	 * Metodo que realiza o checkin do hospede no hotel e ainda cria uma estadia associada ao hospede, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao corretos.
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
	 * Metodo que verifica se o quarto de respectivo ID esta sendo utilizado, no caso associado a algum hospede.
	 * @param quarto
	 * @throws StringInvalidaException
	 */
	private void verificaQuartoOcupado(String quarto)
			throws StringInvalidaException {
		if (verificaQuarto(quarto)) {
			throw new StringInvalidaException("Quarto " + quarto + " ja esta ocupado.");
		}
	}

	/**
	 * Metodo que informa ao usuario um dado passado como atributo a respeito da hospedagem do hospede, passa seus parametros por
	 * uma serie de verificacoes que examinam se os dados passados estao corretos.
	 * @param email
	 * @param atributo
	 * @return retorna uma string contendo a informacao solicitada.
	 * @throws ConsultaException
	 * @throws HospedagemException
	 * @throws ChecarHospedagemException
	 */
	public String getInfoHospedagem(String email, String atributo) throws ConsultaException, HospedagemException, ChecarHospedagemException   {
		try {
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoCadastro(email);
			
			Hospede hospede = buscaHospede(email);
			
			if (hospede.getEstadias().size() == 0){
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
	 * Metodo que realiza o checkout do hospede no hotel removendo-o das suas estadias e adicionando na lista de checkouts,
	 * passa seus parametros por uma serie de verificacoes que examinam se os dados passados estao corretos.
	 * @param email
	 * @param quarto
	 * @return retorna uma string contendo informacoes a respeito do valor a ser pago pelas estadias.
	 * @throws BuscaHospedeException
	 * @throws CheckoutException
	 * @throws ConsultaException
	 */
	public String realizaCheckout(String email, String quarto) throws BuscaHospedeException, CheckoutException, ConsultaException {
		try {
			validacao.verificaEmail(email);
			validacao.verificaEmailInvalidoCadastro(email);
			validacao.verificaQuartoIDValido(quarto);
			Hospede hospede = buscaHospede(email);
			String info = "";
			DecimalFormat df = new DecimalFormat("#0.00");
			info += "R$" + df.format(hospede.estadiaQuarto(quarto));
			info = info.replace('.', ',');
			this.checkoutRealizados.add(hospede);
			hospede.moveEstadia(quarto);
			hospede.removeEstadia(quarto);
			return info;
		} catch (StringInvalidaException e) {
			throw new CheckoutException(e.getMessage());
		} catch (EmailInvalidoException e) {
			throw new CheckoutException(e.getMessage());
		}
		
	}

	/**
	 * Metodo que informa dados sobre o atributo passado a respeito da quantidade de checkouts realizados,
	 * a respeito do valor de todos os checkouts realizados e dos nomes dos hospedes que fizeram checkout.
	 * @param atributo
	 * @return retorna uma string contendo as informacoes requeridas.
	 */
	public String consultaTransacoes(String atributo) {
		String info = "";
		switch (atributo.toUpperCase()) {
		case "QUANTIDADE":
			info += checkoutRealizados.size();
			break;
		case "TOTAL":
			double total = 0.0;
			DecimalFormat df = new DecimalFormat("#0.00");
			for (Hospede hospede : checkoutRealizados) {
				total += hospede.totalPagarCheckOut();
			}
			info += "R$" + df.format(total);
			info = info.replace('.', ',');
			break;
		case "NOME":
			info += checkoutRealizados.get(0).getNome();
			for (int i = 1; i < checkoutRealizados.size(); i++) {
				info += ";" + checkoutRealizados.get(i).getNome();
			}
			break;
		}
		return info;
	}

	/**
	 * Metodo que informa o valor total de um checkout realizado ou o nome de um hospede da lista de checkout, tudo
	 * isso a partir do indice informado, passa seus parametros por uma serie de verificacoes que examinam se os dados
	 * passados estao corretos.
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
				info += "R$" + (int) checkoutRealizados.get(indice).totalPagarCheckOut() + ",00";
				break;
			case "NOME":
				info += checkoutRealizados.get(indice).getNome();
				break;
			}
		} catch (DadoInvalidoException di) {
			throw new DadoInvalidoException(di.getMessage());
		}
		return info;
	}
}