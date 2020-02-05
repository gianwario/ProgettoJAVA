package personale;

public class Impiegato extends Dipendente {

	final static double ORARIO_BASE = 12.5;
	final static double ORARIO_BONUS = 15;
	
	private int oreSettimanali;

	public Impiegato(String nome, String cognome, int ore) {
		super(nome, cognome);
		oreSettimanali = ore;
	}

	/**
	 * Override del metodo 'pagaDipendente' della classe astratta dipendente.
	 * Paga lo stipendio ad un impiegato in base al numero di ore effettuate.
	 * Le ore base sono 30(possono essere anche di meno) pagate a 12,50euro l'ora,
	 * una volta superate invece le ore vengono considerate straordinari, pagati di più.
	 * @return l'importo da pagare al dipendente
	 */
	public double pagaDipendente() {
		double stipendio;
		setDipendentePagato();
		if(oreSettimanali <= 30)
			stipendio=oreSettimanali*ORARIO_BASE;
		else
			stipendio=(30 * ORARIO_BASE)+((oreSettimanali-30)*ORARIO_BONUS);

		return stipendio;
	}

	/**
	 * @return numero di ore settimanali di un impiegato
	 */
	public int getOreSettimanali() {
		return oreSettimanali;
	}

	/**
	 * modifica il numero di ore settimanali di un impiegato
	 * @param nuovo orario
	 */
	public void setOreSettimanali(int oreSettimanali) {
		this.oreSettimanali = oreSettimanali;
	}
	
	public String toString() {
		return super.toString()+"[ numeroOreSettimanali : "+oreSettimanali+" ]";
	}

}
