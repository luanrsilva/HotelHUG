package hotel;

import exceptions.*;

import testes.*;

public class Estadia {

	private int dias;
	private Quarto quarto;

	public Estadia(int qtdDias, String quartoId, String tipoQuarto)
			throws StringInvalidaException, IdInvalidoException {
		this.quarto = new Quarto(quartoId, tipoQuarto);
		this.dias = qtdDias;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public double precoTotal() {
		return this.quarto.getDiaria() * getDias();
	}
	
	/**
	 * Metodo que verifica de a estadia contem o quarto com o ID passado.
	 * @param quartoId
	 * @return retorna um booleano.
	 */
	public boolean contemQuarto(String quartoId){
		if(quartoId.equals(getQuarto().getId())){
			return true;
		}
		return false;
	}

}
