package hospede;

import java.util.ArrayList;
import java.util.List;

import estadia.Estadia;
import exceptions.DadoInvalidoException;
import exceptions.DataInvalidaException;
import exceptions.EmailInvalidoException;
import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;
import util.ValidacaoDeDados;

public class Hospede {

	private String nome;
	private String email;
	private String dataNascimento;
	private List<Estadia> estadias;
	private List<Estadia> estadiasAnteriores;
	private ValidacaoDeDados validacao;

	public Hospede(String nome, String email, String dataNascimento) throws DadoInvalidoException {

		this.validacao = new ValidacaoDeDados();

		verificaNome(nome);
		verificaEmail(email);
		verificaDataDeNascimento(dataNascimento);

		verificaDataInvalida(dataNascimento);
		verificaEmailInvalido(email);
		
		verificaNomeInvalido(nome);
		
		verificaIdadeInvalida(dataNascimento);

		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.estadias = new ArrayList<Estadia>();
		this.estadiasAnteriores = new ArrayList<Estadia>();

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

	private void verificaDataInvalida(String dataNascimento) throws DataInvalidaException {
		if (!validacao.verificaDataValida(dataNascimento)) {
			throw new DataInvalidaException("Formato de data invalido.");
		}
	}

	public boolean addEstadia(Estadia estadia) {
		return this.estadias.add(estadia);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws StringInvalidaException {
		verificaNomeInvalido(nome);
		verificaNome(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws DadoInvalidoException {
		verificaEmail(email);
		verificaEmailInvalido(email);
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws DadoInvalidoException {
		verificaDataDeNascimento(dataNascimento);
		verificaDataInvalida(dataNascimento);
		verificaIdadeInvalida(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Estadia> getEstadias() {
		return (ArrayList<Estadia>) estadias;
	}

	public double totalPagar() {
		double total = 0.0;
		for (Estadia estadia : estadias) {
			total += estadia.precoTotal();
		}

		return total;
	}

	public double estadiaQuarto(String quartoId) {
		for (Estadia estadia : estadias) {
			if (estadia.getQuarto().getId().equalsIgnoreCase(quartoId)) {
				return estadia.getDias() * estadia.getQuarto().getDiaria();
			}
		}
		return 0.0;
	}

	public void criaEstadia(int dias, String quarto, String tipoDeQuarto)
			throws ValorInvalidoException, StringInvalidaException, IdInvalidoException {
		Estadia novaEstadia = new Estadia(dias, quarto, tipoDeQuarto);
		if (!estadias.contains(novaEstadia)) {
			estadias.add(novaEstadia);
		}

	}

	public boolean contemEstadia(String quartoId) {
		for (Estadia estadia : estadias) {
			if (estadia.contemQuarto(quartoId)) {
				return true;
			}
		}

		return false;
	}

	public boolean removeEstadia(String quartoId) {
		for (Estadia estadia : estadias) {
			if (estadia.contemQuarto(quartoId)) {
				return estadias.remove(estadia);
			}

		}
		return false;
	}

	public boolean moveEstadia(String quartoId) {
		for (Estadia estadia : estadias) {
			if (quartoId.equalsIgnoreCase(estadia.getQuarto().getId())) {
				return estadiasAnteriores.add(estadia);
			}
		}
		return false;
	}

	public double totalPagarCheckOut() {
		double total = 0.0;
		for (Estadia estadia : estadiasAnteriores) {
			total += estadia.precoTotal();
		}
		return total;
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

}