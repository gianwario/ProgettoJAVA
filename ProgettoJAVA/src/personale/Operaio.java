package personale;

public class Operaio extends Dipendente {

	final static double STIPENDIO_BASE = 350;
	final static double PERCENTUALE_PATENTE_B = 2;
	final static double PERCENTUALE_PATENTE_C = 4;
	final static double PERCENTUALE_PATENTE_D = 8;

	private String patente;
	private boolean conducente;

	public Operaio(String nome, String cognome, String patente) {
		super(nome, cognome);
		this.patente = patente;
		conducente = false;
	}

	/**
	 * Override del metodo 'checkPaga' della classe astratta dipendente. Calcola
	 * lo stipendio da pagare ad un operaio con un bonus nel caso sia assegnato come
	 * conducente ad un mezzo . Lo stipendio base di un operaio è di 350euro a
	 * settimana, il bonus è calcolato in base a se guida o meno un mezzo e la
	 * patente che ha.
	 * 
	 * @return l'importo da pagare al dipendente
	 */
	public double checkPaga() {
		
		if (conducente) {
			if (patente == "B")
				return STIPENDIO_BASE + ((STIPENDIO_BASE * PERCENTUALE_PATENTE_B) / 100);
			if (patente == "C")
				return STIPENDIO_BASE + ((STIPENDIO_BASE * PERCENTUALE_PATENTE_C) / 100);
			if (patente == "D")
				return STIPENDIO_BASE + ((STIPENDIO_BASE * PERCENTUALE_PATENTE_D) / 100);
		}
		return STIPENDIO_BASE;
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
	
	/** Indica che l'operaio è conducente di un macchinario
	 * @param conducente true se conduce un veicolo altrimenti false
	 */
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
	
	public String stampa() {
		
		return "OPERAIO: " + super.stampa() + ", patente  = " + patente + ", conducente = " + conducente + "\n";
	}


}
