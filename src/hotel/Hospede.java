package hotel;

import java.util.ArrayList;
import java.util.List;

import exceptions.DadoInvalidoException;
import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;


/**
 * Classe que representa uma abstracao de um hospede.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Hospede {

	private String nome;
	private String email;
	private String dataNascimento;
	private List<Estadia> estadias;
	private List<Estadia> estadiasAnteriores;

	public Hospede(String nome, String email, String dataNascimento) throws DadoInvalidoException {

		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.estadias = new ArrayList<Estadia>();
		this.estadiasAnteriores = new ArrayList<Estadia>();

	}

	/**
	 * Metodo que adiciona uma estadia na lista de estadias.
	 * @param estadia
	 * @return retorna um booleano.
	 */
	public boolean addEstadia(Estadia estadia) {
		return this.estadias.add(estadia);
	}

	/**
	 * Metodo que calcula a soma a ser das estadias utilizadas.
	 * @return retorna um valor decimal referente ao valor a ser pago pelo hospede.
	 */
	public double totalPagar() {
		double total = 0.0;
		for (Estadia estadia : estadias) {
			total += estadia.precoTotal();
		}

		return total;
	}

	/**
	 * Metodo que busca o valor das estadias a serem pagas do quarto com o respectivo ID passado.
	 * @param quartoId
	 * @return retorna um valor decimal com o valor da estadia.
	 */
	public double estadiaQuarto(String quartoId) {
		for (Estadia estadia : estadias) {
			if (estadia.getQuarto().getId().equalsIgnoreCase(quartoId)) {
				return estadia.getDias() * estadia.getQuarto().getDiaria();
			}
		}
		return 0.0;
	}

	/**
	 * Metodo que cria uma nova estadia adicionando-a na lista de estadias.
	 * @param dias
	 * @param quarto
	 * @param tipoDeQuarto
	 * @throws StringInvalidaException
	 * @throws IdInvalidoException
	 */
	public void criaEstadia(int dias, String quarto, String tipoDeQuarto)
			throws StringInvalidaException, IdInvalidoException {
		Estadia novaEstadia = new Estadia(dias, quarto, tipoDeQuarto);
		if (!estadias.contains(novaEstadia)) {
			estadias.add(novaEstadia);
		}

	}

	/**
	 * Metodo que verifica se alguma das estadias contem o quarto com o respectivo ID.
	 * @param quartoId
	 * @return retorna um booleano.
	 */
	public boolean contemEstadia(String quartoId) {
		for (Estadia estadia : estadias) {
			if (estadia.contemQuarto(quartoId)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Metodo que remove uma estadia que possua um quarto com o respectivo ID.
	 * @param quartoId
	 * @return retorna um booleano.
	 */
	public boolean removeEstadia(String quartoId) {
		for (Estadia estadia : estadias) {
			if (estadia.contemQuarto(quartoId)) {
				return estadias.remove(estadia);
			}

		}
		return false;
	}

	/**
	 * Metodo que move uma estdia atual para uma lista de estadias que nao mais serao ativas.
	 * @param quartoId
	 * @return retorna um booleano.
	 */
	public boolean moveEstadia(String quartoId) {
		for (Estadia estadia : estadias) {
			if (quartoId.equalsIgnoreCase(estadia.getQuarto().getId())) {
				return estadiasAnteriores.add(estadia);
			}
		}
		return false;
	}

	/**
	 * Metodo que calcula o total a ser pago pelo hospede pelo total de estadias
	 * que este possui cadastrado.
	 * @return retorna um valor decimal informando o total a ser pago.
	 */
	public double totalPagarCheckOut() {
		double total = 0.0;
		for (Estadia estadia : estadiasAnteriores) {
			total += estadia.precoTotal();
		}
		return total;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws StringInvalidaException {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws DadoInvalidoException {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws DadoInvalidoException {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Estadia> getEstadias() {
		return (ArrayList<Estadia>) estadias;
	}
}