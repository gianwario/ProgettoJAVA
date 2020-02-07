package repartoAmministrativo;

import java.util.ArrayList;

import personale.Dipendente;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;

public class TesterTMP {

	public static void main(String[] args) {

		AmministrativoInterno ai = new AmministrativoInterno(10000);

		Impiegato i1 = new Impiegato("Gianmario", "Voria", 28);
		Operaio i2 = new Operaio("Gianmario1", "Voria1", "C");
		Quadro i3 = new Quadro("Viviana", "Pentangelo");
		Dirigente i4 = new Dirigente("Viviana1", "Pentangelo1");

		ai.assumiDipendente(i1);
		ai.assumiDipendente(i2);
		ai.assumiDipendente(i3);
		ai.assumiDipendente(i4);

		System.out.println(ai.toString());

		ai.effettuaPagamento();
		System.out.println("Fondi dopo il pagamento: "+ai.controllaFondi());
		

		ai.effettuaPagamento();
		System.out.println("Fondi dopo il secondo pagamento senza aver resettato lo stato: "+ai.controllaFondi());

		ai.aggiungiFondi(10000 - 8475);
		ai.resettaStatoPagamenti();
		System.out.println("Fondi resettati e stato resettato, nuovo capitale :"+ai.controllaFondi());

		ArrayList<Dipendente> al = ai.listaDipendenti();
		((Operaio) al.get(1)).setConducente(true);
		((Quadro) al.get(2)).setDirigente(true);
		((Dirigente) al.get(3)).nuovoAnnoDiServizio();

		for (Dipendente d : al) {
			System.out.println(d);
			ai.effettuaPagamento(d);
			System.out.println(ai.controllaFondi());
		}

	}

}
