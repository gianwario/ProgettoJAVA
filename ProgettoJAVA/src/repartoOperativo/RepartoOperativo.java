package repartoOperativo;

import java.util.ArrayList;

import esterni.Commissione;
import personale.Responsabile;
import risorse.Prodotto;

public class RepartoOperativo {

	private ArrayList<Cantiere> cantieri;
	private ArrayList<Prodotto> materiali;

	public RepartoOperativo() {
		cantieri = new ArrayList<Cantiere>();
		materiali = new ArrayList<Prodotto>();
	}

	public void apriCantiere(int valore, Responsabile responsabile, ArrayList<Prodotto> prodotti, Commissione commissione) {
		cantieri.add(new Cantiere(responsabile, valore, commissione));
		materiali = prodotti;
	}
	

}
