package restaurante;

import java.util.Comparator;

public class ComparaNome implements Comparator<Refeicao>{

	@Override
	public int compare(Refeicao ref1, Refeicao ref2) {
		return ref1.getNome().compareTo(ref2.getNome());
	}

}
