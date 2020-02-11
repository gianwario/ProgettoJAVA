package repartoAmministrativo;

import java.util.ArrayList;

import eccezioni.FondiInsufficientiException;
import personale.Dipendente;

public class AmministrativoInterno extends RepartoAmministrativo {

	ArrayList<Dipendente> personale;

	/**
	 * Sezione interna del reparto amministrativo che gestisce i dipendenti. Si
	 * occupa dell'assunzione, del licenziamento e dei pagamenti.
	 * 
	 * @param i fondi con cui parte il dipartimento interno
	 */
	public AmministrativoInterno(double fondi) {
		super(fondi);
		personale = new ArrayList<Dipendente>();
	}

	/**
	 * paga lo stipendio a tutti i dipendenti con stato non pagato
	 */
	public void effettuaPagamento() {

		for (Dipendente p : personale) {
			if (!p.controllaStatoPagamento()) {
				try {
					effettuaSpesa(p.pagaDipendente());
				} catch (FondiInsufficientiException e) {

					System.out.println(e);
				}
			}
		}
	}

	/**
	 * paga lo stipendipo ad un singolo dipendente
	 */
	public void effettuaPagamento(Dipendente d) {
		for (Dipendente p : personale) {
			if ((p.getNome().equals(d.getNome())) && (p.getCognome().equals(d.getCognome()))) {
				if (!p.controllaStatoPagamento()) {
					effettuaSpesa(p.pagaDipendente());

				}
			}
		}
	}

	/**
	 * @param d dipendente da assumere
	 */
	public void assumiDipendente(Dipendente d) {
		personale.add(d);
	}

	/**
	 * d dipendente da licenziare
	 */
	public void licenziaDipendente(Dipendente d) {
		for (Dipendente p : personale) {
			if ((p.getNome().equals(d.getNome())) && (p.getCognome().equals(d.getCognome()))) {
				personale.remove(p);
			}
		}
	}

	public ArrayList<Dipendente> listaDipendenti() {
		return (ArrayList<Dipendente>) personale.clone();
	}
	
	public Dipendente getDipendente(int i) {
		return personale.get(i).clone();
	}

	public boolean esisteDipendente(Dipendente d) {
		return personale.contains(d);
	}
	
	public void resettaStatoPagamenti() {
		for (Dipendente p : personale) {
			p.resettaStatoPagamento();
		}
	}

	public String toString() {
		String ret = super.toString() + "[ lista dipendenti : \n";
		for (Dipendente d : personale)
			ret = ret + "	" + d.toString() + "\n";
		ret = ret + "]";
		return ret;
	}

}
