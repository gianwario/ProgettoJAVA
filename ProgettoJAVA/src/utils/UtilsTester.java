package utils;

import java.util.ArrayList;

import esterni.Commissione;
import repartoAmministrativo.AmministrativoEsterno;
import risorse.Magazzino;

public class UtilsTester {

	public static void main(String[] args) {
		
		Magazzino magazzino = new Magazzino(70.0);
		AmministrativoEsterno reparto = new AmministrativoEsterno(8000, magazzino);
		
		Commissione c1 = new Commissione("Gianni Morandi", 4000, 120);
		Commissione c2 = new Commissione("Enzo Paolo Turchi", 1200, 50);
		Commissione c3 = new Commissione("Barbara D'Urso", 7630, 230);
		Commissione c4 = new Commissione("Maria De Filippi", 3405, 103);
		
		reparto.riceviCommissione(c1);
		reparto.riceviCommissione(c2);
		reparto.riceviCommissione(c3);
		reparto.riceviCommissione(c4);
		
		Selezionabile<Commissione> selezione = (s) -> s.getNominativoCliente().equals("Gianni Morandi");
		
		Selezionatore s = new Selezionatore(reparto.getListaCommissioni(), selezione);
		
		System.out.println(s.seleziona());
		
		Comparabile<Commissione> comparazione = (s1, s2) -> s1.getPagamento() - s2.getPagamento();
		Ordinatore<Commissione> o = new Ordinatore(reparto.getListaCommissioni(), comparazione);
		
		ArrayList<Commissione> res = o.ordina();
		
		System.out.println();
		for(Commissione c : res)
			System.out.println("\n"+ c.getNominativoCliente());

	}

}
