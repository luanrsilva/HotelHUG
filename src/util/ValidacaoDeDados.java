package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.StringInvalidaException;

public class ValidacaoDeDados {

	public ValidacaoDeDados() {

	}

	public boolean verificaDataValida(String data) {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			LocalDate.parse(data, dataFormatada);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verificaEmailValido(String email) {
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
	
	public boolean verificaIdadeValida(String data){
		String[] novaData = data.split("/");
		int ano = Integer.parseInt(novaData[2]);
		LocalDate dataAtual = LocalDate.now();
		int anoAtual = dataAtual.getYear();
		if (anoAtual-ano < 18) {
			return true;
		}
		return false;
	}

}
