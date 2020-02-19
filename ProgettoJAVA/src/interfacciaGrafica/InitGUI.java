package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	
	private String filename;

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
			add(save, BorderLayout.CENTER);
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

		pan.add(b);
		pan1.add(bt);
		pan.add(bt1);

		bt.setVisible(false);
		bt1.setVisible(false);

		cp.add(pan);
		cp.add(pan1);
		cp.add(pan);
		b.addActionListener((e) -> {
			LeggiScriviAzienda a = new LeggiScriviAzienda();
			az = new Azienda(Integer.parseInt(t.getText()), Integer.parseInt(vol.getText()));

			a.setAzienda(az);
			a.scriviAzienda(name.getText());
			az = a.getAzienda();
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
		JPanel p2 = new JPanel();
		JTextField t = new JTextField(20);
		t.setText("azienda.dat"); //TOGLI
		JButton b = new JButton("Carica");
		JPanel tp = new JPanel();
		JLabel error = new JLabel("  Errore: file non trovato!");
		JButton b1 = new JButton("Reparto amministrativo");
		JButton b2 = new JButton("Reparto operativo");
		JButton b3 = new JButton("Aggiorna info");
		JButton b4 = new JButton("Salva ed esci");
		JLabel jlt = new JLabel();
		JLabel jle = new JLabel();
		JLabel jli = new JLabel();
		JLabel jlc = new JLabel();

		JPanel cp = new JPanel();
		JPanel cp2 = new JPanel();
		JPanel cp3 = new JPanel();
		JPanel cp4 = new JPanel();
		error.setForeground(Color.red);
		error.setFont(new Font("", Font.BOLD, 18));
		tp.add(t);
		tp.add(b);
		p2.add(error);
		p.add(tp, BorderLayout.NORTH);
		p.add(p2, BorderLayout.SOUTH);
		p2.setVisible(false);

		b.addActionListener((e) -> {

			LeggiScriviAzienda a = new LeggiScriviAzienda();
			try {
				
				a.leggiAzienda(t.getText());
				az = a.getAzienda();
				filename = t.getText();
				
				jlt.setText("Fondi totali : " + az.getFondiTotali());
				jle.setText("Fondi del reparto amministrativo esterno: $" + az.getFondiEsterno());
				jli.setText("Fondi del reparto amministrativo interno: $" + az.getFondiInterno());
				jlc.setText("Numero cantieri aperti: " + az.getOperativo().getNumeroCantieriAperti());
				
				p2.setVisible(false);
				cp3.setBorder((new TitledBorder(new EtchedBorder(), "Gestisci")));

				cp.setLayout(new GridLayout(4, 1));
				cp4.setLayout(new GridLayout(2, 1));
				cp.add(jlt);
				cp.add(jle);
				cp.add(jli);
				cp.add(jlc);
				cp3.add(b1);
				cp3.add(b2);
				cp4.add(b3);
				cp4.add(b4);
				cp2.add(cp3);
				cp2.add(cp4);
				p.setVisible(false);
				p.add(cp, BorderLayout.CENTER);
				p.add(cp2, BorderLayout.SOUTH);
				p.setVisible(true);
				b.setEnabled(false);

			} catch (NullPointerException ex) {
				t.setText("");
				p2.setVisible(true);
			}
			
			b1.addActionListener((e1) -> {				
				
				new AmministrativoGUI(az);				

			});

			b2.addActionListener((e2) -> {				
				
				new OperativoGUI(az);				

			});
			
			b4.addActionListener((e4) -> {				
				
				az.equilibriaFondi();
				a.scriviAzienda(filename);
				this.setVisible(false);				

			});
			
			b3.addActionListener((e3) -> {				
				
				cp.setVisible(false);				
				jlt.setText("Fondi totali : " + az.getFondiTotali());
				jle.setText("Fondi del reparto amministrativo esterno: $" + az.getFondiEsterno());
				jli.setText("Fondi del reparto amministrativo interno: $" + az.getFondiInterno());
				jlc.setText("Numero cantieri aperti: " + az.getOperativo().getNumeroCantieriAperti());
				cp.setVisible(true);				

			});
		});

		return p;
	}
}
