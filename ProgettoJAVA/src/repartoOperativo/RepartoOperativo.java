package repartoOperativo;

import java.util.ArrayList;

import esterni.Commissione;
import personale.Dipendente;
import personale.Responsabile;
import risorse.Magazzino;
import risorse.Prodotto;

public class RepartoOperativo {

	private ArrayList<Cantiere> cantieri;
	private Magazzino magazzino;

	public RepartoOperativo(Magazzino magazzino) {
		cantieri = new ArrayList<Cantiere>();
		this.magazzino = magazzino;
	}

	public void apriCantiere(int valore, Responsabile responsabile, Commissione commissione) {
		ArrayList<Prodotto> prodotti = this.magazzino.getListaProdotti(); // Modifica con criteri
		cantieri.add(new Cantiere(responsabile, valore, commissione, prodotti));
	}

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
	
	public Magazzino getMagazzino() {
		return this.magazzino;
	}

	public ArrayList<Prodotto> getProdottiDalMagazzino() {
		return this.magazzino.getListaProdotti();
	}

	public ArrayList<Cantiere> getCantieri(){
		return this.cantieri;
	}

}
