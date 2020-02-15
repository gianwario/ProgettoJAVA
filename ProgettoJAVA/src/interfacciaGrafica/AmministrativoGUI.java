package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import azienda.Azienda;
import personale.*;
import utils.*;

public class AmministrativoGUI extends JFrame {

	private Azienda azienda;

	private JPanel jp0;
	private JPanel jp1;
	private JPanel jp2;
	private JPanel jp3;
	private JPanel jp4;

	private JTextArea textArea;
	private JRadioButton cb1;
	private JRadioButton cb2;
	private JRadioButton cb3;
	private JRadioButton cb4;
	private JRadioButton cb5;

	private JCheckBox rb1;
	private JCheckBox rb2;
	private JCheckBox rb3;
	private JCheckBox rb4;
	private JCheckBox rb12;
	private JCheckBox rb13;

	private JRadioButton rb5;
	private JRadioButton rb6;
	private JRadioButton rb7;
	private JRadioButton rb8;
	private JRadioButton rb9;
	private JRadioButton rb10;
	private JRadioButton rb11;

	public AmministrativoGUI(Azienda azienda) {

		this.azienda = azienda;

		setTitle("Gestisci Reparto Amministrativo");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) dim.getWidth(), (int) dim.getHeight() - 100);
		setResizable(false);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setLayout(new GridLayout(1, 2));

		add(interno());
		add(esterno());

		setVisible(true);
	}

	private JPanel interno() {

		JPanel p = new JPanel(); // pannello generico di sx
		JPanel sp1 = new JPanel(); // pannello del titolo
		JPanel sp2 = new JPanel(); // pannello dei report
		JPanel sp3 = new JPanel(); // pannello delle operazioni
		JLabel title = new JLabel("Gestisci sezione interna");

		p.setBorder(BorderFactory.createLineBorder(Color.black));
		sp1.setPreferredSize(new Dimension(730, 30));
		title.setFont(new Font("", Font.BOLD, 15));
		sp1.add(title, BorderLayout.NORTH);
		sp2 = reportInternoPanel();

		sp3.setBackground(new Color(204, 204, 0));
		sp3.setPreferredSize(new Dimension(760, 300));
		p.add(sp1, BorderLayout.NORTH);

		p.add(sp2);
		p.add(sp3, BorderLayout.SOUTH);

		return p;
	}

	private JPanel esterno() {

		JPanel p = new JPanel(); // pannello generico di dx
		JLabel title = new JLabel("Gestisci sezione esterna");

		p.setLayout(new GridLayout(3, 1));
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		title.setFont(new Font("", Font.BOLD, 15));
		p.add(title);

		return p;
	}

	private JPanel reportInternoPanel() {

		JPanel p = new JPanel();
		JPanel sp1 = new JPanel(); // pannello text area
		JPanel tmp = new JPanel();

		textArea = new JTextArea(25, 30);
		JScrollPane bar = new JScrollPane(textArea);

		p.setBorder((new TitledBorder(new EtchedBorder(), "Report")));
		// p.setBackground(Color.CYAN);
		p.setPreferredSize(new Dimension(680, 500));
		tmp.add(bar);
		// tmp.setBackground(Color.red);
		sp1.setPreferredSize(new Dimension(500, 450));
		sp1.add(tmp);
		p.setLayout(new GridLayout(1, 2));
		p.add(sp1);
		p.add(criteriPanel());

		return p;
	}

	private JPanel criteriPanel() {

		JPanel p = new JPanel();
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();

		JButton b1 = new JButton("Genera report");
		JButton b2 = new JButton("Cancella");

		cb1 = new JRadioButton("Impiegato");
		cb2 = new JRadioButton("Operaio");
		cb3 = new JRadioButton("Quadro");
		cb4 = new JRadioButton("Dirigente");
		cb5 = new JRadioButton("Tutti");

		rb1 = new JCheckBox("Pagato");
		rb2 = new JCheckBox("Non pagato");
		rb3 = new JCheckBox("Occupato");
		rb4 = new JCheckBox("Non occupato");
		rb12 = new JCheckBox("Conducente");
		rb13 = new JCheckBox("DirettoreCantiere");

		rb5 = new JRadioButton("Crescente");
		rb6 = new JRadioButton("Decrescente");
		rb7 = new JRadioButton("Ordine alfabetico");
		rb8 = new JRadioButton("Stipendio");
		rb9 = new JRadioButton("Anni di servizio");
		rb10 = new JRadioButton("Ore settimanali");
		rb11 = new JRadioButton("Patente");

		ButtonGroup g0 = new ButtonGroup();
		ButtonGroup g1 = new ButtonGroup();
		ButtonGroup g3 = new ButtonGroup();
		ButtonGroup g4 = new ButtonGroup();
		ButtonGroup g5 = new ButtonGroup();

		g0.add(cb1);
		g0.add(cb2);
		g0.add(cb3);
		g0.add(cb4);
		g0.add(cb5);

		g1.add(rb7);
		g1.add(rb8);
		g1.add(rb9);
		g1.add(rb10);
		g1.add(rb11);

		g3.add(rb5);
		g3.add(rb6);

		/*
		 * g4.add(rb2); g4.add(rb1); g5.add(rb3); g5.add(rb4);
		 */

		rb1.setSelected(true);
		cb1.setSelected(true);
		rb5.setSelected(true);
		rb7.setSelected(true);
		rb9.setEnabled(false);
		rb11.setEnabled(false);
		rb12.setEnabled(false);
		rb13.setEnabled(false);

		p.setPreferredSize(new Dimension(220, 950));
		// p.setBackground(Color.yellow);

		jp1.setBorder((new TitledBorder(new EtchedBorder(), "Tipo")));
		jp2.setBorder((new TitledBorder(new EtchedBorder(), "Stato")));
		jp3.setBorder((new TitledBorder(new EtchedBorder(), "In ordine")));
		jp4.setBorder((new TitledBorder(new EtchedBorder(), "Ordina per")));
		jp0.setPreferredSize(new Dimension(200, 30));
		jp1.setPreferredSize(new Dimension(220, 120));
		jp2.setPreferredSize(new Dimension(220, 110));
		jp4.setPreferredSize(new Dimension(220, 120));
		jp3.setPreferredSize(new Dimension(220, 60));

		jp1.setLayout(new GridLayout(5, 1));
		jp1.add(cb1);
		jp1.add(cb2);
		jp1.add(cb3);
		jp1.add(cb4);
		jp1.add(cb5);

		jp2.setLayout(new GridLayout(4, 2));
		jp2.add(rb1);
		jp2.add(rb2);
		jp2.add(rb3);
		jp2.add(rb4);
		jp2.add(rb12);
		jp2.add(new JLabel());
		jp2.add(rb13);

		jp4.setLayout(new GridLayout(5, 1));
		jp4.add(rb7);
		jp4.add(rb8);
		jp4.add(rb9);
		jp4.add(rb10);
		jp4.add(rb11);

		jp3.add(rb5);
		jp3.add(rb6);

		p.add(jp1);
		p.add(jp2);
		p.add(jp4);
		p.add(jp3);
		p.add(b1);
		p.add(b2);

		cb1.addActionListener(new CriteriListener());
		cb2.addActionListener(new CriteriListener());
		cb3.addActionListener(new CriteriListener());
		cb4.addActionListener(new CriteriListener());
		cb5.addActionListener(new CriteriListener());
		b1.addActionListener(new ReportGenerator());

		b2.addActionListener((e) -> {
			textArea.setText("");
		});

		return p;
	}

	private class CriteriListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (cb2.isSelected()) { // OPERAIO - PATENTE - CONDUCENTE
				jp4.setVisible(false);
				rb12.setEnabled(true);
				rb11.setEnabled(true);
				jp4.setVisible(true);
			}

			else {
				jp4.setVisible(false);
				rb7.setSelected(true);
				rb11.setSelected(false);
				rb11.setEnabled(false);
				rb12.setSelected(false);
				rb12.setEnabled(false);
				jp4.setVisible(true);
			}

			if (cb3.isSelected() ) { // DIRIGENTE || QUADRO - DIRIGE CANTIERE

				jp4.setVisible(false);
				rb13.setEnabled(true);
				jp4.setVisible(true);
			}

			else {
				jp4.setVisible(false);
				rb13.setSelected(false);
				rb13.setEnabled(false);
				jp4.setVisible(true);
			}

			if (cb1.isSelected()) { // IMPIEGATO - ORE SETTIMANALI

				jp4.setVisible(false);
				rb10.setEnabled(true);
				jp4.setVisible(true);
			}

			else {
				jp4.setVisible(false);
				rb7.setSelected(true);
				rb10.setSelected(false);
				rb10.setEnabled(false);
				jp4.setVisible(true);
			}

			if (cb4.isSelected()) { // DIRIGENTE - ANNI DI SERVIZIO

				jp4.setVisible(false);
				rb9.setEnabled(true);
				jp4.setVisible(true);
			}

			else {
				jp4.setVisible(false);
				rb7.setSelected(true);
				rb9.setSelected(false);
				rb9.setEnabled(false);
				jp4.setVisible(true);
			}

			if (cb5.isSelected()) { // TUTTI

				rb7.setSelected(true);
				jp4.setVisible(false);
				rb9.setSelected(false);
				rb9.setEnabled(false);
				rb10.setSelected(false);
				rb10.setEnabled(false);
				rb11.setSelected(false);
				rb11.setEnabled(false);
				rb12.setSelected(false);
				rb12.setEnabled(false);
				rb13.setSelected(false);
				rb13.setEnabled(false);
				jp4.setVisible(true);
			}

		}
	}

	private class ReportGenerator implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Selezionabile s1 = scegliCriterio1();
			Selezionatore selezione0 = new Selezionatore(azienda.getInterno().listaDipendenti(), s1);
			ArrayList<Dipendente> selezione = filtraSelezione(selezione0.seleziona());

			Comparabile c = scegliOrdine();
			Ordinatore risultato = new Ordinatore(selezione, c);

			if (rb5.isSelected())

				for (int i = 0; i < risultato.ordina().size(); i++)
					textArea.append(((Dipendente) risultato.ordina().get(i)).stampa());

			if (rb6.isSelected())
				for (int i = risultato.ordina().size() - 1; i >= 0; i--)
					textArea.append(((Dipendente) risultato.ordina().get(i)).stampa());

		}
	}

	private Selezionabile<Dipendente> scegliCriterio1() {

		Selezionabile<Dipendente> s;

		if (cb1.isSelected()) {
			s = (d) -> d.getClass().getSimpleName().equals("Impiegato");
			return s;
		}

		if (cb2.isSelected()) {
			s = (d) -> d.getClass().getSimpleName().equals("Operaio");
			return s;
		}

		if (cb3.isSelected()) {
			s = (d) -> d.getClass().getSimpleName().equals("Quadro");
			return s;
		}

		if (cb4.isSelected()) {
			s = (d) -> d.getClass().getSimpleName().equals("Dirigente");
			return s;
		}

		if (cb5.isSelected()) {
			s = (d) -> true;
			return s;
		}

		return null;
	}

	private ArrayList<Dipendente> filtraSelezione(ArrayList<Dipendente> list) {

		ArrayList<Dipendente> res = new ArrayList<Dipendente>();
		ArrayList<Dipendente> res1 = new ArrayList<Dipendente>();
		ArrayList<Dipendente> res2 = new ArrayList<Dipendente>();

		Selezionabile<Dipendente> s;

		if (rb1.isSelected() && !rb2.isSelected()) { // PAGATO

			s = (d) -> d.controllaStatoPagamento();

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res.contains(h)) {
					res.add(h);
				}
			}
		}

		if (rb2.isSelected() && !rb1.isSelected()) { // NON PAGATO

			s = (d) -> !d.controllaStatoPagamento();

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res.contains(h)) {
					res.add(h);
				}
			}
		}

		if ((rb2.isSelected() && rb1.isSelected()) || (!rb2.isSelected() && !rb1.isSelected())) { // PAGATO E NON

			s = (d) -> true;

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res.contains(h)) {
					res.add(h);
				}
			}
		}

		if (rb3.isSelected() && !rb4.isSelected()) { // OCCUPATO

			s = (d) -> d.controllaStatoDipendente();

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res1.contains(h)) {
					res1.add(h);
				}
			}
		}

		if (rb4.isSelected() && !rb3.isSelected()) { // NON OCCUPATO

			s = (d) -> !d.controllaStatoDipendente();

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res1.contains(h)) {
					res1.add(h);
				}
			}
		}

		if ((rb3.isSelected() && rb4.isSelected()) || (!rb3.isSelected() && !rb4.isSelected())) { // OCCUPATO E NON

			s = (d) -> true;

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res1.contains(h)) {
					res1.add(h);
				}
			}
		}

		if (rb12.isSelected()) {

			s = (o) -> ((Operaio) o).isConducente();

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res2.contains(h)) {
					res2.add(h);
				}

			}
			res1.retainAll(res2);
		}
		
		if (rb13.isSelected()) {

			s = (o) -> ((Quadro) o).isDirigente();

			for (Dipendente h : list) {

				if (s.seleziona(h) && !res2.contains(h)) {
					res2.add(h);
				}

			}
			res1.retainAll(res2);
		}
		
		res.retainAll(res1);
		return res;

	}

	private Comparabile<Dipendente> scegliOrdine() {

		Comparabile<Dipendente> c;

		if (rb7.isSelected()) {
			c = (d1, d2) -> d1.getCognome().compareTo(d2.getCognome());
			return c;
		}

		if (rb8.isSelected()) {
			c = (d1, d2) -> d1.checkPaga() - d2.checkPaga();
			return c;
		}

		if (rb9.isSelected()) {
			c = (d1, d2) -> ((Dirigente) d1).controllaAnniDiServizio() - ((Dirigente) d2).controllaAnniDiServizio();
			return c;
		}

		if (rb10.isSelected()) {
			c = (d1, d2) -> ((Impiegato) d1).getOreSettimanali() - ((Impiegato) d2).getOreSettimanali();
			return c;
		}

		if (rb11.isSelected()) {
			c = (d1, d2) -> ((Operaio) d1).getPatente().compareTo(((Operaio) d2).getPatente());
			return c;
		}

		return null;

	}
}
