package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import azienda.Azienda;
import azienda.LeggiScriviAzienda;

public class InitGUI extends JFrame {

	// private JButton salva;
	private JButton carica;
	private JButton nuovo;

	// private JPanel salvaPanel;
	private JPanel caricaPanel;
	private JPanel nuovoPanel;

	private JPanel topPanel;

	Azienda az;

	public InitGUI() {

		az = null;
		// Organizzazione frame e inizializzazione bottoni
		// salvaPanel=new JPanel();
		caricaPanel = new JPanel();
		nuovoPanel = new JPanel();
		caricaPanel.setSize(600, 600);
		nuovoPanel.setSize(600, 600);
		topPanel = new JPanel();

		// salva=new JButton("Salva nu");
		carica = new JButton("Carica azienda");
		nuovo = new JButton("Nuova azienda");

		caricaPanel.add(carica);
		nuovoPanel.add(nuovo);
		topPanel.add(caricaPanel);
		topPanel.add(nuovoPanel);
		add(topPanel, BorderLayout.NORTH);
	}

	private JPanel createPanel() {
		JPanel p = new JPanel();
		return p;
	}

	private JPanel loadPanel(String fileName) {
		JPanel p = new JPanel();
		JTextField t = new JTextField(20);
		LeggiScriviAzienda a = new LeggiScriviAzienda(az);
		a.leggiAzienda(fileName);
		az = a.getAzienda();
		
		return p;
	}

}
