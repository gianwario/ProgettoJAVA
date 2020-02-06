package personale;

public class Operaio extends Dipendente {

	final static double STIPENDIO_BASE = 350;
	final static double PERCENTUALE_PATENTE_B = 2;
	final static double PERCENTUALE_PATENTE_C = 4;
	final static double PERCENTUALE_PATENTE_D = 8;

	private String patente;
	private boolean conducente;

	public Operaio(String nome, String cognome, String patente, boolean conduce) {
		super(nome, cognome);
		patente = patente;
		conducente = conduce;
	}

	/**
	 * Override del metodo 'pagaDipendente' della classe astratta dipendente. Paga
	 * lo stipendio base ad un operaio con un bonus nel caso sia assegnato come
	 * conducente ad un mezzo . Lo stipendio base di un operaio è di 350euro a
	 * settimana, il bonus è calcolato in base a se guida o meno un mezzo e la
	 * patente che ha.
	 * @return l'importo da pagare al dipendente
	 */
	public double pagaDipendente() {
		double stipendio = STIPENDIO_BASE;
		if (conducente) {
			if (patente == "B")
				stipendio = stipendio + ((stipendio * PERCENTUALE_PATENTE_B) / 100);
			else if (patente == "C")
				stipendio = stipendio + ((stipendio * PERCENTUALE_PATENTE_C) / 100);
			else if (patente == "D")
				stipendio = stipendio + ((stipendio * PERCENTUALE_PATENTE_D) / 100);
		}
		return stipendio;
	}

	public String getPatente() {
		return patente;
	}

	public void modificaPatente(String patente) {
		this.patente = patente;
	}

	public boolean isConducente() {
		return conducente;
	}

	public void setConducente(boolean conducente) {
		this.conducente = conducente;
	}

	public String toString() {
		return super.toString() + "[ patente : " + patente + ", conducente : " + conducente + " ]";
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		Operaio op = (Operaio) o;
		return (conducente == op.conducente) && (patente.equals(op.patente));
	}

	public Operaio clone() {
		return (Operaio) super.clone();
	}

}
