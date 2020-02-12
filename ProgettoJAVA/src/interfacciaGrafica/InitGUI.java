package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		topPanel = new JPanel();
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(50, 400));
		add(p1, BorderLayout.WEST);
		add(p1, BorderLayout.EAST);

		// salva=new JButton("Salva nu");
		carica = new JButton("Carica azienda");
		nuovo = new JButton("Nuova azienda");

		caricaPanel.add(carica);
		nuovoPanel.add(nuovo);
		topPanel.add(caricaPanel);
		topPanel.add(nuovoPanel);
		add(topPanel, BorderLayout.NORTH);
		JPanel load = loadPanel();
		JPanel save = createPanel();
		carica.addActionListener((e) -> {
			nuovo.setEnabled(true);
			load.setVisible(true);
			save.setVisible(false);
			setVisible(false);
			add(load, BorderLayout.CENTER);
			setVisible(true);
			carica.setEnabled(false);
		});
		nuovo.addActionListener((e) -> {
			nuovo.setEnabled(false);
			save.setVisible(true);
			load.setVisible(false);
			setVisible(false);
			add(save,BorderLayout.CENTER);		
			setVisible(true);
			carica.setEnabled(true);
		});

	}

	private JPanel createPanel() {
		JPanel p = new JPanel();
		JTextField t = new JTextField(20); 
		JLabel lb = new JLabel("Inserisci capitale");
		JTextField name = new JTextField(20);
		JLabel lb1 = new JLabel("Inserisci nome del file");
		JTextField vol = new JTextField(20);
		JLabel lb2 = new JLabel("Inserisci volume magazzino");
		JPanel cp = new JPanel();
		cp.setLayout(new GridLayout(6, 2));
		cp.add(lb1);
		cp.add(name);
		cp.add(lb);
		cp.add(t);
		cp.add(lb2);
		cp.add(vol);
		
		JButton b = new JButton("Crea");
		JButton bt = new JButton("Reparto Amministrativo");
		JButton bt1 = new JButton("Reparto Operativo");
		JPanel pan = new JPanel();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
		pan.add(b);
		pan1.add(bt);
		pan.add(bt1);
		
		bt.setVisible(false);
		bt1.setVisible(false);
		
		cp.add(pan);
		cp.add(pan1);
		cp.add(pan);
		b.addActionListener((e) ->{
			LeggiScriviAzienda a = new LeggiScriviAzienda();
			az= new Azienda(Integer.parseInt(t.getText()),Integer.parseInt(vol.getText()));
			a.setAzienda(az);
			a.scriviAzienda(name.getText());
			az=a.getAzienda();
			System.out.println(az);
			b.setVisible(false);
			bt.setVisible(true);
			bt1.setVisible(true);
		});
		p.add(cp);
		
		return p;
	}

	private JPanel loadPanel() {
		JPanel p = new JPanel();
		JTextField t = new JTextField(20); 
		JButton b = new JButton("Carica");
		JPanel tp = new JPanel();
		tp.add(t);
		tp.add(b);
		p.add(tp, BorderLayout.NORTH);

		b.addActionListener((e) -> {
			LeggiScriviAzienda a = new LeggiScriviAzienda();
			a.leggiAzienda(t.getText());
			az = a.getAzienda();
			JLabel jlt = new JLabel("Fondi totali : " + az.getFondiTotali());
			JLabel jle = new JLabel("Fondi reparto amministrativo esterno : " + az.getFondiEsterno());
			JLabel jli = new JLabel("Fondi reparto amministrativo interno : " + az.getFondiInterno());
			JLabel jlc = new JLabel("Numero cantieri aperti : " + az.getOperativo().getNumeroCantieriAperti());
			JPanel cp = new JPanel();
			cp.setLayout(new GridLayout(4, 1));
			cp.add(jlt);
			cp.add(jle);
			cp.add(jli);
			cp.add(jlc);
			p.setVisible(false);
			p.add(cp, BorderLayout.CENTER);
			p.setVisible(true);
			b.setEnabled(false);
		});

		return p;
	}
}
