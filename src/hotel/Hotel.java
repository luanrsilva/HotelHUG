package hotel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import estadia.Estadia;
import exceptions.AtualizacaoHospedeException;
import exceptions.BuscaHospedeException;
import exceptions.CadastraPratoException;
import exceptions.CadastraRefeicaoException;
import exceptions.CadastroHospedeException;
import exceptions.DadoInvalidoException;
import exceptions.DataInvalidaException;
import exceptions.EmailInvalidoException;
import exceptions.HospedagemException;
import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;
import hospede.Hospede;
import restaurante.Restaurante;
import util.ValidacaoDeDados;

public class Hotel {

	private Map<String, Hospede> hospedes;
	private List<Hospede> checkoutRealizados;
	private Restaurante restaurante;
	private ValidacaoDeDados validacao;

	public Hotel() {
		this.hospedes = new HashMap<String, Hospede>();
		this.checkoutRealizados = new ArrayList<Hospede>();
		this.restaurante = new Restaurante();
		this.validacao = new ValidacaoDeDados();
	}

	public String cadastraHospede(String nome, String email, String nascimento)
			throws StringInvalidaException, CadastroHospedeException {

		try {

			Hospede hospede = new Hospede(nome, email, nascimento);
			this.hospedes.put(email, hospede);
			return email;

		} catch (Exception e) {
			throw new CadastroHospedeException(e.getMessage());
		}

	}

	public boolean removeHospede(String email) throws BuscaHospedeException {
		if (hospedes.containsKey(email)) {
			hospedes.remove(email);
			return true;
		} else {
			return false;
		}

	}

	public void atualizaCadastro(String email, String atributo, String valor)
			throws DadoInvalidoException, AtualizacaoHospedeException {
		Hospede hospede = this.hospedes.get(email);
		

		try {
			switch (atributo.toUpperCase()) {
			case "NOME":
				verificaNome(valor);
				verificaNomeInvalido(valor);
				hospede.setNome(valor);
				break;
			case "EMAIL":
				verificaEmail(valor);
				verificaEmailInvalido(valor);
				hospede.setEmail(valor);
				this.hospedes.put(valor, hospede);
				this.hospedes.remove(email);
				break;
			case "DATA DE NASCIMENTO":
				verificaDataInvalida(valor);
				verificaDataDeNascimento(valor);
				hospede.setDataNascimento(valor);
				break;
			}
		} catch (StringInvalidaException e) {
			throw new AtualizacaoHospedeException(e.getMessage());
		}
	}

	public String getInfoHospede(String email, String atributo) throws BuscaHospedeException {
		Hospede hospede = buscaHospede(email); 
		
		String info = "";

		try {
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
		} catch (Exception e) {
			throw new BuscaHospedeException(email);
		}
		return info;
	}

	public Hospede buscaHospede(String email) throws BuscaHospedeException {
		if (hospedes.containsKey(email)) {
			return hospedes.get(email);
		} else {
			throw new BuscaHospedeException(email);
		}

	}

	private boolean verificaQuarto(String quartoId) {
		for (Hospede hospede : this.hospedes.values()) {
			if (hospede.contemEstadia(quartoId)) {
				return true;
			}
		}
		return false;
	}

	public void realizaCheckin(String email, int dias, String quarto, String tipoDeQuarto)
			throws BuscaHospedeException, ValorInvalidoException, StringInvalidaException, IdInvalidoException {
		Hospede hospede = buscaHospede(email);
		if (!verificaQuarto(quarto)) {
			hospede.criaEstadia(dias, quarto, tipoDeQuarto);
		}
	}

	public String getInfoHospedagem(String email, String atributo) throws BuscaHospedeException, HospedagemException {
		Hospede hospede = buscaHospede(email);
		String info = "";
		switch (atributo.toUpperCase()) {
		case "HOSPEDAGENS ATIVAS":
			info += hospede.getEstadias().size();
			if (info.equals("0")) {
				throw new HospedagemException(hospede.getNome());
			}
			break;
		case "QUARTO":
			ArrayList<Estadia> estadias = hospede.getEstadias();
			if (estadias.size() == 0) {
				throw new HospedagemException(hospede.getNome());
			}
			info += estadias.get(0).getQuarto().getId();

			for (int i = 1; i < estadias.size(); i++) {
				info += "," + estadias.get(i).getQuarto().getId();
			}

			break;
		case "TOTAL":
			info += "R$" + (int) hospede.totalPagar() + ",00";
			if ((int) hospede.totalPagar() == 0) {
				throw new HospedagemException(hospede.getNome());
			}
			break;
		}

		return info;
	}

	public String realizaCheckout(String email, String quarto) throws BuscaHospedeException {
		Hospede hospede = buscaHospede(email);
		String info = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		info += "R$" + df.format(hospede.estadiaQuarto(quarto));
		info = info.replace('.', ',');
		this.checkoutRealizados.add(hospede);
		hospede.moveEstadia(quarto);
		hospede.removeEstadia(quarto);
		return info;
	}

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

	public String consultaTransacoes(String atributo, int indice) {
		String info = "";
		switch (atributo.toUpperCase()) {
		case "TOTAL":
			info += "R$" + (int) checkoutRealizados.get(indice).totalPagarCheckOut() + ",00";
			break;
		case "NOME":
			info += checkoutRealizados.get(indice).getNome();
		}

		return info;
	}

	public String consultaRestaurante(String nome, String atributo) {
		return restaurante.consultaRestaurante(nome, atributo);
	}

	public void cadastraPrato(String nome, double preco, String descricao)
			throws CadastraPratoException, StringInvalidaException {
		restaurante.cadastraPrato(nome, preco, descricao);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes)
			throws StringInvalidaException, CadastraRefeicaoException {
		restaurante.cadastraRefeicao(nome, descricao, componentes);
	}
	
	private void verificaIdadeInvalida(String dataNascimento) throws DadoInvalidoException {
		if (validacao.verificaIdadeValida(dataNascimento)) {
			throw new DadoInvalidoException("A idade do(a) hospede deve ser maior que 18 anos.");
		}
	}

	private void verificaNomeInvalido(String nome) throws StringInvalidaException {
		if (validacao.verificaNomeValido(nome)) {
			throw new StringInvalidaException("Nome do(a) hospede esta invalido.");
		}
	}

	private void verificaEmailInvalido(String email) throws EmailInvalidoException {
		if(!validacao.verificaEmailValido(email)){
			throw new EmailInvalidoException("Email do(a) hospede esta invalido.");
		}
	}
	private void verificaDataDeNascimento(String dataNascimento) throws StringInvalidaException {
		if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
			throw new StringInvalidaException("Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
	}
	
	private void verificaEmail(String email) throws StringInvalidaException {
		if (email == null || email.trim().isEmpty()) {
			throw new StringInvalidaException("Email do(a) hospede nao pode ser vazio.");
		}
	}
	
	private void verificaNome(String nome) throws StringInvalidaException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome do(a) hospede nao pode ser vazio.");
		}
	}

	private void verificaDataInvalida(String dataNascimento) throws DataInvalidaException {
		if (!validacao.verificaDataValida(dataNascimento)) {
			throw new DataInvalidaException("Formato de data invalido.");
		}
	}
}
