package repartoAmministrativo;

import java.util.ArrayList;

import personale.Dipendente;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;

public class TesterInterno {

	public static void main(String[] args) {

		System.out.println("Istanzio un reparto amministrativo interno con fondi $10000");

		AmministrativoInterno ai = new AmministrativoInterno(10000);

		System.out.println("\nIstanzio e assumo 4 impiegati");

		Impiegato i1 = new Impiegato("Gianmario", "Voria", 28);
		Impiegato i2 = new Impiegato("Viviana", "Pentangelo", 34);
		Impiegato i3 = new Impiegato("Gigi", "Proietti", 18);
		Impiegato i4 = new Impiegato("Lorella", "Cuccarini", 31);

		ai.assumiDipendente(i1);
		ai.assumiDipendente(i2);
		ai.assumiDipendente(i3);
		ai.assumiDipendente(i4);

		System.out.println("\nIstanzio e assumo 3 operai, due dei quali conducenti di macchinari");

		Operaio o1 = new Operaio("Gianna", "Nannini", "C");
		Operaio o2 = new Operaio("Fiorella", "Mannoia", "B");
		Operaio o3 = new Operaio("Stefano", "De Martino", "D");

		o1.setConducente(true);
		o2.setConducente(true);

		ai.assumiDipendente(o1);
		ai.assumiDipendente(o2);
		ai.assumiDipendente(o3);

		System.out.println("\nIstanzio e assumo 2 quadri");

		Quadro q1 = new Quadro("Enzo", "Miccio");
		Quadro q2 = new Quadro("Carla", "Gozzi");

		ai.assumiDipendente(q1);
		ai.assumiDipendente(q2);

		System.out.println("\nIstanzio e assumo 1 dirigente, e incremento i suoi anni di servizio");

		Dirigente d1 = new Dirigente("Paolo", "Brosio");
		d1.nuovoAnnoDiServizio();
		d1.nuovoAnnoDiServizio();

		ai.assumiDipendente(d1);

		System.out.println("\nReparto attuale: \n" + ai);

		System.out.println("\nPago lo stipendio a tutti gli impiegati. Mi aspetto che i fondi rimanenti siano"
				+ ": [10000 - (350 + 435 + 225 + 390)] = $8600");

		ai.effettuaPagamento(i1);
		ai.effettuaPagamento(i2);
		ai.effettuaPagamento(i3);
		ai.effettuaPagamento(i4);

		System.out.println("\nFONDI RIMANENTI: $" + ai.controllaFondi());

		System.out.println("\nPago lo stipendio a tutti gli operai. Mi aspetto che i fondi rimanenti siano"
				+ ": [8600 - (357 + 364 + 350)] = $7529");

		ai.effettuaPagamento(o1);
		ai.effettuaPagamento(o2);
		ai.effettuaPagamento(o3);

		System.out.println("\nFONDI RIMANENTI: $" + ai.controllaFondi());

		System.out.println("\nPago lo stipendio a tutti i quadri. Mi aspetto che i fondi rimanenti siano"
				+ ": [7529 - (400 + 400)] = $6729");

		ai.effettuaPagamento(q1);
		ai.effettuaPagamento(q2);

		System.out.println("\nFONDI RIMANENTI: $" + ai.controllaFondi());
		
		System.out.println("\nPago lo stipendio al dirigente. Mi aspetto che i fondi rimanenti siano"
				+ ": [6729 - (425 + 8.5)] = $6295.5");

		ai.effettuaPagamento(d1);

		System.out.println("\nFONDI RIMANENTI: $" + ai.controllaFondi());
		
		System.out.println("Provo a ripagare lo stipendio a tutto il personale. Non mi aspetto diminuzione di fondi");
		
		ai.effettuaPagamento();
		System.out.println("\nFONDI RIMANENTI: $" + ai.controllaFondi());
		
		System.out.println("\nResetto lo stato di pagamento a tutto il personale ed effettuo nuovamente il pagamento"
				+ "Mi aspetto che i fondi rimanenti siano: [6295.5 - 3704.5] =  $2591");
		
		ai.resettaStatoPagamenti();
		ai.effettuaPagamento();
		
		System.out.println("\nFONDI RIMANENTI: $" + ai.controllaFondi());
		
		System.out.println("\nResetto lo stato di pagamento a tutto il personale ed effettuo nuovamente il pagamento"
				+ "Mi aspetto FondiIsufficientiException!\n");
		
		ai.resettaStatoPagamenti();
		ai.effettuaPagamento();
		
		System.out.println("\nLicenzio 2 impiegati e 1 operaio");
		
		ai.licenziaDipendente(i1);
		ai.licenziaDipendente(i2);
		ai.licenziaDipendente(o3);
		
		System.out.println("\nReparto attuale: \n" + ai);
	}

}
