package risorse;

import personale.Operaio;

public class Macchinario extends Prodotto {

	private String tipoPatente;
	private boolean guidato;

	/**
	 * La classe, specializzazione di prodotto, rappresenta l'astrazione di un
	 * macchinario da lavoro necessario ai cantieri per compiere i lavori
	 * 
	 * @param nome    nome del macchinario
	 * @param prezzo  costo del macchinario per la vendita
	 * @param volume  volume del macchinario in m^3	 *
	 * @param patente patente necessaria per poter guidare il macchinario
	 * 
	 */

	public Macchinario(String nome, double prezzo, double volume, String patente) {

		super(nome, prezzo, volume);
		tipoPatente = patente;;

	}

	public String getTipoPatente() {
		return tipoPatente;
	}

	public void setTipoPatente(String tipoPatente) {
		this.tipoPatente = tipoPatente;
	}

	public boolean isGuidato() {
		return guidato;
	}

	public void setGuidato(boolean guidato) {
		this.guidato = guidato;
	}

	public Macchinario clone() {

		return (Macchinario) super.clone();
	}

	public boolean equals(Object object) {

		if (!super.equals(object))
			return false;

		Macchinario m2 = (Macchinario) object;

		return (this.tipoPatente.equals(m2.tipoPatente));
	}

	public String toString() {

		return super.toString() + "tipo patente = " + tipoPatente + "]";
	}

}
