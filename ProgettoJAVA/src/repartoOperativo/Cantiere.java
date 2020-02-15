package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import esterni.Commissione;
import repartoOperativo.Squadra;
import risorse.Macchinario;
import risorse.Prodotto;
import personale.Dipendente;
import personale.Operaio;
import personale.Quadro;
import personale.Responsabile;

public class Cantiere implements Cloneable, Serializable {

	private Commissione commissione;
	private ArrayList<Squadra> squadre;
	private Responsabile responsabile;
	private ArrayList<Prodotto> materiali;
	private int valore;

	/**
	 * La classe che rappresenta il concetto di cantiere, all'interno del quale
	 * vengono svolti i lavori per completare una commissione di cui si hanno i
	 * permessi di costruzione. Ha delle squadre di operai che vi lavorano e
	 * un solo dipendente che ricopre il ruolo di
	 * responsabile, il quale è scelto tra i quadri se il valore del cantiere è <
	 * 500000, tra un dirigente altrimenti
	 * 
	 * @param responsabile dipendente responsabile del cantiere
	 * @param valore       fondi del cantiere
	 * @param commissione  commissione che ha richiesto l'apertura del cantiere
	 * @param materiali    materiali affidati al cantiere per i lavori
	 */
	public Cantiere(Responsabile responsabile, int valore, Commissione commissione, ArrayList<Prodotto> materiali) {
		this.commissione = commissione;
		this.materiali = materiali;
		squadre = new ArrayList<Squadra>();
		this.valore = valore;
		Dipendente d = (Dipendente) responsabile;
		if (d instanceof Quadro && valore >= 500000)
			throw new IllegalArgumentException(
					"Il responsabile di un cantiere con valore maggiore di 500k deve essere un dirigente");
		if(d instanceof Quadro) {
			Quadro q = (Quadro)d;
			q.setDirigente(true);
			this.responsabile=q;
		}
		else	
			this.responsabile = responsabile;
		d.occupaDipendente();

	}

	public int getValore() {
		return this.valore;
	}

	public void aggiungiSquadra(Squadra squadra) {
		squadra.occupaSquadra();
		squadre.add(squadra);
	}
	
	/**
	 * Setta a true lo stato di conducente di un operaio se non lo è già, se fa
	 * parte di una squadra di quel cantiere e se ha la patente necessaria 
	 * per guidarlo
	 * 
	 * @param m macchinario da guidare
	 * @param o operaio da assegnare al macchinario	 * 
	 * @return restituisce true se l'operazione va a buon fine, false altrimenti
	 */
	public boolean assegnaConducente(Macchinario m, Operaio o) {

		if(m == null | o == null || o.isConducente() == true)
			return false;

		for (Squadra s : squadre) {
			if(s.getOperai().contains(o) && o.getPatente().equals(m.getTipoPatente())) {					
				o.setConducente(true);
				return true;
			}	
		}		
		return false;
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

	public ArrayList<Prodotto> getMateriali() {
		return this.materiali;
	}

	public boolean getStatoCantiere() {
		return commissione.getCompletamento();
	}
	
	public String toString() {
		
		return getClass().getName() + "[Valore = " + valore + ", commissione = " + commissione + ", responsabile = "
				+ responsabile + "\nSquadre = " + squadre + "\nLista materiali: \n" + materiali + "\n";
	}
}
