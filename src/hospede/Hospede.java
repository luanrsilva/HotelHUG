package hospede;

import java.util.ArrayList;
import java.util.List;

import estadia.Estadia;
import exceptions.DadoInvalidoException;
import exceptions.IdInvalidoException;
import exceptions.StringInvalidaException;
import exceptions.ValorInvalidoException;

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

	public boolean addEstadia(Estadia estadia) {
		return this.estadias.add(estadia);
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

}