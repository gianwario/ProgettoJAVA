package azienda;

import repartoAmministrativo.AmministrativoInterno;
import repartoOperativo.RepartoOperativo;
import risorse.Magazzino;

import java.io.Serializable;

import repartoAmministrativo.AmministrativoEsterno;

public class Azienda implements Serializable {

	private AmministrativoInterno interno;
	private AmministrativoEsterno esterno;
	private RepartoOperativo operativo;
	private Magazzino magazzino;

	/**
	 * L'azienda racchiude in se tutti i reparti
	 * 
	 * @param capitale il fondo iniziale dell'azienda che verrà diviso fra i due
	 *                 reparti(esterno e interno)
	 * @param vol il volume in m3 del magazzino dell'azienda
	 */
	public Azienda(int capitale, int vol) {
		interno = new AmministrativoInterno(capitale / 2);
		magazzino = new Magazzino(vol);
		esterno = new AmministrativoEsterno(capitale / 2);
		operativo = new RepartoOperativo();
	}

	
	/**
	 * Divide i fondi totali equamente fra le due sezioni di reparto amministrativo 
	 */
	public void equilibriaFondi() {
		double f = esterno.controllaFondi() + interno.controllaFondi();
		esterno.effettuaSpesa(getFondiEsterno());
		interno.effettuaSpesa(getFondiInterno());
		esterno.aggiungiFondi(f / 2);
		interno.aggiungiFondi(f / 2);
	}

	public AmministrativoInterno getInterno() {
		return interno;
	}

	public AmministrativoEsterno getEsterno() {
		return esterno;
	}

	public RepartoOperativo getOperativo() {
		return operativo;
	}

	public Magazzino getMagazzino() {
		return magazzino;
	}

	public double getFondiTotali() {
		return interno.controllaFondi() + esterno.controllaFondi();
	}

	public double getFondiEsterno() {
		return esterno.controllaFondi();
	}

	public double getFondiInterno() {
		return interno.controllaFondi();
	}
	public String toString() {
		return getClass().getName() + "\n " + esterno.toString() + "\n " + interno.toString() + "\n "
				+ operativo.toString();
	}
}
