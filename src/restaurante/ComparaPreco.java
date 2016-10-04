package restaurante;

import java.util.Comparator;

public class ComparaPreco implements Comparator<Refeicao> {

	@Override
	public int compare(Refeicao ref1, Refeicao ref2) {
		if (ref1.calculaPreco() < ref2.calculaPreco()){
			return -1;
		}
		else if (ref1.calculaPreco() > ref2.calculaPreco()){
			return 1;
		}
		return 0;
	}
}
