package cartao;

public class Vip implements TipoDeCartaoIF{
	private static final double DESCONTO_VIP = 0.85;
	private static final double BONUS_VIP = 0.5;
	private static final double ADICIONAL_VIP = 10;
	
	@Override
	public double desconto() {
		return DESCONTO_VIP;
	}

	@Override
	public double bonus() {
		return BONUS_VIP;
	}

	@Override
	public double adicionalDesconto() {
		return ADICIONAL_VIP;
	}

	@Override
	public double adicionalBonus() {
		return 0;
	}

}
