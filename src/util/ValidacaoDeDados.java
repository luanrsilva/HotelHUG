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
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

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
	
	public void verificaIdadeInvalida(String dataNascimento) throws DadoInvalidoException {
		if (verificaIdadeValida(dataNascimento)) {
			throw new DadoInvalidoException("A idade do(a) hospede deve ser maior que 18 anos.");
		}
	}

	public void verificaNomeInvalido(String nome) throws StringInvalidaException {
		if (verificaNomeValido(nome)) {
			throw new StringInvalidaException("Nome do(a) hospede esta invalido.");
		}
	}

	public void verificaEmailInvalido(String email) throws EmailInvalidoException {
		if(!verificaEmailValido(email)){
			throw new EmailInvalidoException("Email do(a) hospede esta invalido.");
		}
	}
	public void verificaDataDeNascimento(String dataNascimento) throws StringInvalidaException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new StringInvalidaException("Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
	}
	
	public void verificaEmail(String email) throws StringInvalidaException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringInvalidaException("Email do(a) hospede nao pode ser vazio.");
		}
	}
	
	public void verificaNome(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome do(a) hospede nao pode ser vazio.");
		}
	}

	public void verificaDataInvalida(String dataNascimento) throws DataInvalidaException {
		if (!verificaDataValida(dataNascimento)) {
			throw new DataInvalidaException("Formato de data invalido.");
		}
	}


}
