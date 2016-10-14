package cartao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe que possui as definicoes de um cartao do tipo Vip implementando a interface
 * TipoDeCartaoIF afim de utilizar o design patter strategy
 * @author Ariann Farias, Luan Rocha, Nilton Ginani, Yovany Cunha - Turma 03
 *
 */
public class Vip implements TipoDeCartaoIF{
	private static final double DESCONTO_VIP = 0.85;
	private static final double BONUS_VIP = 0.5;
	private static final double ADICIONAL_VIP = 10;
	private static final double TAXA_CONVERTE_FIDELIDADE = 0.7;
	private static final double TAXA_CONVERTE_FIDELIDADE_BONUS = 0.5;
	private static final double LIMITE_VALOR_GASTO = 100.00;
	
	/** 
	 * Metodo que retorna o valor do desconto de um cartao vip
	 */
	@Override
	public double desconto(double valorGasto) {
		double desconto =  valorGasto * DESCONTO_VIP;
		double adicional = 0.0;
		if(valorGasto > LIMITE_VALOR_GASTO){
			adicional = (int) ((valorGasto/100)* ADICIONAL_VIP);
		}
		desconto =  desconto - adicional;
		BigDecimal arredondado =  new BigDecimal(desconto).setScale(2, RoundingMode.HALF_UP);
		return arredondado.doubleValue();
	}

	/**
	 * Metodo que retorna o bonus referente ao tipo vip
	 */
	@Override
	public double bonus() {
		return BONUS_VIP;
	}
	
	/**
	 * Metodo que retorna o adicional do desconto de um cartao vip
	 */
	@Override
	public double adicionalDesconto() {
		return ADICIONAL_VIP;
	}

	/**
	 * Metodo que retorna o bonus adicional
	 * referente ao tipo vip
	 */
	@Override
	public double adicionalBonus() {
		return 0;
	}
	
	/**
	 * Metodo que define o valor final do gasto a partir do valor
	 * da taxa especifica do tipo vip
	 */
	@Override
	public double pagaDividasGastos(double qtdPontos) {
		double valorConvertido = TAXA_CONVERTE_FIDELIDADE * qtdPontos;
		double adicional = (int)(qtdPontos/10) * TAXA_CONVERTE_FIDELIDADE_BONUS;
		double valorFinal = valorConvertido + adicional;
		return valorFinal;
	}

}
