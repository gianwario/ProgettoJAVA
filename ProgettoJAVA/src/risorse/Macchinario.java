package risorse;

import personale.Operaio;

public class Macchinario extends Prodotto {

	private String tipoPatente;
	private Operaio conducente;

	/**
	 * La classe, specializzazione di prodotto, rappresenta l'astrazione di un
	 * macchinario da lavoro necessario ai cantieri per compiere i lavori
	 * 
	 * @param nome    nome del macchinario
	 * @param prezzo  costo del macchinario per la vendita
	 * @param volume  volume del macchinario in m^3
	 *
	 * @param patente patente necessaria per poter guidare il macchinario
	 * @param operaio conducende del macchinario
	 */

	public Macchinario(String nome, double prezzo, double volume, String patente, Operaio operaio) {

		super(nome, prezzo, volume);
		tipoPatente = patente;
		conducente = operaio;

	}

	public String getTipoPatente() {
		return tipoPatente;
	}

	public void setTipoPatente(String tipoPatente) {
		this.tipoPatente = tipoPatente;
	}

	public Operaio getConducente() {
		return conducente;
	}

	public void setConducente(Operaio operaio) {
		conducente = operaio;
	}

	public Macchinario clone() {

		Macchinario m2 = (Macchinario) super.clone();
		m2.conducente = conducente.clone();
		return m2;

	}

	public boolean equals(Object object) {

		if (object == null)
			return false;

		Macchinario m2 = (Macchinario) object;

		return (this.getClass() == m2.getClass() && super.equals(m2) && this.tipoPatente.equals(m2.tipoPatente)
				&& this.conducente.equals(m2.conducente));
	}

	public String toString() {

		return super.toString() + ", del tipo: " + getClass().getName() + "[tipo patente = " + tipoPatente
				+ ", conducente = " + conducente.toString() + "]";
	}

}
