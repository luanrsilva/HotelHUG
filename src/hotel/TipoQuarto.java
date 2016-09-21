package hotel;


/**
 * Classe que representa os tipos do quarto.
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public enum TipoQuarto {

	PRESIDENCIAL(450.00), LUXO(250.00), SIMPLES(100.00);

	private double valorQuarto;

	private TipoQuarto(double valor) {
		this.valorQuarto = valor;
	}

	public double getValorQuarto() {
		return valorQuarto;
	}

}
