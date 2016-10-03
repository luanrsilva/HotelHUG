package cartao;

public interface TipoDeCartaoIF {
	
	public double pagaDividasGastos(double valorGasto);
	public double adicionalDesconto();
	public double desconto();
	public double adicionalBonus();
	public double bonus();
}
