package repartoAmministrativo;

import esterni.Commissione;
import esterni.Fornitore;
import risorse.Attrezzo;
import risorse.Macchinario;
import risorse.Magazzino;

public class TesterEsterno {
	
	public static void main(String[] args) {
	
		Magazzino magazzino = new Magazzino(70.0);
		AmministrativoEsterno reparto = new AmministrativoEsterno(8000, magazzino);
		
		System.out.println("Istanzio un magazzino vuoto con capacità max 70 e un reparto. \n "
				+ reparto);
		
		System.out.println("Istanzio 3 commissioni \n");
		
		Commissione c1 = new Commissione("Gianni Morandi", 4000, 120);
		Commissione c2 = new Commissione("Enzo Paolo Turchi", 1200, 50);
		Commissione c3 = new Commissione("Barbara D'Urso", 7630, 230);
		
		System.out.println("Aggiungo le commissioni alla lista: ");
		
		reparto.riceviCommissione(c1);
		reparto.riceviCommissione(c2);
		reparto.riceviCommissione(c3);
		
		System.out.println("\nLista commissioni attuale: \n" + reparto.getListaCommissioni());
		
		System.out.println("\nPago i permessi agli enti locali di tutte le commissioni: ");
		
		reparto.pagaPermessi(reparto.getListaCommissioni().get(0));
		reparto.pagaPermessi(reparto.getListaCommissioni().get(1));
		reparto.pagaPermessi(reparto.getListaCommissioni().get(2));
		
		System.out.println("\nLista commissioni attuale: \n" + reparto.getListaCommissioni());
		
		
		System.out.println("\nIstanzio 6 attrezzi. \n");
		
		Attrezzo a1 = new Attrezzo("Travi", 120.65, 40.5, "legno");
		Attrezzo a2 = new Attrezzo("Viti e chiodi", 35, 1.5, "metallo");
		Attrezzo a3 = new Attrezzo("Trpano", 80.75, 5.75, "acciaio");
		Attrezzo a4 = new Attrezzo("Trivella", 180.40, 12.5, "acciaio");
		Attrezzo a5 = new Attrezzo("Telone", 25.80, 50.0, "stoffa");
		Attrezzo a6 = new Attrezzo("Barile di cemento", 34.5, 10.5, "latta");
		
		System.out.println("\nIstanzio 3 macchinari. \n");		
		
		Macchinario m1 = new Macchinario("Gru", 3500.50, 50.5, "B");
		Macchinario m2 = new Macchinario("Betoniera", 2300.0, 38.5, "B");
		Macchinario m3 = new Macchinario("Scavatrice", 1560, 55.5, "C");
	
		System.out.println("\nIstanzio tre fornitori: \n");
	
		Fornitore f1 = new Fornitore("SuperFornitore");
		Fornitore f2 = new Fornitore("BestFornitore");
		Fornitore f3 = new Fornitore("FornitoreGrandioso");
		
		System.out.println("\nRiempio i cataloghi \n");
		
		f1.aggiungiProdotto(a1);
		f1.aggiungiProdotto(a2);
		f1.aggiungiProdotto(a5);
		
		f2.aggiungiProdotto(a3);
		f2.aggiungiProdotto(a4);
		f2.aggiungiProdotto(a6);
		
		f3.aggiungiProdotto(m1);
		f3.aggiungiProdotto(m2);
		f3.aggiungiProdotto(m3);
		
		System.out.println("\nAggiungo i fornitori alla lista\n");
		
		reparto.aggiungiFornitore(f1);
		reparto.aggiungiFornitore(f2);
		reparto.aggiungiFornitore(f3);
		
		System.out.println(f1 + "\n" + f2 + "\n" + f3);
		
		System.out.println("\nAcquisto prodotti e fino a superare in volume la capacità disponibile del magazzino: ");
		
		reparto.acquistaDaFornitore(f1, a2);		
		reparto.acquistaDaFornitore(f2, a4);
		reparto.acquistaDaFornitore(f3, m1);
		reparto.acquistaDaFornitore(f3, m2);
		reparto.acquistaDaFornitore(f2, a6);
		
		System.out.println("\nReparto attuale: \n" + reparto);
		
		System.out.println("\nRimuovo due fornitori dalla lista fornitori: ");
		
		reparto.rimuoviFornitore(f2);
		reparto.rimuoviFornitore(f3);
		
		System.out.println("\nReparto attuale: \n" + reparto);		
	
	}
}
