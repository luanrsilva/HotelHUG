package util;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DadoInvalidoException;
import exceptions.EmailInvalidoException;
import exceptions.StringInvalidaException;

public class ValidacaoDeDados {

	public ValidacaoDeDados() {

	}

	/**
	 * Metodo que verifica se a data esta dentro das especificacoes.
	 * @param data
	 * @return retorna um booleano.
	 */
	private boolean verificaDataValida(String data) {
		DateTimeFormatter dataFormatada = DateTimeFormatter
				.ofPattern("dd/MM/yyyy");

		try {
			LocalDate.parse(data, dataFormatada);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Metodo que verifica se o nome esta dentro das especificacoes.
	 * @param nome
	 * @return retorna um booleano.
	 */
	private boolean verificaNomeValido(String nome) {
		if (nome.contains("@")) {
			return true;
		}
		return false;

	}

	/**
	 * Metodo que verifica se o email passado esta dentro das especificacoes.
	 * @param email
	 * @return retorna um booleano.
	 */
	private boolean verificaEmailValido(String email) {
		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.A-Za-z0-9-]+)*@([A-"
				+ "Za-z0-9])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\."
				+ "[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

		Pattern pattern = Pattern.compile(emailPattern,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que verifica se o ID do quarto passado esta dentro das especificacoes.
	 * @param quartoID
	 * @return retorna um booleano.
	 */
	private boolean verificaQuartoValido(String quartoID) {
		String quartoPattern = "([A-Za-z0-9])\\w+";
		Pattern pattern = Pattern.compile(quartoPattern,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(quartoID);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}


	/**
	 * Metodo que verifica se a idade calculada a partir da data passada esta dentro das especificacoes.
	 * @param data
	 * @return retorna um booleano.
	 */
	private boolean verificaIdadeValida(String data) {
		String[] novaData = data.split("/");
		int ano = Integer.parseInt(novaData[2]);
		LocalDate dataAtual = LocalDate.now();
		int anoAtual = dataAtual.getYear();
		if (anoAtual - ano < 18) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que verifica se a idade calculada a partir da data de nascimento esta dentro das especificacoes.
	 * @param dataNascimento
	 * @throws DadoInvalidoException
	 */
	public void verificaIdadeInvalida(String dataNascimento)
			throws DadoInvalidoException {
		if (verificaIdadeValida(dataNascimento)) {
			throw new DadoInvalidoException(
					"A idade do(a) hospede deve ser maior que 18 anos.");
		}
	}

	/**
	 * Metodo que verifica se o nome passado esta dentro das especificacoes.
	 * @param nome
	 * @throws StringInvalidaException
	 */
	public void verificaNomeInvalido(String nome)
			throws StringInvalidaException {
		if (verificaNomeValido(nome)) {
			throw new StringInvalidaException(
					"Nome do(a) hospede esta invalido.");
		}
	}

	/**
	 * Metodo que verifica se o email passado esta dentro das especificacoes, para poder fazer o cadastro.
	 * @param email
	 * @throws EmailInvalidoException
	 */
	public void verificaEmailInvalidoCadastro(String email)
			throws EmailInvalidoException {
		if (!verificaEmailValido(email)) {
			throw new EmailInvalidoException(
					"Email do(a) hospede esta invalido.");
		}
	}
	
	/**
	 * Metodo que verifica se o email passado esta dentro das especificacoes, para poder fazer a remocao.
	 * @param email
	 * @throws EmailInvalidoException
	 */
	public void verificaEmailInvalidoRemocao(String email)
			throws EmailInvalidoException {
		if (!verificaEmailValido(email)) {
			throw new EmailInvalidoException(
					"Email do(a) hospede esta invalido.");
		}
	}

	/**
	 * Metodo que verifica se a data de nascimento passada esta dentro das especificacoes.
	 * @param dataNascimento
	 * @throws StringInvalidaException
	 */
	public void verificaDataDeNascimento(String dataNascimento)
			throws StringInvalidaException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
	}

	/**
	 * Metodo que verifica se o email passado esta dentro das especificacoes.
	 * @param email
	 * @throws StringInvalidaException
	 */
	public void verificaEmail(String email) throws StringInvalidaException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Email do(a) hospede nao pode ser vazio.");
		}
	}

	/**
	 * Metodo que verifica se o nome passado esta dentro das especificacoes.
	 * @param nome
	 * @throws StringInvalidaException
	 */
	public void verificaNome(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome do(a) hospede nao pode ser vazio.");
		}
	}

	/**
	 * Metodo que verifica se a data de nascimento passada esta dentro das especificacoes.
	 * @param dataNascimento
	 * @throws StringInvalidaException
	 */
	public void verificaDataInvalida(String dataNascimento)
			throws StringInvalidaException {
		if (!verificaDataValida(dataNascimento)) {
			throw new StringInvalidaException("Formato de data invalido.");
		}
	}

	/**
	 * Metodo que verifica se o ID passado esta dentro das especificacoes.
	 * @param ID
	 * @throws StringInvalidaException
	 */
	public void verificaId(String ID) throws StringInvalidaException {
		if (ID == null || ID.trim().isEmpty()) {
			throw new StringInvalidaException(
					"ID do quarto invalido, use apenas numeros ou letras.");
		}
	}

	/**
	 * Metodo que verifica se o tipo do quarto passado esta dentro das especificacoes.
	 * @param tipoQuarto
	 * @throws StringInvalidaException
	 */
	public void verficaTipoQuarto(String tipoQuarto)
			throws StringInvalidaException {
		if (!(tipoQuarto.equals("Presidencial") || tipoQuarto.equals("Luxo") || tipoQuarto
				.equals("Simples"))) {

			throw new StringInvalidaException("Tipo de quarto invalido.");
		}
	}

	/**
	 * Metodo que verifica se o ID do qiarto passado esta dentro das especificacoes.
	 * @param quartoID
	 * @throws StringInvalidaException
	 */
	public void verificaQuartoIDValido(String quartoID)
			throws StringInvalidaException {
		if (!(verificaQuartoValido(quartoID))) {
			throw new StringInvalidaException(
					"ID do quarto invalido, use apenas numeros ou letras.");
		}
	}
	
	/**
	 * Metodo que verifica se o preco passado esta dentro das especificacoes.
	 * @param preco
	 * @throws StringInvalidaException
	 */
	public void verificaPreco(double preco) throws StringInvalidaException {
		if (preco < 0) {
			throw new StringInvalidaException("Preco do prato eh invalido.");
		}
	}
	
	/**
	 * Metodo que verifica se os dias passados estao dentro das especificacoes.
	 * @param dias
	 * @throws DadoInvalidoException
	 */
	public void verificaDiasValidos(int dias) throws DadoInvalidoException {
		if (dias <= 0) {
			throw new DadoInvalidoException("Quantidade de dias esta invalida.");
		}
	}
	
	/**
	 * Metodo que verifica se o Indice passado esta dentro das especificacoes.
	 * @param indice
	 * @throws DadoInvalidoException
	 */
	public void verificaIndiceValido(int indice) throws DadoInvalidoException {
		if (indice < 0) {
			throw new DadoInvalidoException("Erro na consulta de transacoes. Indice invalido.");
		}
	}
	
	/**
	 * Metodo que verifica se o nome do prato passado esta dentro das especificacoes.
	 * @param nome
	 * @throws StringInvalidaException
	 */
	public void verificaNomePrato(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome do prato esta vazio.");
		}
	}
	
	/**
	 * Metodo que verifica se o nome da refeicao passada esta dentro das especificacoes.
	 * @param nome
	 * @throws StringInvalidaException
	 */
	public void verificaNomeRefeicao(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome da refeicao esta vazio.");
		}
	}
	
	/**
	 * Metodo que verifica se descricao da refeicao passada esta dentro das especificacoes.
	 * @param descricao
	 * @throws StringInvalidaException
	 */
	public void verficaDescricaoRefeicao(String descricao) throws StringInvalidaException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Descricao da refeicao esta vazia.");
		}
	}
	
	/**
	 * Metodo que verifica se os componentes passados estao dentro das especificacoes.
	 * @param componentes
	 * @throws DadoInvalidoException
	 */
	public void verificaRefeicaoCompleta(String componentes) throws DadoInvalidoException {
		String[] ingredientes = componentes.split(";");
		if (!(ingredientes.length <= 4 && ingredientes.length >= 3)) {
			throw new DadoInvalidoException(
					"Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
	}
	
	/**
	 * Metodo que verifica se os componentes passados estao dentro das especificacoes.
	 * @param componente
	 * @throws StringInvalidaException
	 */
	public void verificaComponente(String componente) throws StringInvalidaException {
		if (componente == null || componente.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Componente(s) esta(o) vazio(s).");
		}
	}

}
