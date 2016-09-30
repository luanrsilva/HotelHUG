package cartao;

public class Padrao implements TipoDeCartaoIF{
	private static final double DESCONTO_PADRAO = 1.0; // sem desconto
	private static final double BONUS_PADRAO = 1.1;
	
	@Override
	public double desconto() {
		return DESCONTO_PADRAO;
	}

	@Override
	public double bonus() {
		return BONUS_PADRAO;
	}

	@Override
	public double adicionalDesconto() {
		return 0;
	}

	@Override
	public double adicionalBonus() {
		return 0;
	}

}
