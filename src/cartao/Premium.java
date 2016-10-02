package cartao;

public class Premium implements TipoDeCartaoIF{
	private static final double DESCONTO_PREMIUM = 0.9;
	private static final double BONUS_PREMIUM = 0.3; 
	private static final double ADICIONAL_PREMIUM = 10;
	
	@Override
	public double desconto() {
		return DESCONTO_PREMIUM;
	}

	@Override
	public double bonus() {
		return BONUS_PREMIUM;
	}

	@Override
	public double adicionalDesconto() {
		return 0;
	}

	@Override
	public double adicionalBonus() {
		return ADICIONAL_PREMIUM;
	}

}
