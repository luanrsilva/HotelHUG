package cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vip implements TipoDeCartaoIF{
	private static final double DESCONTO_VIP = 0.85;
	private static final double BONUS_VIP = 0.5;
	private static final double ADICIONAL_VIP = 10;
	private static final double TAXA_CONVERTE_FIDELIDADE = 0.7;
	private static final double TAXA_CONVERTE_FIDELIDADE_BONUS = 0.5;
	private static final double LIMITE_VALOR_GASTO = 100.00;
	
	@Override
	public double desconto(double valorGasto) {
		double desconto =  valorGasto * DESCONTO_VIP;
		double adicional = 0.0;
		if(valorGasto > LIMITE_VALOR_GASTO){
			adicional = (int) ((valorGasto/100)* ADICIONAL_VIP);
		}
		desconto =  desconto - adicional;
		BigDecimal arredondado =  new BigDecimal(desconto).setScale(2, RoundingMode.UP);
		return arredondado.doubleValue();
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

	@Override
	public double pagaDividasGastos(double qtdPontos) {
		double valorConvertido = TAXA_CONVERTE_FIDELIDADE * qtdPontos;
		double adicional = (int)(qtdPontos/10) * TAXA_CONVERTE_FIDELIDADE_BONUS;
		double valorFinal = valorConvertido + adicional;
		BigDecimal arredondado =  new BigDecimal(valorFinal).setScale(2, RoundingMode.UP);
		return arredondado.doubleValue();
	}

}
