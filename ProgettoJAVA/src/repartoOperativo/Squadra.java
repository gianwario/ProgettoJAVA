package repartoOperativo;

import java.io.Serializable;
import java.util.ArrayList;

import personale.Operaio;
import personale.Quadro;

public class Squadra implements Serializable {

	private Quadro caposquadra;
	private ArrayList<Operaio> operai;

	public Squadra(Quadro caposquadra, ArrayList<Operaio> operai) {
		if(caposquadra.controllaStatoDipendente())
			throw new IllegalArgumentException("Il dipendente è già occupato");
		this.caposquadra = caposquadra;
		this.operai = operai;
	}

	public Quadro getCaposquadra() {
		return caposquadra;
	}

	public void setCaposquadra(Quadro caposquadra) {
		this.caposquadra = caposquadra;
	}

	public ArrayList<Operaio> getOperai() {
		return operai;
	}

	public void addOperai(ArrayList<Operaio> operai) {
		for (Operaio o : operai) {
			if (!operai.contains(o) && !o.controllaStatoDipendente()) {
				operai.add(o);
			}
		}
	}

	public boolean removeOperaio(Operaio o) {
		if (operai.contains(o))
			operai.remove(o);
		else
			return false;
		return true;
	}
	
	public void occupaSquadra() {
		caposquadra.occupaDipendente();

		for(Operaio o : operai) {
			o.occupaDipendente();
		}
	}
	
	public void liberaSquadra() {
		caposquadra.liberaDipendente();
		for(Operaio o : operai) {
			o.liberaDipendente();
		}
	}

}
