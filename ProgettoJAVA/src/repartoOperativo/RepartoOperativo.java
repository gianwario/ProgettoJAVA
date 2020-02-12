package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.AperturaCantiereInvalidaException;
import esterni.Commissione;
import personale.Dipendente;
import personale.Responsabile;
import risorse.Magazzino;
import risorse.Prodotto;

public class RepartoOperativo implements Serializable {

	private ArrayList<Cantiere> cantieri;
	private Magazzino magazzino;

	/**
	 * La classe che rappresenta il reparto operativo che si occupa dell'apertura e
	 * della chiusura di cantieri per svolgere i lavori richiesti dalle commissioni
	 * 
	 * @param magazzino il magazzino dell'azienda da cui prelevare i materiali per i
	 *                  lavori
	 */

	public RepartoOperativo(Magazzino magazzino) {
		cantieri = new ArrayList<Cantiere>();
		this.magazzino = magazzino;
	}

	/**
	 * Apre un cantiere per i lavori, istanziando un nuovo oggetto di tipo cantiere.
	 * Lancia AperturaCantiereInvalidaException se la commissione passata non ha i
	 * permessi di costruzione abilitati
	 * 
	 * @param valore       fondi delegati al cantiere
	 * @param responsabile dipendente che ricopre il ruolo di responsabile di
	 *                     cantiere
	 * @param materiale    lista dei materiali delegati al cantiere
	 * @param commissione  la commissione che ha richiesto l'apertura del cantiere
	 */
	public void apriCantiere(int valore, Responsabile responsabile, ArrayList<Prodotto> materiale,
			Commissione commissione) throws AperturaCantiereInvalidaException {

		if (commissione.getOttenimentoPermessi())
			cantieri.add(new Cantiere(responsabile, valore, commissione, scaricaDaMagazzino(materiale)));

		else
			throw new AperturaCantiereInvalidaException("Permessi non validi per aprire il cantiere!");
	}

	/**
	 * Chiude un cantiere e completa la commissione che ne aveva richiesto
	 * l'apertura. Libera tutti i dipendenti occupati nel cantiere
	 * 
	 * @param c cantiere da chiudere
	 */
	public void chiudiCantiere(Cantiere c) {

		if (cantieri.contains(c)) {
			Dipendente d = (Dipendente) c.getResponsabile();
			d.liberaDipendente();
			for (Squadra s : c.getSquadre()) {
				s.liberaSquadra();
			}
			c.getCommissione().setCompletamento(true);
			cantieri.remove(c);
		}
	}

	public Cantiere getCantiere(int i) {
		return cantieri.get(i);
	}

	public Magazzino getMagazzino() {
		return this.magazzino;
	}

	/**
	 * Data una lista di prodotti, il metodo li preleva dal magazzino e li
	 * restituisce
	 * 
	 * @param prodotti prodotti da prelevare dal magazzino
	 * @return lista di prodotti prelevati dal magazzino
	 */
	public ArrayList<Prodotto> scaricaDaMagazzino(ArrayList<Prodotto> prodotti) {

		ArrayList<Prodotto> res = new ArrayList<Prodotto>();

		for (Prodotto p : prodotti)
			if (this.magazzino.getListaProdotti().contains(p))
				res.add(this.magazzino.prelevaProdotto(p));

		return res;
	}

	public ArrayList<Prodotto> getProdottiDalMagazzino() {
		return this.magazzino.getListaProdotti();
	}

	public ArrayList<Cantiere> getCantieri() {
		return this.cantieri;
	}

	public int getNumeroCantieriAperti() {
		int num = 0;
		for (Cantiere c : cantieri) {
			num++;
		}
		return num;
	}

	public String toString() {

		return getClass().getName() + "[Cantieri : " + cantieri.toString() + ", magazzino : " + magazzino.toString()
				+ "]";
	}
}
