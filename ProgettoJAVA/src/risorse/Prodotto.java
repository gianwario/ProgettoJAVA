package risorse;

import java.io.Serializable;

import javax.swing.JOptionPane;


public abstract class Prodotto implements Serializable, Cloneable {
	
	private String nome;
	private double prezzo;
	
	/**
	 * La classe rappresenta l'astrazione del concetto di prodotto generico, che viene venduto dai fornitori
	 * e conservato nei magazzini per permettere i lavori dei cantieri
	 * @param nome nome del prodotto
	 * @param prezzo costo del prodotto in vendita
	 */
	public Prodotto(String nome, double prezzo) {
		
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public boolean equals(Object object) {
		
		if (object == null)
			return false;
		Prodotto p2 = (Prodotto) object;
		return (this.getClass() == p2.getClass() && this.getNome().equals(p2.getNome()) 
				&& this.prezzo == p2.getPrezzo());
	
	}
	
	public Prodotto clone() {
		

		try {
			return (Prodotto) super.clone();
		} catch (CloneNotSupportedException e) {

			JOptionPane.showMessageDialog(null, "Clone di PRODOTTO fallita!", "Errore", JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
		}

		return null;
	}
	
	public String toString() {

		return getClass().getName() + "[nome = " + nome + ", prezzo = $" + prezzo + "]";
	}
	
}
