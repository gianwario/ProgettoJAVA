package azienda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LeggiScriviAzienda {

	Azienda a;

	public LeggiScriviAzienda() {
	}

	/**
	 * Scrive un azienda in un file
	 * @param fileName il path del file in cui memorizzare l'azienda
	 */
	public void scriviAzienda(String fileName) {
		File file = new File(fileName);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(a);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legge da un file un azienda
	 * @param fileName il path del file dal quale leggere l'azienda
	 */
	public void leggiAzienda(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				a = (Azienda) in.readObject();
				in.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Restituisce l'azienda letta dal file
	 * @return azienda
	 */
	public Azienda getAzienda() {
		return a;
	}
	
	/**
	 * Imposta l'azienda per lettura o scrittura
	 * @param a azienda
	 */
	public void setAzienda(Azienda a) {
		this.a=a;
	}

}
