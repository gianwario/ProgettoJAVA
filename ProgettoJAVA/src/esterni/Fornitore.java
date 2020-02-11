package esterni;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import risorse.Prodotto;

public class Fornitore {

	private String nome;
	private ArrayList<Prodotto> catalogo;

	public Fornitore(String nome) {

		this.nome = nome;
		catalogo = new ArrayList<Prodotto>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Prodotto> getCatalogo() {
		return catalogo;
	}
	
	/**
	 * Restituisce un prodotto dal catalogo del fornitore
	
	 * @param p prodotto da estrarre
	 * @return clone del prodotto ottenuto
	 */
	public Prodotto getProdotto(Prodotto p) {
		
		if (p == null || !catalogo.contains(p))			
			return null;
		
		return p.clone();
		
	}
	
	public void aggiungiProdotto(Prodotto p) {
		
		if(p == null)
			return;
		
		catalogo.add(p);
	}
	
	public boolean equals(Object object) {
		
		if(object == null)
			return false;
		
		Fornitore f2 = (Fornitore)object;
				
		if(f2.catalogo.size() != this.catalogo.size())
			return false;
		
		for(int i = 0; i < this.catalogo.size(); i++)
			if(!this.catalogo.get(i).equals(f2.catalogo.get(i)))
				return false;
		
		return super.equals(f2) && true;		
		
	}

	public Fornitore clone() {

		try {
			Fornitore f2 = (Fornitore) super.clone();
			
			ArrayList<Prodotto> tmp = new ArrayList<Prodotto>();
			
			for(Prodotto p : this.catalogo)
				tmp.add(p.clone());
			
			f2.catalogo = tmp;
			
			return f2;
		} 
		catch (CloneNotSupportedException e) {

			JOptionPane.showMessageDialog(null, "Clone di FORNITORE fallita!", "Errore", 
					JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}

		return null;

	}

	public String toString() {

		String s1 = getClass().getName() + "[ nome = " + nome + ", lista prodotti: \n";
		String s2 = "";

		for (Prodotto p : catalogo)
			s2 += p.toString() + "\n";

		return s1 + s2 + "]";
	}

}
