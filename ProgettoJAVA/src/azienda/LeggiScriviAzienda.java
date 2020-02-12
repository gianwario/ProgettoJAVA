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

	public Azienda getAzienda() {
		return a;
	}
	
	public void setAzienda(Azienda a) {
		this.a=a;
	}

}
