package azienda;

import repartoAmministrativo.AmministrativoInterno;
import repartoOperativo.RepartoOperativo;
import risorse.Magazzino;
import repartoAmministrativo.AmministrativoEsterno;

public class Azienda {
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

}
