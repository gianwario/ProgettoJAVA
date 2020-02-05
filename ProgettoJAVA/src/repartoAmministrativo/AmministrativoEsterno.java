package repartoAmministrativo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import eccezioni.FondiInsufficientiException;
import esterni.Commissione;

public class AmministrativoEsterno extends RepartoAmministrativo {

	/**
	 * La classe che rappresenta la sezione del reparto amministativo che si occupa
	 * dell'interazione con gli esterni. Apre e chiude commissioni di lavoro,
	 * rifornisce i materiali ai magazzini e ottiene i permessi di costruzione dagli
	 * enti locali
	 */

	private ArrayList<Commissione> listaCommissioni;

	public AmministrativoEsterno(double fondi) {

		super(fondi);
		listaCommissioni = new ArrayList<Commissione>();

	}

	/**
	 * Paga i permessi di costruzione all'ente rendendoli disponibili per quella
	 * commissione, utilizzando i fondi del reparto
	 * 
	 * @param commissione indica la commissione di cui ottenere i permessi
	 */
	public void pagaPermessi(Commissione commissione) {

		try {
			effettuaSpesa(commissione.getPrezzoPermessi());
			commissione.setOttenimentoPermessi(true);
		} catch (FondiInsufficientiException e) {

			JOptionPane.showMessageDialog(null, "Fondi insufficienti!", "Errore", JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}

	}

	/**
	 * Aggiunge una nuova commissione alla lista delle commissioni
	 * 
	 * @param commissione commissione ricevuta da aggiungere alla lista
	 */

	public void riceviCommissione(Commissione commissione) {

		if (commissione == null)
			return;
		else
			listaCommissioni.add(commissione);
	}

	/**
	 * Aggiunge ai fondi del reparto tutti gli incassi delle commissioni completate
	 * e le rimuove dalla lista delle commissioni
	 */
	public void chiudiCommissioni() {

		for (Commissione c : listaCommissioni) {

			if (c.getCompletamento() == true)
				aggiungiFondi(c.getPagamento());
			listaCommissioni.remove(c);
		}

	}

	/**
	 * Chiude ed effettua l'incasso di una singola commissione
	 * passata come parametro
	 * 
	 * @param commissione commissione da chiudere
	 */
	public void chiudiCommissioni(Commissione commissione) {

		if (commissione == null)
			return;
		
		if (listaCommissioni.contains(commissione)) {

			if (commissione.getCompletamento() == true) {
				aggiungiFondi(commissione.getPagamento());
				listaCommissioni.remove(commissione);
			}
		}
	}

}
