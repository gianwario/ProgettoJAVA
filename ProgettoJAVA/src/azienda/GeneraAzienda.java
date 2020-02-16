package azienda;

import java.util.ArrayList;

import eccezioni.AperturaCantiereInvalidaException;
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
import risorse.Prodotto;

public class GeneraAzienda {

	public static void main(String[] args) {

		Azienda a = new Azienda(2300000, 2000);
		AmministrativoEsterno es = a.getEsterno();
		AmministrativoInterno in = a.getInterno();
		RepartoOperativo op = a.getOperativo();
		// Magazzino m =a.getMagazzino();

		System.out.println("SOLDOS : " + es.controllaFondi() + "   " + in.controllaFondi() + "\n");

		// SALVATAGGIO REPARTO INTERNO
		Impiegato i1 = new Impiegato("Gianmario", "Voria", 28);
		Impiegato i2 = new Impiegato("Viviana", "Pentangelo", 34);
		Impiegato i3 = new Impiegato("Paolo", "Bonolis", 40);
		Impiegato i4 = new Impiegato("Luca", "Laurenti", 20);
		Impiegato i5 = new Impiegato("Cristina", "D'Avena", 12);
		Impiegato i6 = new Impiegato("Barbara", "D'Urso", 36);
		Impiegato i7 = new Impiegato("Mara", "Venier", 37);
		Impiegato i8 = new Impiegato("Carlo", "Conti", 25);

		Operaio o1 = new Operaio("Gerry", "Scotti", "B");
		Operaio o2 = new Operaio("Paola", "Perego", "C");
		Operaio o3 = new Operaio("Ilary", "Blasi", "D");
		Operaio o4 = new Operaio("Alessia", "Marcuzzi", "B");
		Operaio o5 = new Operaio("Alfonso", "Signorini", "C");
		Operaio o6 = new Operaio("Laura", "Pausini", "D");
		Operaio o7 = new Operaio("Vasco", "Rossi", "B");
		Operaio o8 = new Operaio("Tiziano", "Ferro", "C");
		Operaio o9 = new Operaio("Milly", "Carducci", "D");
		Operaio o10 = new Operaio("Emma", "Marrone", "B");
		Operaio o11 = new Operaio("Cristiano", "Malgioglio", "C");
		Operaio o12 = new Operaio("Ezio", "Greggio", "D");
		Operaio o13 = new Operaio("Silvia", "Toffanin", "B");
		Operaio o14 = new Operaio("Simona", "Ventura", "C");
		Operaio o15 = new Operaio("Claudio", "Bisio", "D");
		Operaio o16 = new Operaio("Vincenzo", "Salemme", "B");
		Operaio o17 = new Operaio("Alessandro", "Siani", "C");
		Operaio o18 = new Operaio("Carlo", "Verdone", "D");

		Quadro q1 = new Quadro("Enzo", "Iacchetti");
		Quadro q2 = new Quadro("Paolo", "Brosio");
		Quadro q3 = new Quadro("Nina", "Moric");
		Quadro q4 = new Quadro("Valeria", "Marini");
		Quadro q5 = new Quadro("Mike", "Bongiorno");
		Quadro q6 = new Quadro("Carlo", "Cracco");
		Quadro q7 = new Quadro("Giorgio", "Locatelli");
		Quadro q8 = new Quadro("Bruno", "Barbieri");

		Dirigente d1 = new Dirigente("Gigi", "D'Alessio");
		Dirigente d2 = new Dirigente("Antonino", "Cannavacciuolo");
		Dirigente d3 = new Dirigente("Pamela", "Prati");
		
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();
		d2.nuovoAnnoDiServizio();
		d2.nuovoAnnoDiServizio();
		d2.nuovoAnnoDiServizio();
		d2.nuovoAnnoDiServizio();
		d3.nuovoAnnoDiServizio();
		d3.nuovoAnnoDiServizio();	

		in.assumiDipendente(i1);
		in.assumiDipendente(i2);
		in.assumiDipendente(i3);
		in.assumiDipendente(i4);
		in.assumiDipendente(i5);
		in.assumiDipendente(i6);
		in.assumiDipendente(i7);
		in.assumiDipendente(i8);
		in.assumiDipendente(o1);
		in.assumiDipendente(o2);
		in.assumiDipendente(o3);
		in.assumiDipendente(o4);
		in.assumiDipendente(o5);
		in.assumiDipendente(o6);
		in.assumiDipendente(o7);
		in.assumiDipendente(o8);
		in.assumiDipendente(o9);
		in.assumiDipendente(o10);
		in.assumiDipendente(o11);
		in.assumiDipendente(o12);
		in.assumiDipendente(o13);
		in.assumiDipendente(o14);
		in.assumiDipendente(o15);
		in.assumiDipendente(o16);
		in.assumiDipendente(o17);
		in.assumiDipendente(o18);
		in.assumiDipendente(q1);
		in.assumiDipendente(q2);
		in.assumiDipendente(q3);
		in.assumiDipendente(q4);
		in.assumiDipendente(q5);
		in.assumiDipendente(q6);
		in.assumiDipendente(q7);
		in.assumiDipendente(q8);
		in.assumiDipendente(d1);
		in.assumiDipendente(d2);
		in.assumiDipendente(d3);

		// SALVATAGGIO REPARTO ESTERNO
		Attrezzo a1 = new Attrezzo("Travi", 120.65, 40.5, "legno");
		Attrezzo a2 = new Attrezzo("Viti", 35, 1.5, "metallo");
		Attrezzo a3 = new Attrezzo("Trpano", 80.75, 5.75, "acciaio");
		Attrezzo a4 = new Attrezzo("Trivella", 180.40, 12.5, "acciaio");
		Attrezzo a5 = new Attrezzo("Telone", 25.80, 50.0, "stoffa");
		Attrezzo a6 = new Attrezzo("Barile di cemento", 34.5, 10.5, "latta");
		Attrezzo a7 = new Attrezzo("Barile di stucco", 20.5, 8.5, "latta");
		Attrezzo a8 = new Attrezzo("Tegole", 14.5, 2, "argilla");
		Attrezzo a9 = new Attrezzo("Chiodi", 12, 1.5, "metallo");
		Attrezzo a10 = new Attrezzo("Cacciavite", 20.5, 1.5, "acciaio");
		Attrezzo a11 = new Attrezzo("Chiave inglese", 34, 2, "acciaio");
		Attrezzo a12 = new Attrezzo("Colla", 11.5, 0.8, "plastica");
		Attrezzo a13 = new Attrezzo("Martello", 23, 0.6, "metallo");

		Macchinario m1 = new Macchinario("Gru", 3500.50, 50.5, "C");
		Macchinario m2 = new Macchinario("Betoniera", 2300.0, 38.5, "B");
		Macchinario m3 = new Macchinario("Escavatrice", 1560, 55.5, "D");
		Macchinario m4 = new Macchinario("Macchinario con pinza", 3060, 65.5, "B");
		Macchinario m5 = new Macchinario("Piattaforma aerea", 2850, 20, "C");

		Fornitore f1 = new Fornitore("SuperFornitore");
		Fornitore f2 = new Fornitore("BestFornitore");
		Fornitore f3 = new Fornitore("FornitoreGrandioso");
		Fornitore f4 = new Fornitore("Forniture4Ever");

		f1.aggiungiProdotto(a2);
		f1.aggiungiProdotto(a9);
		f1.aggiungiProdotto(a12);
		f1.aggiungiProdotto(a10);
		f1.aggiungiProdotto(a13);

		f2.aggiungiProdotto(a3);
		f2.aggiungiProdotto(a4);
		f2.aggiungiProdotto(a13);
		f2.aggiungiProdotto(a10);
		f2.aggiungiProdotto(a11);

		f3.aggiungiProdotto(a6);
		f3.aggiungiProdotto(a7);
		f3.aggiungiProdotto(a5);
		f3.aggiungiProdotto(a1);
		f3.aggiungiProdotto(a8);

		f4.aggiungiProdotto(m1);
		f4.aggiungiProdotto(m2);
		f4.aggiungiProdotto(m3);
		f4.aggiungiProdotto(m4);
		f4.aggiungiProdotto(m5);

		es.aggiungiFornitore(f1);
		es.aggiungiFornitore(f2);
		es.aggiungiFornitore(f3);
		es.aggiungiFornitore(f4);

		for (int i = 0; i < 3; i++) {

			es.acquistaDaFornitore(f1, a2);
			es.acquistaDaFornitore(f1, a12);
			es.acquistaDaFornitore(f1, a10);
			es.acquistaDaFornitore(f2, a4);
			es.acquistaDaFornitore(f2, a11);
			es.acquistaDaFornitore(f3, a8);
			es.acquistaDaFornitore(f3, a7);
		}

		for (int i = 0; i < 4; i++) {

			es.acquistaDaFornitore(f1, a13);
			es.acquistaDaFornitore(f1, a9);
			es.acquistaDaFornitore(f2, a3);
			es.acquistaDaFornitore(f2, a5);
			es.acquistaDaFornitore(f3, a6);
			es.acquistaDaFornitore(f3, a1);

			es.acquistaDaFornitore(f4, m1);
			es.acquistaDaFornitore(f4, m2);
			es.acquistaDaFornitore(f4, m3);
			es.acquistaDaFornitore(f4, m4);
			es.acquistaDaFornitore(f4, m5);
			es.acquistaDaFornitore(f4, m5);
		}

		Commissione c1 = new Commissione("Gianni Morandi", 42000, 120);
		Commissione c2 = new Commissione("Enzo Paolo Turchi", 11200, 50);
		Commissione c3 = new Commissione("Fabio De Luigi", 72630, 300);
		Commissione c4 = new Commissione("Federica Panicucci", 51430, 210);
		Commissione c5 = new Commissione("Maria De Filippi", 90230, 430);
		Commissione c6 = new Commissione("Rudy Zerbi", 16650, 96);
		Commissione c7 = new Commissione("Adriano Celentano", 9010, 170);

		es.riceviCommissione(c1);
		es.riceviCommissione(c2);
		es.riceviCommissione(c3);
		es.riceviCommissione(c4);
		es.riceviCommissione(c5);
		es.riceviCommissione(c6);
		es.riceviCommissione(c7);

		es.pagaPermessi(c1);
		es.pagaPermessi(c2);
		es.pagaPermessi(c3);
		es.pagaPermessi(c4);

		ArrayList<Prodotto> list1 = new ArrayList<Prodotto>();
		ArrayList<Prodotto> list2 = new ArrayList<Prodotto>();
		ArrayList<Prodotto> list3 = new ArrayList<Prodotto>();

		for (int i = 0; i < 2; i++) {

			list1.add(a2);
			list1.add(a10);
			list1.add(a13);
			list1.add(a5);
			list1.add(m5);
			list1.add(m4);

			list2.add(a12);
			list2.add(a4);
			list2.add(a3);
			list2.add(m1);
			list2.add(m2);

			list3.add(a7);
			list3.add(a8);
			list3.add(a11);
			list3.add(a9);
			list3.add(a6);
			list3.add(m1);
			list3.add(m3);
			list3.add(m4);
		}

		try {
			op.apriCantiere(30000, q1, list1, c1);
			op.apriCantiere(560000, d1, list2, c2);
			op.apriCantiere(100000, q2, list3, c3);
		} catch (AperturaCantiereInvalidaException e) {
			e.printStackTrace();
		}

		ArrayList<Operaio> team1 = new ArrayList<Operaio>();
		ArrayList<Operaio> team2 = new ArrayList<Operaio>();
		ArrayList<Operaio> team3 = new ArrayList<Operaio>();
		ArrayList<Operaio> team4 = new ArrayList<Operaio>();
		ArrayList<Operaio> team5 = new ArrayList<Operaio>();

		team1.add(o1);
		team1.add(o2);
		team1.add(o3);

		team2.add(o4);
		team2.add(o5);

		team3.add(o6);
		team3.add(o7);
		team3.add(o8);

		team4.add(o9);
		team4.add(o10);
		team4.add(o11);

		team5.add(o12);
		team5.add(o13);

		Squadra s1 = new Squadra(q3, team1);
		Squadra s2 = new Squadra(q4, team2);
		Squadra s3 = new Squadra(q5, team3);
		Squadra s4 = new Squadra(q6, team4);
		Squadra s5 = new Squadra(q7, team5);

		op.getCantiere(0).aggiungiSquadra(s1);
		op.getCantiere(0).aggiungiSquadra(s2);
		op.getCantiere(1).aggiungiSquadra(s3);
		op.getCantiere(2).aggiungiSquadra(s4);
		op.getCantiere(2).aggiungiSquadra(s5);

		op.getCantiere(0).assegnaConducente(m5, o1);
		op.getCantiere(1).assegnaConducente(m1, o8);
		op.getCantiere(1).assegnaConducente(m2, o7);
		op.getCantiere(2).assegnaConducente(m4, o10);

		System.out.println(o1.checkPaga() +" "+ o7.checkPaga()+" "+ o8.checkPaga()+" "+ o10.checkPaga());

		LeggiScriviAzienda lsa = new LeggiScriviAzienda();
		lsa.setAzienda(a);
		lsa.scriviAzienda("azienda.dat");

		System.out.println(lsa.getAzienda());

		System.out.println("SOLDOS : " + es.controllaFondi() + "   " + in.controllaFondi() + "\n");
	}

}
