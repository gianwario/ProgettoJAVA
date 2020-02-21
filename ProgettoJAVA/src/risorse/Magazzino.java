package risorse;

import java.io.Serializable;
import java.util.ArrayList;
import eccezioni.CapacitaInsufficienteException;

public class Magazzino implements Serializable{

	private double capacitaMax;
	private double capacitaOccupata;
	private ArrayList<Prodotto> listaProdotti;
	
	/**
	 * La classe rappresenta il concetto di magazzino che conserva i prodotti acquistati dai
	 * fornitori che serviranno ai lavori di cantiere
	 * 
	 * @param max definisce il volume massimo di prodotti che il magazzino puo contenere
	 */

	public Magazzino(double max) {

		capacitaMax = max;
		capacitaOccupata = 0;
		listaProdotti = new ArrayList<Prodotto>();

	}

	public double getCapacitaMax() {
		return capacitaMax;
	}

	public double getCapacitaOccupata() {
		return capacitaOccupata;
	}

	public ArrayList<Prodotto> getListaProdotti() {
		return listaProdotti;
	}
	
	/** 
	 * Aggiunge un prodotto al magazzino se la capacita rimasta è sufficiente
	 * 
	 * @param p prodotto da aggiungere al magazzino in seguito all'acquisto
	 * @throws CapacitaInsufficienteException lanciata se 
	 */
	public void aggiungiProdotto(Prodotto p) throws CapacitaInsufficienteException {
		
		if (p == null)
			return;

		if (capacitaOccupata + p.getVolume() <= capacitaMax) {
			listaProdotti.add(p);
			capacitaOccupata += p.getVolume();
		}

		else
			throw new CapacitaInsufficienteException("Capacita insufficiente!");
	}
	
	/**
	 * Rimuove un prodotto dal magazzino se questo è presente e lo restituisce
	 * 
	 * @param p prodotto da prelevare se questo è presente
	 * @return il prodotto richiesto in seguito alla rimozione
	 */
	public Prodotto prelevaProdotto(Prodotto p) {

		if (p == null || !listaProdotti.contains(p))
			return null;

		else {

			Prodotto p2 = p.clone();
			capacitaOccupata -= p.getVolume();
			listaProdotti.remove(p);
			return p2;
		}

	}

	public String toString() {

		String s1 = getClass().getName() + "[capacita massima = " + capacitaMax + ", capacita occupata = "
				+ capacitaOccupata + ", lista prodotti: \n";
		String s2 = "";

		for (Prodotto p : listaProdotti)
			s2 += p.toString() + "\n";

		return s1 + s2 + "]";
	}
}
