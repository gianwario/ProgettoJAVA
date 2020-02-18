package repartoAmministrativo;

import java.io.Serializable;

import eccezioni.FondiInsufficientiException;
import eccezioni.ImportoNegativoException;

public abstract class RepartoAmministrativo implements Serializable {

	private double fondi;

	/**
	 * La classe che rappresenta il reparto amministrativo si occupa della gestione
	 * del capitale dell'azienda e viene specializzato in due sottoreparti, il
	 * reparto interno e quello esterno
	 * 
	 * @param fondi i fondi iniziali di un reparto
	 */
	public RepartoAmministrativo(double fondi) {
		this.fondi = fondi;
	}

	/**
	 * Un reparto riceve un pagamento e viene aggiunta una determinata somma ai
	 * fondi
	 * 
	 * @param fondi l'importo guadagnato
	 */
	public void aggiungiFondi(double importo) {
		if(importo > 0)
			this.fondi += importo;
		else
			throw new ImportoNegativoException();
	}

	/**
	 * Un reparto effettua una spesa e viene rimosso dal fondo l'importo speso
	 * 
	 * @param fondi l'importo da rimuovere
	 */
	public void effettuaSpesa(double importo) {
		if (importo > 0) {
			if ((this.fondi - importo) >= 0) {
				this.fondi -= importo;
			} else {
				throw new FondiInsufficientiException("Impossibile effettuare la transazione, fondi non disponibili\n");
			}
		} else {
			throw new ImportoNegativoException("L'importo inserito non è valido");
		}
	}

	/**
	 * @return i fondi attuali del reparto
	 */
	public double controllaFondi() {
		return fondi;
	}

	public String toString() {
		return getClass().getName() + "[fondi : " + fondi + "]";
	}

}
