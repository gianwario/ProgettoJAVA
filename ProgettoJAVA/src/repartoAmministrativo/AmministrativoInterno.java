package repartoAmministrativo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
			if (p.controllaStatoPagamento() == false) {
				try {
					effettuaSpesa(p.checkPaga());
					p.setDipendentePagato();
				} catch (FondiInsufficientiException e) {

					JOptionPane.showMessageDialog(null, "Fondi insufficienti!", "Errore", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		}
	}

	/**
	 * paga lo stipendio ad un singolo dipendente con stato non pagato
	 * 
	 * @param d dipendente a cui pagare lo stipendio
	 */
	public void effettuaPagamento(Dipendente d) {
		if(personale.contains(d) && !d.controllaStatoPagamento()) {
					effettuaSpesa(d.checkPaga());
					d.setDipendentePagato();
		}
	}

	/**
	 * Aggiunge un dipendente alla lista del personale
	 * 
	 * @param d dipendente da assumere
	 */
	public void assumiDipendente(Dipendente d) {
		personale.add(d);
	}

	/**
	 * Rimuove un dipendente dalla lista del personale d dipendente da licenziare
	 */
	public void licenziaDipendente(Dipendente d) {
		if(personale.contains(d))
			personale.remove(d);
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

	/**
	 * Setta a false lo stato di pagamento di tutti i dipendenti presenti nella
	 * lista del personale
	 * 
	 */
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
