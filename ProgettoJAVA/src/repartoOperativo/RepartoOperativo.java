package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import eccezioni.AperturaCantiereInvalidaException;
import esterni.Commissione;
import personale.Dipendente;
import personale.Operaio;
import personale.Responsabile;
import risorse.Macchinario;
import risorse.Magazzino;
import risorse.Prodotto;
import utils.Selezionabile;
import utils.Selezionatore;

public class RepartoOperativo implements Serializable {

	private ArrayList<Cantiere> cantieri;

	/**
	 * La classe che rappresenta il reparto operativo che si occupa dell'apertura e
	 * della chiusura di cantieri per svolgere i lavori richiesti dalle commissioni
	 * 
	 * @param magazzino il magazzino dell'azienda da cui prelevare i materiali per i
	 *                  lavori
	 */

	public RepartoOperativo() {
		cantieri = new ArrayList<Cantiere>();
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
			Commissione commissione, Magazzino magazzino) throws AperturaCantiereInvalidaException {

		if (commissione.getOttenimentoPermessi()) {
			cantieri.add(new Cantiere(responsabile, valore, commissione, scaricaDaMagazzino(materiale, magazzino)));
			
		}

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


	/**
	 * Data una lista di prodotti, il metodo li preleva dal magazzino e li
	 * restituisce
	 * 
	 * @param prodotti prodotti da prelevare dal magazzino
	 * @return lista di prodotti prelevati dal magazzino
	 */
	public ArrayList<Prodotto> scaricaDaMagazzino(ArrayList<Prodotto> prodotti, Magazzino magazzino) {

		ArrayList<Prodotto> res = new ArrayList<Prodotto>();

		for (Prodotto p : prodotti)
			if (magazzino.getListaProdotti().contains(p))
				res.add(magazzino.prelevaProdotto(p));

		return res;
	}

	public ArrayList<Prodotto> getProdottiDalMagazzino(Magazzino magazzino) {
		return magazzino.getListaProdotti();
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
	
	public ArrayList<Cantiere> selezionaCantieri(Selezionabile<Cantiere> s) {
		
		Selezionatore selezione = new Selezionatore<Cantiere>(cantieri, s);
		return selezione.seleziona();
	}


	public String toString() {

		return getClass().getName() + "[Cantieri : " + cantieri.toString() + "]";
	}
}
