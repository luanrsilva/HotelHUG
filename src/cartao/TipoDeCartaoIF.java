
package cartao;

/**
 * Interface reponsavel por definir metodos pertecentes aos tipos de cartao
 * utilizando do design pattern strategy
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public interface TipoDeCartaoIF {
	
	public double pagaDividasGastos(double valorGasto);
	public double adicionalDesconto();
	public double desconto(double valorGasto);
	public double adicionalBonus();
	public double bonus();
}
