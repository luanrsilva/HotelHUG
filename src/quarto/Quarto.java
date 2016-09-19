package quarto;

import exceptions.IdInvalidoException;

public class Quarto {

	private String id;
	private double diaria;
	private TipoQuarto tipo;

	public Quarto(String id, String tipo) throws IdInvalidoException {
		if (id == null || id.trim().isEmpty()) {
			throw new IdInvalidoException(id);
		}
		this.tipo = associaTipo(tipo);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDiaria() {
		return tipo.getValorQuarto();
	}

	public TipoQuarto getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuarto tipo) {
		this.tipo = tipo;
	}

	private TipoQuarto associaTipo(String tipo) {

		switch (tipo.toUpperCase()) {
		case "PRESIDENCIAL":
			return TipoQuarto.PRESIDENCIAL;

		case "LUXO":
			return TipoQuarto.LUXO;

		case "SIMPLES":
			return TipoQuarto.SIMPLES;

		default:
			break;

		}
		return null;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quarto other = (Quarto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
