package risorse;

import personale.Operaio;

public class Macchinario extends Prodotto {

	private double capacitaPieno;
	private Operaio conducente;

	public Macchinario(String nome, double prezzo, double pieno, Operaio operaio) {

		super(nome, prezzo);
		capacitaPieno = pieno;
		conducente = operaio;
	}

	public double getCapacitaPieno() {
		return capacitaPieno;
	}

	public void setCapacitaPieno(double capacitaPieno) {
		this.capacitaPieno = capacitaPieno;
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

	public String toString() {

		return super.toString() + ", del tipo: " + getClass().getName() + "[capacita pieno = " + capacitaPieno
				+ ", conducente = $" + conducente.toString() + "]";
	}

}
