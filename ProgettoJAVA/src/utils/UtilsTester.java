package utils;

import java.util.ArrayList;

import esterni.Commissione;
import repartoAmministrativo.AmministrativoEsterno;
import risorse.Magazzino;

public class UtilsTester {

	public static void main(String[] args) {

		System.out.println("Istanzio un reparto amministrativo e aggiungo 6 commissioni alla lista");

		Magazzino magazzino = new Magazzino(70.0);
		AmministrativoEsterno reparto = new AmministrativoEsterno(8000, magazzino);

		Commissione c1 = new Commissione("Gianni Morandi", 4000, 120);
		Commissione c2 = new Commissione("Enzo Paolo Turchi", 1200, 50);
		Commissione c3 = new Commissione("Barbara D'Urso", 7630, 230);
		Commissione c4 = new Commissione("Maria De Filippi", 3405, 103);
		Commissione c5 = new Commissione("Paolo Bonolis", 650, 98);
		Commissione c6 = new Commissione("Amadeus", 6430, 2100);

		reparto.riceviCommissione(c1);
		reparto.riceviCommissione(c2);
		reparto.riceviCommissione(c3);
		reparto.riceviCommissione(c4);
		reparto.riceviCommissione(c5);
		reparto.riceviCommissione(c6);

		System.out.println("Lista commissioni: \n" + reparto.getListaCommissioni());

		System.out.println("\nSeleziono tutte le commissioni con prezzo dei permessi maggiore di 100");

		Selezionabile<Commissione> selezione = (s) -> s.getPrezzoPermessi() > 100;
		Selezionatore s = new Selezionatore(reparto.getListaCommissioni(), selezione);

		System.out.println("\nRISULTATO SELEZIONE: \n" + s.seleziona());

		System.out.println("\nSeleziono tutte le commissioni con prezzo del pagamento" + "compreso fra 1000 e 4000");

		
		s = new Selezionatore(reparto.getListaCommissioni(), (Selezionabile<Commissione>)(s2) -> s2.getPagamento() >= 1000 && s2.getPagamento() <= 4000);

		System.out.println("\nRISULTATO SELEZIONE: \n" + s.seleziona());

		System.out.println("\nOrdino la lista delle commissioni per nominativo clienti in ordine alfabetico");

		Comparabile<Commissione> comparazione = (s3, s4) -> s3.getNominativoCliente()
				.compareTo(s4.getNominativoCliente());
		Ordinatore<Commissione> o = new Ordinatore(reparto.getListaCommissioni(), comparazione);

		System.out.println("\nRISULTATO ORDINAMENTO: \n" + o.ordina());
		
	}

}
