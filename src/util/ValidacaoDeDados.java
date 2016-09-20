package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DadoInvalidoException;
import exceptions.DataInvalidaException;
import exceptions.EmailInvalidoException;
import exceptions.StringInvalidaException;

public class ValidacaoDeDados {

	public ValidacaoDeDados() {

	}

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

	private boolean verificaNomeValido(String nome) {
		if (nome.contains("@")) {
			return true;
		}
		return false;

	}

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

	public void verificaIdadeInvalida(String dataNascimento)
			throws DadoInvalidoException {
		if (verificaIdadeValida(dataNascimento)) {
			throw new DadoInvalidoException(
					"A idade do(a) hospede deve ser maior que 18 anos.");
		}
	}

	public void verificaNomeInvalido(String nome)
			throws StringInvalidaException {
		if (verificaNomeValido(nome)) {
			throw new StringInvalidaException(
					"Nome do(a) hospede esta invalido.");
		}
	}

	public void verificaEmailInvalidoCadastro(String email)
			throws EmailInvalidoException {
		if (!verificaEmailValido(email)) {
			throw new EmailInvalidoException(
					"Email do(a) hospede esta invalido.");
		}
	}
	
	public void verificaEmailInvalidoRemocao(String email)
			throws EmailInvalidoException {
		if (!verificaEmailValido(email)) {
			throw new EmailInvalidoException(
					"Email do(a) hospede esta invalido.");
		}
	}

	public void verificaDataDeNascimento(String dataNascimento)
			throws StringInvalidaException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
	}

	public void verificaEmail(String email) throws StringInvalidaException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Email do(a) hospede nao pode ser vazio.");
		}
	}

	public void verificaNome(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome do(a) hospede nao pode ser vazio.");
		}
	}

	public void verificaDataInvalida(String dataNascimento)
			throws DataInvalidaException {
		if (!verificaDataValida(dataNascimento)) {
			throw new DataInvalidaException("Formato de data invalido.");
		}
	}

	public void verificaId(String ID) throws StringInvalidaException {
		if (ID == null || ID.trim().isEmpty()) {
			throw new StringInvalidaException(
					"ID do quarto invalido, use apenas numeros ou letras.");
		}
	}

	public void verficaTipoQuarto(String tipoQuarto)
			throws StringInvalidaException {
		if (!(tipoQuarto.equals("Presidencial") || tipoQuarto.equals("Luxo") || tipoQuarto
				.equals("Simples"))) {

			throw new StringInvalidaException("Tipo de quarto invalido.");
		}
	}

	public void verificaQuartoIDValido(String quartoID)
			throws StringInvalidaException {
		if (!(verificaQuartoValido(quartoID))) {
			throw new StringInvalidaException(
					"ID do quarto invalido, use apenas numeros ou letras.");
		}
	}
	
	public void verificaPreco(double preco) throws StringInvalidaException {
		if (preco < 0) {
			throw new StringInvalidaException("Preco do prato eh invalido.");
		}
	}
	
	public void verificaDiasValidos(int dias) throws DadoInvalidoException {
		if (dias <= 0) {
			throw new DadoInvalidoException("Quantidade de dias esta invalida.");
		}
	}
	
	public void verificaIndiceValido(int indice) throws DadoInvalidoException {
		if (indice < 0) {
			throw new DadoInvalidoException("Erro na consulta de transacoes. Indice invalido.");
		}
	}
	
	public void verificaNomePrato(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome do prato esta vazio.");
		}
	}
	
	public void verificaNomeRefeicao(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Nome da refeicao esta vazio.");
		}
	}
	
	public void verficaDescricaoRefeicao(String descricao) throws DadoInvalidoException {
		if (descricao == null || descricao.trim().isEmpty()) {
			throw new StringInvalidaException(
					"Descricao da refeicao esta vazia.");
		}
	}
	
	public void verificaRefeicaoCompleta(String componentes) throws DadoInvalidoException {
		String[] ingredientes = componentes.split(";");
		if (ingredientes.length <= 3 && ingredientes.length >= 2) {
			throw new DadoInvalidoException(
					"Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
	}
	
	public void verificaComponente(String componente) throws DadoInvalidoException {
		if (componente == null || componente.trim().isEmpty()) {
			throw new DadoInvalidoException(
					"Componente(s) esta(o) vazio(s).");
		}
	}

}
