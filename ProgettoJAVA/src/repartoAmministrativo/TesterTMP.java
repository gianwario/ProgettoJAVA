package repartoAmministrativo;

import java.util.ArrayList;

import personale.Dipendente;
import personale.Impiegato;

public class TesterTMP {

	public static void main(String[] args) {
		
		AmministrativoInterno ai = new AmministrativoInterno(10000);
		System.out.println(ai.controllaFondi());
		
		Impiegato i1 = new Impiegato("Gianmario", "Voria", 28);
		Impiegato i2 = new Impiegato("Gianmario1", "Voria1", 20);
		Impiegato i3 = new Impiegato("Viviana", "Pentangelo", 30);
		Impiegato i4 = new Impiegato("Gianmario", "Voria", 40);
		
		ai.assumiDipendente(i1);
		ai.assumiDipendente(i2);
		ai.assumiDipendente(i3);
		ai.assumiDipendente(i4);
		
		ArrayList<Dipendente> al = ai.listaDipendenti();
		
		for(Dipendente d : al) {
			System.out.println(d);
		}
		
		ai.effettuaPagamento();
		System.out.println(ai.controllaFondi());
		
		al = ai.listaDipendenti();
		
		for(Dipendente d : al) {
			System.out.println(d);
		}
		
		ai.effettuaPagamento();
		System.out.println(ai.controllaFondi());
		
		ai.resettaStatoPagamenti();
		
		ai.effettuaPagamento();
		System.out.println(ai.controllaFondi());
	}

}
