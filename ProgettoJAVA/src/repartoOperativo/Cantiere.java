package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import esterni.Commissione;
import repartoOperativo.Squadra;
import risorse.Prodotto;
import personale.Dipendente;
import personale.Quadro;
import personale.Responsabile;

public class Cantiere implements Cloneable, Serializable {

	private Commissione commissione;
	private ArrayList<Squadra> squadre;
	private Responsabile responsabile;
	private ArrayList<Prodotto> materiali;
	private int valore;

	public Cantiere(Responsabile responsabile, int valore, Commissione commissione, ArrayList<Prodotto> materiali) {
		this.commissione = commissione;
		this.materiali=materiali;
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

	
	public Commissione getCommissione() {
		return commissione;
	}

	public ArrayList<Squadra> getSquadre() {
		return squadre;
	}

	public Dipendente getResponsabile() {
		return (Dipendente) responsabile;
	}

	public ArrayList<Prodotto> getMateriali(){
		return this.materiali;
	}
	
	public boolean getStatoCantiere() {
		return commissione.getCompletamento();
	}
}
