package repartoAmministrativo;

import esterni.Fornitore;
import personale.Operaio;
import risorse.Attrezzo;
import risorse.Macchinario;
import risorse.Magazzino;

public class TesterEsterno {
	
	public static void main(String[] args) {
	
		Magazzino magazzino = new Magazzino(300.0);
		
		System.out.println("Istanzio 6 attrezzi. \n");
		
		Attrezzo a1 = new Attrezzo("Travi", 120.65, 40.5, "legno");
		Attrezzo a2 = new Attrezzo("Viti e chiodi", 35, 1.5, "metallo");
		Attrezzo a3 = new Attrezzo("Trpano", 80.75, 5.75, "acciaio");
		Attrezzo a4 = new Attrezzo("Trivella", 180.40, 12.5, "acciaio");
		Attrezzo a5 = new Attrezzo("Telone", 25.80, 50.0, "stoffa");
		Attrezzo a6 = new Attrezzo("Barile di cemento", 34.5, 10.5, "latta");
		
		System.out.println("Istanzio 3 macchinari. \n");		
		
		Macchinario m1 = new Macchinario("Gru", 3500.50, 50.5, "B");
		Macchinario m2 = new Macchinario("Betoniera", 2300.0, 38.5, "B");
		Macchinario m3 = new Macchinario("Scavatrice", 1560, 55.5, "C");
	
		System.out.println("Istanzio tre fornitori: \n");
	
		Fornitore f1 = new Fornitore("SuperFornitore");
		Fornitore f2 = new Fornitore("BestFornitore");
		Fornitore f3 = new Fornitore("FornitoreGrandioso");
	
	
	}
}
