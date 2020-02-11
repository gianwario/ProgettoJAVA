package azienda;

import repartoAmministrativo.AmministrativoInterno;
import repartoOperativo.RepartoOperativo;
import risorse.Magazzino;

import java.io.Serializable;

import repartoAmministrativo.AmministrativoEsterno;

public class Azienda implements Serializable{
	final static double VOLUME_MAGAZZINO = 200;

	AmministrativoInterno interno;
	AmministrativoEsterno esterno;
	RepartoOperativo operativo;
	Magazzino magazzino = new Magazzino(VOLUME_MAGAZZINO);


	/**
	 * L'azienda racchiude in se tutti i reparti 
	 * @param capitale il fondo iniziale dell'azienda che verrà diviso fra i due
	 *                 reparti(esterno e interno)
	 */
	public Azienda(int capitale) {
		interno = new AmministrativoInterno(capitale / 2);
		esterno = new AmministrativoEsterno(capitale / 2, magazzino);
		operativo = new RepartoOperativo(magazzino);
	}
	
	public double getFondiTotali() {
		return interno.controllaFondi()+esterno.controllaFondi();
	}
	public double getFondiEsterno() {
		return esterno.controllaFondi();
	}
	public double getFondiInterno() {
		return interno.controllaFondi();
	}

	public AmministrativoInterno getInterno() {
		return interno;
	}

	public void setInterno(AmministrativoInterno interno) {
		this.interno = interno;
	}

	public AmministrativoEsterno getEsterno() {
		return esterno;
	}

	public void setEsterno(AmministrativoEsterno esterno) {
		this.esterno = esterno;
	}

	public RepartoOperativo getOperativo() {
		return operativo;
	}

	public void setOperativo(RepartoOperativo operativo) {
		this.operativo = operativo;
	}

	public Magazzino getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}

	public String toString() {
		return getClass().getName()+"\n "+esterno.toString()+"\n "+interno.toString()+"\n "+operativo.toString();
	}
}
