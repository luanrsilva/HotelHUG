package hotel;

import exceptions.*;
import quarto.*;

public class Estadia {

	private int dias;
	private Quarto quarto;

	public Estadia(int qtdDias, String quartoId, String tipoQuarto)
			throws ValorInvalidoException, StringInvalidaException, IdInvalidoException {
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
	
	public boolean contemQuarto(String quartoId){
		if(quartoId.equals(getQuarto().getId())){
			return true;
		}
		return false;
	}

}
