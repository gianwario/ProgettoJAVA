package repartoOperativo;

import javax.swing.JOptionPane;

import eccezioni.CapacitaInsufficienteException;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;
import risorse.Attrezzo;
import risorse.Macchinario;
import risorse.Magazzino;

public class TesterOperativo {

	public static void main(String[] args) {
		
		System.out.println("Istanzio un nuovo reparto operativo e un magazzino con dei prodotti");
		Magazzino magazzino = new Magazzino(400.0);
		
		Attrezzo a1 = new Attrezzo("Travi", 120.65, 40.5, "legno");
		Attrezzo a2 = new Attrezzo("Viti e chiodi", 35, 1.5, "metallo");
		Attrezzo a3 = new Attrezzo("Trpano", 80.75, 5.75, "acciaio");
		Attrezzo a4 = new Attrezzo("Trivella", 180.40, 12.5, "acciaio");
		Attrezzo a5 = new Attrezzo("Telone", 25.80, 50.0, "stoffa");
		Attrezzo a6 = new Attrezzo("Barile di cemento", 34.5, 10.5, "latta");
		Macchinario m1 = new Macchinario("Gru", 3500.50, 50.5, "B");
		Macchinario m2 = new Macchinario("Betoniera", 2300.0, 38.5, "B");
		Macchinario m3 = new Macchinario("Scavatrice", 1560, 55.5, "C");
		
		Impiegato i1 = new Impiegato("Gianmario", "Voria", 28);
		Impiegato i2 = new Impiegato("Viviana", "Pentangelo", 34);
		Impiegato i3 = new Impiegato("Gigi", "Proietti", 18);
		Impiegato i4 = new Impiegato("Lorella", "Cuccarini", 31);
		Operaio o1 = new Operaio("Gianna", "Nannini", "C");
		Operaio o2 = new Operaio("Fiorella", "Mannoia", "B");
		Operaio o3 = new Operaio("Stefano", "De Martino", "D");
		Quadro q1 = new Quadro("Enzo", "Miccio");
		Quadro q2 = new Quadro("Carla", "Gozzi");
		Dirigente d1 = new Dirigente("Paolo", "Brosio");
		
		try {
			magazzino.aggiungiProdotto(a1);
			magazzino.aggiungiProdotto(a1);
			magazzino.aggiungiProdotto(a2);
			magazzino.aggiungiProdotto(a2);
			magazzino.aggiungiProdotto(a3);
			magazzino.aggiungiProdotto(a3);
			magazzino.aggiungiProdotto(a4);
			magazzino.aggiungiProdotto(a4);
			magazzino.aggiungiProdotto(a5);
			magazzino.aggiungiProdotto(a5);
			magazzino.aggiungiProdotto(a6);
			magazzino.aggiungiProdotto(a6);
			magazzino.aggiungiProdotto(m1);
			magazzino.aggiungiProdotto(m2);
			magazzino.aggiungiProdotto(m3);
		} 
		catch (CapacitaInsufficienteException e) {
			
			JOptionPane.showMessageDialog(null, " Capacita magazzino insufficiente!", "Errore", JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}
		
		RepartoOperativo reparto = new RepartoOperativo();
		
		System.out.println("\nReparto attuale: " + reparto);
		

	}

}
