package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import personale.Operaio;
import personale.Quadro;

public class Squadra implements Serializable {

	private Quadro caposquadra;
	private ArrayList<Operaio> operai;

	/**
	 * La classe rappresenta l'astrazione di squadra, un insieme di operai con un
	 * Quadro che ricopre il ruolo di caposquadra
	 * 
	 * @param caposquadra il quadro assegnato come caposquadra della squadra
	 * @param operai      lista di operai che compongono la squadra
	 */
	public Squadra(Quadro caposquadra, ArrayList<Operaio> operai) {
		if (caposquadra.controllaStatoDipendente())
			throw new IllegalArgumentException("Il dipendente è già occupato");
		this.caposquadra = caposquadra;
		this.operai = operai;
	}

	public Quadro getCaposquadra() {
		return caposquadra;
	}

	public ArrayList<Operaio> getOperai() {
		return operai;
	}

	/**
	 * Aggiunge una lista di operai a quelli che compongono la squadra
	 * 
	 * @param operai operai da aggiungere alla squadra
	 */
	public void addOperai(ArrayList<Operaio> operai) {
		for (Operaio o : operai) {
			if (!operai.contains(o) && !o.controllaStatoDipendente()) {
				operai.add(o);
			}
		}
	}

	/**
	 * Dato un operaio, questo viene rimosso dalla lista degli operai facente parti
	 * della squadra
	 * 
	 * @param o operaio da rimuovere dalla lista di operai della squadra
	 * @return restituisce valore true se l'operaio è presente in squadra e viene
	 *         rimosso, false altrimenti
	 */
	public boolean removeOperaio(Operaio o) {
		if (operai.contains(o))
			operai.remove(o);
		else
			return false;
		return true;
	}

	/**
	 * Assegna una squadra ai lavori per un cantiere e imposta a true lo stato di
	 * occupazione di ogni operaio facente parte della squadra
	 */
	public void occupaSquadra() {
		caposquadra.occupaDipendente();

		for (Operaio o : operai) {
			o.occupaDipendente();
		}
	}

	/**
	 * Libera una squadra dai lavori per un cantiere e imposta a false lo stato di
	 * occupazione di ogni operaio facente parte della squadra
	 */
	public void liberaSquadra() {
		caposquadra.liberaDipendente();
		for (Operaio o : operai) {
			o.liberaDipendente();
		}
	}
	
	public Squadra clone() {
		
		Squadra s2;
		try {
			s2 = (Squadra)super.clone();
			s2.caposquadra = this.caposquadra.clone();
			s2.operai = (ArrayList<Operaio>) this.operai.clone();
			return s2;
		} 
		catch (CloneNotSupportedException e) {
			JOptionPane.showMessageDialog(null, "Clone di SQUADRA fallita!", "Errore", 
					JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean equals(Object ob) {

		if (ob == null)
			return false;
		Squadra s2 = (Squadra) ob;
		return this.caposquadra.equals(s2.caposquadra) && this.operai.equals(s2.operai);
	}

	public String toString() {

		return getClass().getName() + "[caposquadra = " + caposquadra.toString() + ", lista operai: \n"
				+ operai.toString() + "]";
	}

}
