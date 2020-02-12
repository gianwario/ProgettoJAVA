package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
		setResizable(false);
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
		carica.addActionListener((e) -> {
			nuovo.setEnabled(true);
			setVisible(false);
			add(loadPanel(), BorderLayout.CENTER);
			setVisible(true);
			carica.setEnabled(false);
		});
		nuovo.addActionListener((e) -> {nuovo.setEnabled(false); carica.setEnabled(true);});
		

	}

	private JPanel createPanel() {
		JPanel p = new JPanel();
		return p;
	}

	private JPanel loadPanel() {
		JPanel p = new JPanel();
		
		
		JTextField t = new JTextField(20);
		JButton b = new JButton("Carica");
		p.add(t, BorderLayout.NORTH);
		p.add(b, BorderLayout.NORTH);
		
		
		b.addActionListener((e) -> {
			LeggiScriviAzienda a = new LeggiScriviAzienda(az);
			a.leggiAzienda(t.getText());
			az = a.getAzienda();
			JLabel jlt = new JLabel("Fondi totali : "+az.getFondiTotali());
			JLabel jle = new JLabel("Fondi reparto amministrativo esterno : "+az.getFondiEsterno());
			JLabel jli = new JLabel("Fondi reparto amministrativo interno : "+az.getFondiInterno());
			JLabel jlc = new JLabel("Numero cantieri aperti : "+az.getOperativo().getNumeroCantieriAperti());	
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();
			p1.add(jlt);
			p2.add(jle);
			p3.add(jli);
			p4.add(jlc);
			p.setVisible(false);
			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);
			p1.setSize(600,100);
			p2.setSize(600,100);
			p3.setSize(600,100);
			p4.setSize(600,100);
			p.setVisible(true);
			b.setEnabled(false);
		});
		p.setSize(300, 400);
		
		
		return p;
	}

}
