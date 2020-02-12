package repartoAmministrativo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import eccezioni.CapacitaInsufficienteException;
import eccezioni.FondiInsufficientiException;
import esterni.Commissione;
import esterni.Fornitore;
import risorse.Macchinario;
import risorse.Magazzino;
import risorse.Prodotto;

public class AmministrativoEsterno extends RepartoAmministrativo {

	private ArrayList<Commissione> listaCommissioni;
	private Magazzino magazzino;
	private ArrayList<Fornitore> listaFornitori;

	/**
	 * La classe che rappresenta la sezione del reparto amministativo che si occupa
	 * dell'interazione con gli esterni. Apre e chiude commissioni di lavoro,
	 * rifornisce i materiali ai magazzini e ottiene i permessi di costruzione dagli
	 * enti locali
	 * 
	 * @param fondi     fondi iniziali
	 * @param magazzino il magazzino che il reparto gestisce
	 */

	public AmministrativoEsterno(double fondi, Magazzino magazzino) {

		super(fondi);
		this.magazzino = magazzino;
		listaCommissioni = new ArrayList<Commissione>();
		listaFornitori = new ArrayList<Fornitore>();

	}

	public ArrayList<Fornitore> getListaFornitori() {

		return listaFornitori;
	}

	public ArrayList<Commissione> getListaCommissioni() {

		return listaCommissioni;
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
	 * Aggiunge ai fondi del reparto esterno la metà di tutti gli incassi delle
	 * commissioni completate e le rimuove dalla lista delle commissioni
	 */
	public void chiudiCommissioni() {

		for (Commissione c : listaCommissioni) {

			if (c.getCompletamento() == true) {
				aggiungiFondi(c.getPagamento() / 2);
				listaCommissioni.remove(c);
			}
		}

	}

	/**
	 * Chiude ed effettua l'incasso della metà dell'importo di una singola
	 * commissione passata come parametro
	 * 
	 * @param commissione commissione da chiudere
	 */
	public void chiudiCommissioni(Commissione commissione) {

		if (commissione == null)
			return;

		if (listaCommissioni.contains(commissione)) {

			if (commissione.getCompletamento() == true) {
				aggiungiFondi(commissione.getPagamento() / 2);
				listaCommissioni.remove(commissione);
			}
		}
	}

	public void aggiungiFornitore(Fornitore f) {

		if (f == null)
			return;
		listaFornitori.add(f);

	}

	public void rimuoviFornitore(Fornitore f) {

		if (f == null || !listaFornitori.contains(f))
			return;

		else
			listaFornitori.remove(f);
	}

	public void pagaProdotti(ArrayList<Prodotto> list) {

		double sum = 0;
		for (Prodotto p : list)
			sum += p.getPrezzo();

		effettuaSpesa(sum);

	}

	public void pagaProdotti(Prodotto p) {

		if (p == null)
			return;
		effettuaSpesa(p.getPrezzo());

	}

	public void acquistaDaFornitore(Fornitore f, Prodotto p) {

		if (f == null || p == null || !f.getCatalogo().contains(p) || !listaFornitori.contains(f))
			return;

		pagaProdotti(p);
		try {
			magazzino.aggiungiProdotto(p);
		} catch (CapacitaInsufficienteException e) {
			
			JOptionPane.showMessageDialog(null, "Capacità magazzino insufficiente!", "CapacitaInsufficienteException", JOptionPane.ERROR_MESSAGE,
					null);
			e.printStackTrace();
		}
	}


	public String toString() {

		return super.toString() + " [lista commissioni: \n" + listaCommissioni + "\nMagazzino: \n" + magazzino
				+ "\nLista fornitori: \n" + listaFornitori + "]";
	}

}
