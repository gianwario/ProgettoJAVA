package interfacciaGrafica;

import repartoAmministrativo.AmministrativoEsterno;
import risorse.*;
import esterni.*;

public class InterfacciaTester {

	public static void main(String[] args) {
		
		Magazzino magazzino = new Magazzino(500);		
		AmministrativoEsterno reparto = new AmministrativoEsterno(500000.0, magazzino);
		
		Attrezzo a1 = new Attrezzo("Trapano", 50, 0.30, "acciaio");
		Attrezzo a2 = new Attrezzo("Viti e chiodi", 40, 0.10, "metallo");
		Attrezzo a3 = new Attrezzo("Travi", 150, 1.5, "legno");
		Attrezzo a4 = new Attrezzo("Barile di cemento", 65, 0.70, "alluminio");
		Attrezzo a5 = new Attrezzo("Trivella", 200, 2.40, "acciaio");
		Attrezzo a6 = new Attrezzo("Martello pneumatico", 180, 0.40, "acciaio");
		Attrezzo a7 = new Attrezzo("Telo", 47, 2.50, "stoffa");
		Attrezzo a8 = new Attrezzo("Cani", 1000, 40.0, "bark");
		
		Fornitore f1 = new Fornitore("Voria SRL");
		Fornitore f2 = new Fornitore("Adele Rescigno INC");
		Fornitore f3 = new Fornitore("Bau Bau Bau");
		
		f1.aggiungiProdotto(a1);
		f1.aggiungiProdotto(a5);
		f1.aggiungiProdotto(a6);
		f1.aggiungiProdotto(a2);
		
		f2.aggiungiProdotto(a3);
		f2.aggiungiProdotto(a4);
		f2.aggiungiProdotto(a7);
		f2.aggiungiProdotto(a2);
		
		f3.aggiungiProdotto(a8);
		f3.aggiungiProdotto(a8);
		f3.aggiungiProdotto(a8);
		f3.aggiungiProdotto(a8);
		f3.aggiungiProdotto(a8);
		
		reparto.aggiungiFornitore(f1);
		reparto.aggiungiFornitore(f2);
		reparto.aggiungiFornitore(f3);
		
		new AmministrativoEsternoGUI(reparto);

	}

}
