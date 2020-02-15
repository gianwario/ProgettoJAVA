package esterni;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Commissione implements Serializable, Cloneable {

	private String nominativoCliente;
	private double pagamento;
	private double prezzoPermessi;
	private boolean completamento;
	private boolean ottenimentoPermessi;

	/**
	 * La classe che rappresenta una commissione effettuata da un cliente
	 * all'azienda. Per essere completata devono essere abilitati i permessi di
	 * costruzione. L'azienda incassa il pagamento al suo completamento.
	 * 
	 * @param nome          nominativo del cliente che richiede la commissione
	 * @param prezzo        incasso che l'azienda otterrà al suo completamento
	 * @param costoPermessi importo che l'azienda deve pagare all'ente per ottenere
	 *                      i permessi di costruzione
	 */

	public Commissione(String nome, double prezzo, double costoPermessi) {

		nominativoCliente = nome;
		pagamento = prezzo;
		prezzoPermessi = costoPermessi;
		completamento = false;
		ottenimentoPermessi = false;

	}

	public String getNominativoCliente() {
		return nominativoCliente;
	}

	public double getPagamento() {
		return pagamento;
	}

	public double getPrezzoPermessi() {
		return prezzoPermessi;
	}

	public void setCompletamento(boolean completamento) {
		this.completamento = completamento;
	}

	public boolean getCompletamento() {
		return completamento;
	}

	public void setOttenimentoPermessi(boolean ottenimentoPermessi) {
		this.ottenimentoPermessi = ottenimentoPermessi;
	}

	public boolean getOttenimentoPermessi() {
		return ottenimentoPermessi;
	}

	public boolean equals(Object object) {

		if (object == null)
			return false;
		Commissione com2 = (Commissione) object;
		return (this.getClass() == com2.getClass() && this.nominativoCliente.equals(com2.nominativoCliente)
				&& this.pagamento == com2.pagamento && this.prezzoPermessi == com2.prezzoPermessi
				&& this.completamento == com2.completamento && this.ottenimentoPermessi == com2.ottenimentoPermessi);

	}

	public Commissione clone() {

		try {
			return (Commissione) super.clone();
		} catch (CloneNotSupportedException e) {

			JOptionPane.showMessageDialog(null, "Clone di COMMISSIONE fallita!", "Errore", JOptionPane.ERROR_MESSAGE, null);;
			e.printStackTrace();
		}

		return null;
	}

	public String toString() {

		return getClass().getName() + "[nominativoCliente = " + nominativoCliente + ", pagamento = " + pagamento
				+ ", prezzoPermessi = " + prezzoPermessi + "\n completamento = " + completamento
				+ ", ottentimentoPermessi = " + ottenimentoPermessi + "]";
	}
	
	public String stampa() {
		return "Cliente : " + nominativoCliente + ", introito netto : "+pagamento+", prezzo permessi : "+ prezzoPermessi +", completamento : " + completamento + 
		", permessi ottenuti : " + ottenimentoPermessi;
	}

}
