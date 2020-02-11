package azienda;

import java.util.ArrayList;

import eccezioni.CapacitaInsufficienteException;
import esterni.Commissione;
import esterni.Fornitore;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;
import repartoAmministrativo.AmministrativoEsterno;
import repartoAmministrativo.AmministrativoInterno;
import repartoOperativo.RepartoOperativo;
import repartoOperativo.Squadra;
import risorse.Attrezzo;
import risorse.Macchinario;
import risorse.Magazzino;

public class GeneraAzienda {

	public static void main(String[] args) {

		Azienda a = new Azienda(100000);
		AmministrativoEsterno es= a.getEsterno();
		AmministrativoInterno in = a.getInterno();
		RepartoOperativo op = a.getOperativo();
		//Magazzino m =a.getMagazzino();
		
		
		//SALVATAGGIO REPARTO INTERNO
		Impiegato i1 = new Impiegato("impiegato", "1", 28);
		Impiegato i2 = new Impiegato("impiegato", "2", 40);
		Operaio o1 = new Operaio("operaio", "1", "C");
		o1.setConducente(true);
		Operaio o2 = new Operaio("operaio", "2", "B");
		Quadro q1 = new Quadro("quadro", "1");
		Quadro q2 = new Quadro("quadro", "2");
		Dirigente d1 = new Dirigente("dirigente", "1");
		Dirigente d2 = new Dirigente("dirigente", "2");
		in.assumiDipendente(i1);
		in.assumiDipendente(i2);
		in.assumiDipendente(o1);
		in.assumiDipendente(o2);
		in.assumiDipendente(q1);
		in.assumiDipendente(q2);
		in.assumiDipendente(d1);
		in.assumiDipendente(d2);
		
		//SALVATAGGIO REPARTO ESTERNO
		Attrezzo a1 = new Attrezzo("Travi", 120.65, 40.5, "legno");
		Attrezzo a2 = new Attrezzo("Viti e chiodi", 35, 1.5, "metallo");
		Attrezzo a3 = new Attrezzo("Trpano", 80.75, 5.75, "acciaio");
		Attrezzo a4 = new Attrezzo("Trivella", 180.40, 12.5, "acciaio");
		Attrezzo a5 = new Attrezzo("Telone", 25.80, 50.0, "stoffa");
		Attrezzo a6 = new Attrezzo("Barile di cemento", 34.5, 10.5, "latta");
		Macchinario m1 = new Macchinario("Gru", 3500.50, 50.5, "B");
		Macchinario m2 = new Macchinario("Betoniera", 2300.0, 38.5, "B");
		Macchinario m3 = new Macchinario("Scavatrice", 1560, 55.5, "C");
		Fornitore f1 = new Fornitore("SuperFornitore");
		Fornitore f2 = new Fornitore("BestFornitore");
		Fornitore f3 = new Fornitore("FornitoreGrandioso");
		f1.aggiungiProdotto(a1);
		f1.aggiungiProdotto(a2);
		f1.aggiungiProdotto(a3);
		f2.aggiungiProdotto(a4);
		f2.aggiungiProdotto(a5);
		f2.aggiungiProdotto(a6);
		f3.aggiungiProdotto(m1);
		f3.aggiungiProdotto(m2);
		f3.aggiungiProdotto(m3);
		Commissione c1 = new Commissione("Gianni Morandi", 4000, 120);
		Commissione c2 = new Commissione("Enzo Paolo Turchi", 1200, 50);
		Commissione c3 = new Commissione("Barbara D'Urso", 7630, 230);
		es.aggiungiFornitore(f1);
		es.aggiungiFornitore(f2);
		es.aggiungiFornitore(f3);
		es.riceviCommissione(c1);
		es.riceviCommissione(c2);
		es.riceviCommissione(c3);
		es.acquistaDaFornitore(f1, a1);
		es.acquistaDaFornitore(f3, m1);
		
		//SALVATAGGIO OPERATIVI
		op.apriCantiere(10000, d1, c1);
		ArrayList<Operaio> l = new ArrayList<Operaio>();
		l.add(o1);
		op.getCantiere(0).aggiungiSquadra(new Squadra(q1, l));
		
		LeggiScriviAzienda lsa = new LeggiScriviAzienda(a);
		lsa.scriviAzienda("azienda.dat");
		
		
		System.out.println(lsa.getAzienda());
	}

}
