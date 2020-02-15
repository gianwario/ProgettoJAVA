package personale;

public class Quadro extends Dipendente implements Responsabile {

	final static double STIPENDIO_BASE = 400;
	final static double BONUS_DIRIGENZA = 20;

	private boolean isDirigente;

	public Quadro(String nome, String cognome) {
		super(nome, cognome);
	}

	/**
	 * Override del metodo 'pagaDipendente' della classe astratta dipendente. Paga
	 * lo stipendio d un quadro, aggiungendo un bonus se egli dirige un cantiere. Lo
	 * stipendio base di un quadro è di 400euro a settimana, il bonus è di 20euro.
	 * 
	 * @return l'importo da pagare al dipendente
	 */
	public double pagaDipendente() {
		setDipendentePagato();
		return !isDirigente ? STIPENDIO_BASE : STIPENDIO_BASE + BONUS_DIRIGENZA;
	}
	
	public double checkPaga() {
		return !isDirigente ? STIPENDIO_BASE : STIPENDIO_BASE + BONUS_DIRIGENZA;
	}

	public boolean isDirigente() {
		return isDirigente;
	}

	public void setDirigente(boolean isDirigente) {
		this.isDirigente = isDirigente;
	}

	public String toString() {
		return super.toString() + "[ è dirigente : " + isDirigente + " ]";
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		Quadro q = (Quadro) o;
		return (isDirigente == q.isDirigente);
	}

	public Quadro clone() {
		return (Quadro) super.clone();
	}
	
	public String stampa() {
		
		return "QUADRO: " + super.stampa() + ", dirige cantiere  = " + isDirigente + "\n";
	}


}
