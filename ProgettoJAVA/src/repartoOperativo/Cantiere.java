package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import esterni.Commissione;
import repartoOperativo.Squadra;
import personale.Dipendente;
import personale.Quadro;
import personale.Responsabile;

public class Cantiere implements Cloneable, Serializable {

	private Commissione commissione;
	private ArrayList<Squadra> squadre;
	private Responsabile responsabile;
	private int valore;

	public Cantiere(Responsabile responsabile, int valore, Commissione commissione) {
		commissione = commissione;
		squadre = new ArrayList<Squadra>();
		this.valore= valore;
		Dipendente d = (Dipendente) responsabile;
		if(d instanceof Quadro && valore>=500000) 
			throw new IllegalArgumentException("Il responsabile di un cantiere con valore maggiore di 500k deve essere un dirigente");	
		this.responsabile = responsabile;
		
	}
	
	public int getValore() {
		return this.valore;
	}

	public void aggiungiSquadra(Squadra squadra) {
		squadra.occupaSquadra();
		squadre.add(squadra);
	}
	
	public void chiudiCantiere() {
		Dipendente d = (Dipendente) responsabile;
		d.liberaDipendente();
		for(Squadra s : squadre) {
			s.liberaSquadra();
		}
		commissione.setCompletamento(true);
		
	}
	
	public Commissione getCommissione() {
		return commissione;
	}

	public ArrayList<Squadra> getSquadre() {
		return squadre;
	}

	public Squadra getSquadra(int i) {
		return squadre.get(i);
	}
	
	public Dipendente getResponsabile() {
		return (Dipendente) responsabile;
	}

	
	
	
}
