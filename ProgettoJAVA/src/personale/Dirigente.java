package personale;

public class Dirigente extends Dipendente implements Responsabile {

	final static double STIPENDIO_BASE = 425;

	private int anniDiServizio;

	public Dirigente(String nome, String cognome) {
		super(nome, cognome);
		anniDiServizio = 0;
	}

	/**
	 * Override del metodo 'pagaDipendente' della classe astratta dipendente. Paga
	 * lo stipendio ad un dirigente con un bonus in base agli anni di servizio. Lo
	 * stipendio base di un dirigente è di 425euro a settimana, il bonus aumenta
	 * dell'1% lo stipendio ogni anno di servizio.
	 * 
	 * @return l'importo da pagare al dipendente
	 */
	public double pagaDipendente() {
		setDipendentePagato();
		return STIPENDIO_BASE + ((STIPENDIO_BASE * anniDiServizio) / 100);
	}

	public int controllaAnniDiServizio() {
		return anniDiServizio;
	}
	
	/**
	 * Incrementa gli anni di servizio presso l'azienda
	 */
	public void nuovoAnnoDiServizio() {
		this.anniDiServizio += 1;
	}

	public String toString() {
		return super.toString() + "[ anni di servizio : " + anniDiServizio + " ]";
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		Dirigente op = (Dirigente) o;
		return (anniDiServizio == op.anniDiServizio);
	}

	public Dirigente clone() {
		return (Dirigente) super.clone();
	}
	
	public String stampa() {
		
		return "DIRIGENTE: " + super.stampa() + ", anni di servizio = " + anniDiServizio + "\n";
	}

}
