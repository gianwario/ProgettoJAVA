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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import azienda.Azienda;
import eccezioni.CapacitaInsufficienteException;
import esterni.Fornitore;
import personale.*;
import risorse.Attrezzo;
import risorse.Macchinario;
import risorse.Prodotto;
import utils.*;
import esterni.Commissione;

public class AmministrativoGUI extends JFrame {

	private Azienda azienda;

	private JPanel jp0;
	private JPanel jp1;
	private JPanel jp2;
	private JPanel jp3;
	private JPanel jp4;

	private JPanel jjp0;
	private JPanel jjp1;
	private JPanel jjp2;
	private JPanel jjp3;
	private JPanel jjp4;

	private JTextArea textArea;
	private JTextArea textArea2;
	private JRadioButton cb1;
	private JRadioButton cb2;
	private JRadioButton cb3;
	private JRadioButton cb4;
	private JRadioButton cb5;
	private JRadioButton es0;
	private JRadioButton es1;
	private JRadioButton es2;
	private JRadioButton es3;
	private JRadioButton es4;
	private JRadioButton es5;
	private JRadioButton es6;
	private JRadioButton es7;
	private JRadioButton es8;
	private JRadioButton es9;
	private JRadioButton es10;
	private JRadioButton es11;
	private JRadioButton es12;
	private JRadioButton es13;
	private JRadioButton es14;

	private JRadioButton rb5;
	private JRadioButton rb6;
	private JRadioButton rb7;
	private JRadioButton rb8;
	private JRadioButton rb9;
	private JRadioButton rb10;
	private JRadioButton rb11;

	private JCheckBox rb1;
	private JCheckBox rb2;
	private JCheckBox rb3;
	private JCheckBox rb4;
	private JCheckBox rb12;
	private JCheckBox rb13;

	private JLabel fondi;
	private JLabel fondi2;
	private JLabel cap;
	private JTextField nomeF;
	private JTextField mat;
	private JTextField min;
	private JTextField max;

	JComboBox obox;
	JComboBox obox2;
	JComboBox obox3;

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
		JPanel sp3 = opInterno(); // pannello delle operazioni
		fondi = new JLabel("Gestisci sezione interna    Fondi: $" + azienda.getFondiInterno());

		p.setBorder(BorderFactory.createLineBorder(Color.black));
		sp1.setPreferredSize(new Dimension(730, 30));
		fondi.setFont(new Font("", Font.BOLD, 15));
		sp1.add(fondi, BorderLayout.NORTH);
		sp2 = reportInternoPanel();

		sp3.setPreferredSize(new Dimension(760, 180));
		p.add(sp1, BorderLayout.NORTH);

		p.add(sp2);
		p.add(sp3, BorderLayout.SOUTH);

		return p;
	}

	private JPanel esterno() {

		JPanel p = new JPanel(); // pannello generico di dx
		JPanel sp1 = new JPanel();
		JPanel sp2 = new JPanel();
		JPanel sp3 = opEsterno();

		sp1.setPreferredSize(new Dimension(500, 30));
		sp1.setLayout(new GridLayout(2, 1));

		fondi2 = new JLabel("Gestisci sezione esterna    Fondi: $" + (int) azienda.getFondiEsterno());
		cap = new JLabel("Capcita occupata del magazzino: " + (int) azienda.getMagazzino().getCapacitaOccupata() + " / "
				+ (int) azienda.getMagazzino().getCapacitaMax() + " m^3");

		// p.setLayout(new GridLayout(3, 1));
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		fondi2.setFont(new Font("", Font.BOLD, 15));
		cap.setFont(new Font("", Font.BOLD, 13));
		sp1.add(fondi2);
		sp1.add(cap);
		p.add(sp1, BorderLayout.NORTH);

		sp2 = reportEsternoPanel();

		sp3.setPreferredSize(new Dimension(760, 220));
		p.add(sp1, BorderLayout.NORTH);

		p.add(sp2);
		p.add(sp3, BorderLayout.SOUTH);

		return p;
	}

	private JPanel reportInternoPanel() {

		JPanel p = new JPanel();
		JPanel sp1 = new JPanel(); // pannello text area
		JPanel tmp = new JPanel();

		textArea = new JTextArea(25, 30);
		JScrollPane bar = new JScrollPane(textArea);

		p.setBorder((new TitledBorder(new EtchedBorder(), "Report personale")));
		p.setPreferredSize(new Dimension(680, 500));
		textArea.setEditable(false);
		tmp.add(bar);

		sp1.setPreferredSize(new Dimension(500, 450));
		sp1.add(tmp);
		p.setLayout(new GridLayout(1, 2));
		p.add(sp1);
		p.add(criteriPanel());

		return p;
	}

	private JPanel opInterno() {

		JPanel p = new JPanel();
		JPanel op1 = new JPanel();
		JPanel op2 = new JPanel();
		JPanel op3 = new JPanel();
		JPanel op4 = new JPanel();

		p.setLayout(new GridLayout(4, 1));
		p.setBorder((new TitledBorder(new EtchedBorder(), "Gestisci personale")));

		JLabel olabel = new JLabel("Dipendente: ");
		JLabel olabel2 = new JLabel("Scegli un'operazione ");
		obox = new JComboBox(azienda.getInterno().listaDipendenti().toArray());

		JButton ob1 = new JButton("Paga tutti");
		JButton ob2 = new JButton("Resetta stati pagamento");
		JButton ob3 = new JButton("Assumi dipendente");
		JButton ob4 = new JButton("Licenzia selezionato");
		JButton ob5 = new JButton("Paga selezionato");
		JButton ob6 = new JButton("Registra nuovo anno di servizio");
		JButton ob7 = new JButton("Resetta stato pagamento per selezionato");

		olabel2.setFont(new Font("", Font.BOLD, 12));

		op1.add(ob1);
		op1.add(ob2);
		op1.add(ob3);
		op1.add(ob6);

		op2.add(olabel2);

		op3.add(olabel);
		op3.add(obox);
		op4.add(ob4);
		op4.add(ob5);
		op4.add(ob7);

		p.add(op3);
		p.add(op4);
		p.add(op2);
		p.add(op1);

		ob1.addActionListener((e) -> {

			ArrayList<Dipendente> list = new ArrayList<Dipendente>();

			for (Dipendente d : azienda.getInterno().listaDipendenti())
				if (!d.controllaStatoPagamento())
					list.add(d);
			azienda.getInterno().effettuaPagamento();
			olabel2.setText("Effettuato pagamento di tutti i dipendenti non pagati");
			fondi.setText("Gestisci sezione interna    Fondi: $" + azienda.getFondiInterno());
			textArea.setText("PAGATI: \n\n");
			for (Dipendente d : list)
				textArea.append(d.stampa());
		});

		ob2.addActionListener((e) -> {

			azienda.getInterno().resettaStatoPagamenti();
			textArea.setText("");
			olabel2.setText("Effettuato reset di tutti gli stati pagamento");

		});
		ob5.addActionListener((e) -> {

			if (((Dipendente) obox.getSelectedItem()).controllaStatoPagamento() == true) {
				olabel2.setText(((Dipendente) obox.getSelectedItem()).getNome() + " "
						+ ((Dipendente) obox.getSelectedItem()).getCognome() + " giï¿½ pagato!");
				return;
			}

			azienda.getInterno().effettuaPagamento((Dipendente) obox.getSelectedItem());

			olabel2.setText("Effettuato pagamento di " + ((Dipendente) obox.getSelectedItem()).getNome() + " "
					+ ((Dipendente) obox.getSelectedItem()).getCognome() + " di $"
					+ ((Dipendente) obox.getSelectedItem()).checkPaga());

			fondi.setText("Gestisci sezione interna    Fondi: $" + azienda.getFondiInterno());
		});

		ob4.addActionListener((e) -> {

			azienda.getInterno().licenziaDipendente((Dipendente) obox.getSelectedItem());
			op3.setVisible(false);
			olabel2.setText("Effettuato licenziamento di " + ((Dipendente) obox.getSelectedItem()).getNome() + " "
					+ ((Dipendente) obox.getSelectedItem()).getCognome());
			obox.removeItem(obox.getSelectedItem());
			op3.setVisible(true);
		});

		ob6.addActionListener((e) -> {

			ArrayList<Dipendente> list = new ArrayList<Dipendente>();

			for (Dipendente d : azienda.getInterno().listaDipendenti())
				if (d instanceof Dirigente) {
					list.add(d);
					((Dirigente) d).nuovoAnnoDiServizio();
				}
			olabel2.setText("Inizio nuovo anno di servizio registrato per tutti i dirigenti");
			textArea.setText("MODIFICATI: \n\n");
			for (Dipendente d : list)
				textArea.append(d.stampa());
		});

		ob3.addActionListener((e) -> {

			new AssumiDipendente();

		});

		ob7.addActionListener((e) -> {

			if (((Dipendente) obox.getSelectedItem()).controllaStatoPagamento() == false) {
				olabel2.setText(((Dipendente) obox.getSelectedItem()).getNome() + " "
						+ ((Dipendente) obox.getSelectedItem()).getCognome() + " non è stato ancora pagato!");
				return;
			}

			((Dipendente) obox.getSelectedItem()).resettaStatoPagamento();

			olabel2.setText("Effettuato reset di pagamento di " + ((Dipendente) obox.getSelectedItem()).getNome() + " "
					+ ((Dipendente) obox.getSelectedItem()).getCognome());
		});

		return p;
	}

	private JPanel reportEsternoPanel() {

		JPanel p = new JPanel();
		JPanel sp1 = new JPanel(); // pannello text area
		JPanel tmp = new JPanel();

		textArea2 = new JTextArea(25, 30);
		JScrollPane bar2 = new JScrollPane(textArea2);

		p.setBorder((new TitledBorder(new EtchedBorder(), "Report magazzino")));
		p.setPreferredSize(new Dimension(680, 470));
		textArea2.setEditable(false);
		tmp.add(bar2);

		sp1.setPreferredSize(new Dimension(500, 450));
		sp1.add(tmp);
		p.setLayout(new GridLayout(1, 2));
		p.add(sp1);
		p.add(criteriPanel2());

		return p;
	}

	private JPanel opEsterno() {

		JPanel p = new JPanel();
		JPanel op1 = new JPanel();
		JPanel op2 = new JPanel();
		JPanel op3 = new JPanel();
		JPanel op4 = new JPanel();

		p.setLayout(new GridLayout(4, 1));
		p.setBorder((new TitledBorder(new EtchedBorder(), "Gestisci esterni")));

		JLabel olabel = new JLabel("Commissioni: ");
		JLabel olabel1 = new JLabel("Fornitori: ");
		JLabel olabel2 = new JLabel("Scegli un'operazione ");
		obox2 = new JComboBox(azienda.getEsterno().getListaCommissioni().toArray());
		ArrayList<String> nomi = new ArrayList<String>();
		for (Fornitore f : azienda.getEsterno().getListaFornitori())
			nomi.add(f.getNome());
		obox3 = new JComboBox(nomi.toArray());

		JButton ob1 = new JButton("Paga permessi ente ");
		JButton ob2 = new JButton("Chiudi commissione");
		JButton ob3 = new JButton("Registra nuova commissione");
		JButton ob4 = new JButton("Visualizza catalogo");
		JButton ob5 = new JButton("Rimuovi selezionato");
		JButton ob6 = new JButton("Aggiungi nuovo fornitore");

		ob1.setEnabled(false);
		ob2.setEnabled(false);

		olabel2.setFont(new Font("", Font.BOLD, 12));

		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				if (!((Commissione) obox2.getSelectedItem()).getOttenimentoPermessi()) {
					ob1.setEnabled(true);
					ob2.setEnabled(false);
				}

				if (((Commissione) obox2.getSelectedItem()).getCompletamento()) {
					ob1.setEnabled(false);
					ob2.setEnabled(true);
				}

				if (!((Commissione) obox2.getSelectedItem()).getCompletamento()
						&& ((Commissione) obox2.getSelectedItem()).getOttenimentoPermessi()) {
					ob1.setEnabled(false);
					ob2.setEnabled(false);
				}

			}
		}

		obox2.addActionListener(new ButtonListener());
		ob1.addActionListener(new ButtonListener());
		ob2.addActionListener(new ButtonListener());

		ob1.addActionListener((e) -> {

			obox2.setVisible(false);
			Commissione c = (Commissione) obox2.getSelectedItem();
			azienda.getEsterno().pagaPermessi(c);
			fondi2.setText("Gestisci sezione esterna    Fondi: $" + (int) azienda.getFondiEsterno());
			obox2.setVisible(true);
		});

		ob2.addActionListener((e) -> {

			obox2.setVisible(false);
			Commissione c = (Commissione) obox2.getSelectedItem();
			azienda.getEsterno().chiudiCommissioni(c);
			fondi2.setText("Gestisci sezione esterna    Fondi: $" + (int) azienda.getFondiEsterno());
			obox2.removeItem(c);
			obox2.setVisible(true);
		});

		ob3.addActionListener((e) -> {
			new AggiungiCommissione();
		});

		ob4.addActionListener((e) -> {

			String s = (String) obox3.getSelectedItem();

			for (Fornitore f : azienda.getEsterno().getListaFornitori())
				if (f.getNome().equals(s)) {
					textArea2.setText("Catalogo di " + f.getNome() + ": \n");
					for (Prodotto p2 : f.getCatalogo())
						textArea2.append(p2.stampa() + "\n");
				}
		});

		ob5.addActionListener((e) -> {

			String s = (String) obox3.getSelectedItem();
			Fornitore dar = new Fornitore(null);
			for (Fornitore f : azienda.getEsterno().getListaFornitori())
				if (f.getNome().equals(s)) {
					dar = f;
				}

			obox3.setVisible(false);
			obox3.removeItem(obox3.getSelectedItem());
			azienda.getEsterno().rimuoviFornitore(dar);
			obox3.setVisible(true);
		});

		ob6.addActionListener((e) -> {

			new AggiungiFornitore();
		});

		op1.add(olabel);
		op1.add(obox2);
		op2.add(ob1);
		op2.add(ob2);
		op2.add(ob3);

		op3.add(olabel1);
		op3.add(obox3);

		op4.add(ob4);
		op4.add(ob5);
		op4.add(ob6);

		p.add(op1);
		p.add(op2);
		p.add(op3);
		p.add(op4);

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

		cb1.setSelected(true);
		rb5.setSelected(true);
		rb7.setSelected(true);
		rb9.setEnabled(false);
		rb11.setEnabled(false);
		rb12.setEnabled(false);
		rb13.setEnabled(false);

		p.setPreferredSize(new Dimension(220, 950));

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

	private JPanel criteriPanel2() {

		JPanel p = new JPanel();
		jjp0 = new JPanel();
		jjp1 = new JPanel();
		jjp2 = new JPanel();
		jjp3 = new JPanel();
		jjp4 = new JPanel();

		JButton b1 = new JButton("Genera report");
		JButton b2 = new JButton("Cancella");
		JButton b3 = new JButton("Acquista nuovi prodotti");

		JLabel minl = new JLabel("                Min");
		JLabel maxl = new JLabel("              Max");

		nomeF = new JTextField(10);
		min = new JTextField(6);
		max = new JTextField(6);
		mat = new JTextField(10);

		es0 = new JRadioButton("Tutti");
		es1 = new JRadioButton("Nome prodotto: ");
		es2 = new JRadioButton("Range");
		es3 = new JRadioButton("Prezzo");
		es4 = new JRadioButton("Volume");
		es5 = new JRadioButton("Magazzino");
		es6 = new JRadioButton("Catalogo fornitore");
		es7 = new JRadioButton("Materiale:");
		es8 = new JRadioButton("Attrezzo");
		es9 = new JRadioButton("Macchinario");
		es10 = new JRadioButton("B");
		es11 = new JRadioButton("C");
		es12 = new JRadioButton("D");
		es13 = new JRadioButton("Patente");
		es14 = new JRadioButton("Tutto");

		ButtonGroup g1 = new ButtonGroup();
		ButtonGroup g2 = new ButtonGroup();
		ButtonGroup g3 = new ButtonGroup();
		ButtonGroup g4 = new ButtonGroup();
		ButtonGroup g5 = new ButtonGroup();

		g1.add(es0);
		g1.add(es1);
		g1.add(es2);
		g1.add(es7);
		g1.add(es13);

		g2.add(es3);
		g2.add(es4);

		g3.add(es5);
		g3.add(es6);

		g4.add(es10);
		g4.add(es11);
		g4.add(es12);

		g5.add(es8);
		g5.add(es9);
		g5.add(es14);

		es14.setSelected(true);
		es5.setSelected(true);
		es4.setEnabled(false);
		es3.setEnabled(false);
		es7.setEnabled(false);
		es13.setEnabled(false);
		es10.setEnabled(false);
		es11.setEnabled(false);
		es12.setEnabled(false);
		nomeF.setEditable(false);
		mat.setEditable(false);
		min.setEditable(false);
		max.setEditable(false);

		es0.addActionListener(new CriteriListener2());
		es1.addActionListener(new CriteriListener2());
		es2.addActionListener(new CriteriListener2());
		es3.addActionListener(new CriteriListener2());
		es4.addActionListener(new CriteriListener2());
		es5.addActionListener(new CriteriListener2());
		es6.addActionListener(new CriteriListener2());
		es7.addActionListener(new CriteriListener2());
		es8.addActionListener(new CriteriListener2());
		es9.addActionListener(new CriteriListener2());
		es10.addActionListener(new CriteriListener2());
		es11.addActionListener(new CriteriListener2());
		es12.addActionListener(new CriteriListener2());
		es13.addActionListener(new CriteriListener2());
		es14.addActionListener(new CriteriListener2());

		b3.addActionListener((e) -> {

			new AcquistoFrame();
		});

		p.setPreferredSize(new Dimension(220, 910));
		jjp0.setPreferredSize(new Dimension(250, 20));
		jjp1.setPreferredSize(new Dimension(250, 210));
		jjp2.setPreferredSize(new Dimension(270, 60));
		jjp3.setPreferredSize(new Dimension(270, 40));
		jjp4.setPreferredSize(new Dimension(275, 55));
		jjp1.setBorder((new TitledBorder(new EtchedBorder(), "Cerca per")));
		jjp2.setBorder((new TitledBorder(new EtchedBorder(), "Cerca in")));
		jjp4.setBorder((new TitledBorder(new EtchedBorder(), "Tipo")));

		jjp1.setLayout(new GridLayout(8, 2));

		jjp1.add(es0);
		jjp1.add(new JPanel());
		jjp1.add(es1);
		jjp1.add(nomeF);
		jjp1.add(es7);
		jjp1.add(mat);
		jjp1.add(es13);
		jjp1.add(jjp0);
		jjp1.add(es2);
		jjp1.add(new JLabel(""));
		jjp1.add(es3);
		jjp1.add(es4);
		jjp1.add(minl);
		jjp1.add(maxl);
		jjp1.add(min);
		jjp1.add(max);

		jjp2.add(es5);
		jjp2.add(es6);

		jjp0.add(es10);
		jjp0.add(es11);
		jjp0.add(es12);

		jjp3.add(b1);
		jjp3.add(b2);

		jjp4.add(es8);
		jjp4.add(es9);
		jjp4.add(es14);

		p.add(jjp4);
		p.add(jjp1);
		p.add(jjp2);
		p.add(jjp3);
		p.add(b3);

		b1.addActionListener(new ReportGenerator2());
		b2.addActionListener((e) -> {
			textArea2.setText("");
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

			if (cb3.isSelected()) { // DIRIGENTE || QUADRO - DIRIGE CANTIERE

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

	private class CriteriListener2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (es8.isSelected()) {

				es0.setEnabled(true);
				es1.setEnabled(true);
				es2.setEnabled(true);
				es7.setEnabled(true);
				es13.setEnabled(false);

			}

			if (es9.isSelected()) {

				es0.setEnabled(true);
				es1.setEnabled(true);
				es2.setEnabled(true);
				es7.setEnabled(false);
				es13.setEnabled(true);

			}

			if (es14.isSelected()) {

				es0.setEnabled(true);
				es1.setEnabled(true);
				es2.setEnabled(true);
				es7.setEnabled(false);
				es10.setEnabled(false);
				es11.setEnabled(false);
				es12.setEnabled(false);
				es13.setEnabled(false);
			}

			if (!es13.isSelected()) {

				es10.setEnabled(false);
				es11.setEnabled(false);
				es12.setEnabled(false);
			}

			if (es8.isSelected() && es9.isSelected()) {

				es0.setEnabled(true);
				es1.setEnabled(true);
				es2.setEnabled(true);
				es7.setEnabled(false);
				es10.setEnabled(false);
				es13.setEnabled(false);

			}

			if (es0.isSelected()) {

				es4.setEnabled(false);
				es3.setEnabled(false);
				nomeF.setEditable(false);
				mat.setEditable(false);
				min.setEditable(false);
				max.setEditable(false);
				es10.setEnabled(false);
				es11.setEnabled(false);
				es12.setEnabled(false);
			}

			if (es1.isSelected()) {

				es4.setEnabled(false);
				es3.setEnabled(false);
				nomeF.setEditable(true);
				mat.setEditable(false);
				min.setEditable(false);
				max.setEditable(false);
				es10.setEnabled(false);
				es11.setEnabled(false);
				es12.setEnabled(false);
			}

			if (es2.isSelected()) {

				es3.setEnabled(true);
				es4.setEnabled(true);
				nomeF.setEditable(false);
				mat.setEditable(false);
				min.setEditable(true);
				max.setEditable(true);
				es10.setEnabled(false);
				es11.setEnabled(false);
				es12.setEnabled(false);
			}

			if (es7.isSelected()) {

				es4.setEnabled(false);
				es3.setEnabled(false);
				nomeF.setEditable(false);
				mat.setEditable(true);
				min.setEditable(false);
				max.setEditable(false);
				es10.setEnabled(false);
				es11.setEnabled(false);
				es12.setEnabled(false);
			}

			if (es13.isSelected()) {

				es10.setEnabled(true);
				es11.setEnabled(true);
				es12.setEnabled(true);
				min.setEditable(false);
				max.setEditable(false);
				nomeF.setEditable(false);
				mat.setEditable(false);
				es4.setEnabled(false);
				es3.setEnabled(false);

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

	private class ReportGenerator2 implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (es5.isSelected())
				new ReportGeneratorMagazzino().actionPerformed(e);

			else if (es6.isSelected())
				new ReportGeneratorFornitore().actionPerformed(e);

			nomeF.setText("");
			min.setText("");
			max.setText("");
			mat.setText("");
		}
	}

	private class ReportGeneratorMagazzino implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Selezionabile<Prodotto> s = scegliCriterio2();
			Selezionatore<Prodotto> selezione = new Selezionatore<Prodotto>(azienda.getMagazzino().getListaProdotti(),
					s);

			textArea2.setText("TROVATI IN MAGAZZINO: \n\n");
			for (Prodotto p : selezione.seleziona())
				textArea2.append(p.stampa() + "\n");

		}
	}

	private class ReportGeneratorFornitore implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Selezionatore<Prodotto> selezione;
			Selezionabile<Prodotto> s = scegliCriterio2();
			ArrayList<Prodotto> res = new ArrayList<Prodotto>();
			textArea2.setText("TROVATI IN CATALOGHI: \n\n");

			for (Fornitore f : azienda.getEsterno().getListaFornitori()) {

				selezione = new Selezionatore<Prodotto>(f.getCatalogo(), s);
				res = selezione.seleziona();
				if (res.size() > 0) {
					textArea2.append("Catalogo di " + f.getNome() + ":\n");
					for (Prodotto p : res)
						textArea2.append(p.stampa() + "\n");
					textArea2.append("\n");
				}
			}
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

	private Selezionabile<Prodotto> scegliCriterio2() {

		Selezionabile<Prodotto> s = null;

		if (es0.isSelected()) {

			if (es8.isSelected()) {
				s = (p) -> {
					if (p instanceof Attrezzo)
						return true;
					else
						return false;
				};
			}

			if (es9.isSelected()) {
				s = (p) -> {
					if (p instanceof Macchinario)
						return true;
					else
						return false;
				};
			}

			if (es14.isSelected()) {
				s = (p) -> true;
			}
		}

		if (es1.isSelected()) {

			if (es8.isSelected()) {
				s = (p) -> {
					if (p instanceof Attrezzo)
						return p.getNome().equalsIgnoreCase(nomeF.getText());
					else
						return false;
				};
			}

			if (es9.isSelected()) {
				s = (p) -> {
					if (p instanceof Macchinario)
						return p.getNome().equalsIgnoreCase(nomeF.getText());
					else
						return false;
				};
			}

			if (es14.isSelected()) {
				s = (p) -> p.getNome().equalsIgnoreCase(nomeF.getText());
			}
		}

		if (es7.isSelected()) {
			s = (p) -> {
				if (p instanceof Attrezzo)
					return ((Attrezzo) p).getMateriale().equals(mat.getText());
				else
					return false;
			};
		}

		if (es13.isSelected()) {

			if (es10.isSelected()) {
				s = (p) -> {
					if (p instanceof Macchinario)
						return ((Macchinario) p).getTipoPatente().equals("B");
					else
						return false;
				};

			}

			if (es11.isSelected()) {
				s = (p) -> {
					if (p instanceof Macchinario)
						return ((Macchinario) p).getTipoPatente().equals("C");
					else
						return false;
				};

			}

			if (es12.isSelected()) {
				s = (p) -> {
					if (p instanceof Macchinario)
						return ((Macchinario) p).getTipoPatente().equals("D");
					else
						return false;
				};

			}

		}

		if (es2.isSelected()) {

			if (es8.isSelected()) {

				s = (p) -> {
					if (p instanceof Attrezzo) {
						if (es3.isSelected())
							return (p.getPrezzo() >= Double.parseDouble(min.getText()))
									&& (p.getPrezzo() <= Double.parseDouble(max.getText()));
						if (es4.isSelected())
							return (p.getVolume() >= Double.parseDouble(min.getText()))
									&& (p.getVolume() <= Double.parseDouble(max.getText()));
						return false;
					} else
						return false;
				};
			}

			if (es9.isSelected()) {

				s = (p) -> {
					if (p instanceof Macchinario) {
						if (es3.isSelected())
							return (p.getPrezzo() >= Double.parseDouble(min.getText()))
									&& (p.getPrezzo() <= Double.parseDouble(max.getText()));
						if (es4.isSelected())
							return (p.getVolume() >= Double.parseDouble(min.getText()))
									&& (p.getVolume() <= Double.parseDouble(max.getText()));
						return false;
					} else
						return false;
				};
			}

			if (es14.isSelected()) {

				s = (p) -> {
					if (es3.isSelected())
						return (p.getPrezzo() >= Double.parseDouble(min.getText()))
								&& (p.getPrezzo() <= Double.parseDouble(max.getText()));
					if (es4.isSelected())
						return (p.getVolume() >= Double.parseDouble(min.getText()))
								&& (p.getVolume() <= Double.parseDouble(max.getText()));
					return false;
				};
			}
		}

		return s;
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

	private class AssumiDipendente extends JFrame {

		public AssumiDipendente() {

			setTitle("Assumi nuovo dipendente");

			setSize(350, 340);
			setVisible(true);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
			setResizable(false);

			JPanel p = new JPanel();
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();
			JPanel p5 = new JPanel();
			JPanel p6 = new JPanel();
			JPanel p7 = new JPanel();

			JRadioButton r1 = new JRadioButton("B");
			JRadioButton r2 = new JRadioButton("C");
			JRadioButton r3 = new JRadioButton("D");

			ButtonGroup group = new ButtonGroup();
			group.add(r1);
			group.add(r2);
			group.add(r3);

			r1.setEnabled(false);
			r2.setEnabled(false);
			r3.setEnabled(false);

			p1.setPreferredSize(new Dimension(380, 50));
			p2.setPreferredSize(new Dimension(380, 30));
			p3.setPreferredSize(new Dimension(380, 30));
			p4.setPreferredSize(new Dimension(380, 30));
			p5.setPreferredSize(new Dimension(380, 30));
			p6.setPreferredSize(new Dimension(380, 30));

			String[] tipo = { "Impiegato", "Operaio", "Quadro", "Dirigente" };

			JLabel message = new JLabel("Inserisci informazioni per l'assunzione");
			message.setFont(new Font("", Font.BOLD, 15));

			JLabel i1 = new JLabel("Nome: ");
			JLabel i2 = new JLabel("Cognome: ");
			JLabel i3 = new JLabel("Categoria: ");
			JLabel i4 = new JLabel("Tipo patente: ");
			JLabel i5 = new JLabel("Ore settimanali: ");

			JTextField t1 = new JTextField(10);
			JTextField t2 = new JTextField(10);
			JComboBox t3 = new JComboBox(tipo);
			JTextField t5 = new JTextField(6);

			JButton ok = new JButton("Assumi");

			t3.addActionListener((e) -> {

				if (t3.getSelectedItem().equals("Operaio")) {

					r1.setEnabled(true);
					r2.setEnabled(true);
					r3.setEnabled(true);

					r1.setSelected(true);
					t5.setEditable(false);
					t5.setText("");

				}

				else if (t3.getSelectedItem().equals("Impiegato")) {

					r1.setEnabled(false);
					r2.setEnabled(false);
					r3.setEnabled(false);

					t5.setEditable(true);
				}

				else if (t3.getSelectedItem().equals("Quadro")) {

					r1.setEnabled(false);
					r2.setEnabled(false);
					r3.setEnabled(false);

					t5.setEditable(false);
					t5.setText("");
				}

				else if (t3.getSelectedItem().equals("Dirigente")) {

					r1.setEnabled(false);
					r2.setEnabled(false);
					r3.setEnabled(false);

					t5.setEditable(false);
					t5.setText("");
				}

			});

			ok.addActionListener((e) -> {

				if (t3.getSelectedItem().equals("Impiegato")) {

					Impiegato im = new Impiegato(t1.getText(), t2.getText(), Integer.parseInt(t5.getText()));

					azienda.getInterno().assumiDipendente(im);
					textArea.setText("ASSUNZIONE EFFETTUATA \n Dettagli assunzione: \n" + im.stampa());
					obox.setVisible(false);
					obox.addItem(im);
					obox.setVisible(true);
				}

				if (t3.getSelectedItem().equals("Operaio")) {

					String patente = "";

					if (r1.isSelected())
						patente = "B";
					if (r2.isSelected())
						patente = "C";
					if (r3.isSelected())
						patente = "D";

					Operaio op = new Operaio(t1.getText(), t2.getText(), patente);

					azienda.getInterno().assumiDipendente(op);
					textArea.setText("ASSUNZIONE EFFETTUATA \n Dettagli assunzione: \n" + op.stampa());
					obox.setVisible(false);
					obox.addItem(op);
					obox.setVisible(true);
				}

				if (t3.getSelectedItem().equals("Quadro")) {

					Quadro qu = new Quadro(t1.getText(), t2.getText());

					azienda.getInterno().assumiDipendente(qu);
					textArea.setText("ASSUNZIONE EFFETTUATA \n Dettagli assunzione: \n" + qu.stampa());
					obox.setVisible(false);
					obox.addItem(qu);
					obox.setVisible(true);
				}

				if (t3.getSelectedItem().equals("Dirigente")) {

					Dirigente di = new Dirigente(t1.getText(), t2.getText());

					azienda.getInterno().assumiDipendente(di);
					textArea.setText("ASSUNZIONE EFFETTUATA \n Dettagli assunzione: \n" + di.stampa());
					obox.setVisible(false);
					obox.addItem(di);
					obox.setVisible(true);
				}

				setVisible(false);

			});

			p1.add(message);
			p2.add(i1);
			p2.add(t1);
			p3.add(i2);
			p3.add(t2);
			p4.add(i3);
			p4.add(t3);

			p5.add(i4);
			p5.add(r1);
			p5.add(r2);
			p5.add(r3);

			p6.add(i5);
			p6.add(t5);
			p7.add(ok);

			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);
			p.add(p5);
			p.add(p6);
			p.add(p7);

			add(p);

		}

	}

	private class AggiungiCommissione extends JFrame {

		public AggiungiCommissione() {
			setTitle("Richiesta nuova commissione");

			setSize(350, 340);
			setVisible(true);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
			setResizable(false);

			JPanel p = new JPanel();
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();
			JPanel p5 = new JPanel();
			JPanel p6 = new JPanel();
			JPanel p7 = new JPanel();
			/*
			 * p1.setPreferredSize(new Dimension(380, 50)); p2.setPreferredSize(new
			 * Dimension(380, 30)); p3.setPreferredSize(new Dimension(380, 30));
			 * p4.setPreferredSize(new Dimension(380, 30)); p5.setPreferredSize(new
			 * Dimension(380, 30)); p6.setPreferredSize(new Dimension(380, 30));
			 */

			JLabel nome = new JLabel("Nominativo cliente");
			JTextField tnome = new JTextField(15);
			JLabel pagamento = new JLabel("Importo commissione");
			JTextField tpagamento = new JTextField(15);
			JLabel prezzop = new JLabel("Prezzo dei permessi");
			JTextField tprezzop = new JTextField(15);
			JButton conf = new JButton("Conferma");

			conf.addActionListener((e) -> {
				Commissione c = new Commissione(tnome.getText(), Double.parseDouble(tpagamento.getText()),
						Double.parseDouble(tprezzop.getText()));
				azienda.getEsterno().riceviCommissione(c);
				obox2.setVisible(false);
				obox2.addItem(c);
				obox2.setVisible(true);
				setVisible(false);
			});

			p1.add(nome);
			p2.add(tnome);
			p3.add(pagamento);
			p4.add(tpagamento);
			p5.add(prezzop);
			p6.add(tprezzop);
			p7.add(conf);
			p.setLayout(new GridLayout(7, 1));
			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);
			p.add(p5);
			p.add(p6);
			p.add(p7);
			add(p);
		}
	}

	private class AggiungiFornitore extends JFrame {

		public AggiungiFornitore() {

			setTitle("Registra nuovo fornitore");
			setSize(860, 500);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
			setResizable(false);
			setVisible(true);

			JTextArea area = new JTextArea();
			JScrollPane bar = new JScrollPane(area);
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();
			JPanel p5 = new JPanel();
			JPanel p6 = new JPanel();
			JPanel p7 = new JPanel();
			JPanel p8 = new JPanel();
			JPanel p9 = new JPanel();
			JPanel p10 = new JPanel();
			JPanel p11 = new JPanel();

			JTextField nomeP = new JTextField(10);
			JTextField costo = new JTextField(8);
			JTextField volume = new JTextField(8);
			JTextField materiale = new JTextField(8);
			JTextField nomeF = new JTextField(10);

			JRadioButton att = new JRadioButton("Attrezzo");
			JRadioButton macc = new JRadioButton("Macchinario");

			JButton aggiungi = new JButton("Aggiungi prodotto al catalogo");
			JButton registra = new JButton("Registra fornitore");

			ButtonGroup g = new ButtonGroup();
			g.add(att);
			g.add(macc);

			String[] patenti = { "B", "C", "D" };
			JComboBox box = new JComboBox(patenti);
			ArrayList<Prodotto> catalogo = new ArrayList<Prodotto>();

			p1.setPreferredSize(new Dimension(330, 510));
			p1.setLayout(new GridLayout(10, 1));
			area.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			area.setText("CATALOGO: \n");
			area.setEditable(false);

			box.setEnabled(false);
			materiale.setEditable(false);

			class Buttons implements ActionListener {

				public void actionPerformed(ActionEvent e) {

					if (att.isSelected()) {
						materiale.setEditable(true);
						box.setEnabled(false);
					}

					if (macc.isSelected()) {
						materiale.setEditable(false);
						box.setEnabled(true);
					}
				}
			}

			att.addActionListener(new Buttons());
			macc.addActionListener(new Buttons());

			aggiungi.addActionListener((e) -> {

				if (att.isSelected()) {

					Attrezzo a = new Attrezzo(nomeP.getText(), Double.parseDouble(costo.getText()),
							Double.parseDouble(volume.getText()), materiale.getText());
					area.append(a.stampa());
					catalogo.add(a);
				}

				else if (macc.isSelected()) {

					Macchinario a = new Macchinario(nomeP.getText(), Double.parseDouble(costo.getText()),
							Double.parseDouble(volume.getText()), (String) box.getSelectedItem());
					area.append("\n" + a.stampa());
					catalogo.add(a);
				}
			});

			registra.addActionListener((e) -> {

				Fornitore f = new Fornitore(nomeF.getText());

				for (Prodotto p : catalogo)
					f.aggiungiProdotto(p);

				obox3.setVisible(false);
				obox3.addItem(f.getNome());
				azienda.getEsterno().aggiungiFornitore(f);
				obox3.setVisible(true);

				setVisible(false);
			});

			p2.add(new JLabel("Nome fornitore: "));
			p2.add(nomeF);
			p3.add(new JLabel("Inserisci prodotto al catalogo"));
			p4.add(new JLabel("Nome: "));
			p4.add(nomeP);
			p5.add(new JLabel("Costo: $"));
			p5.add(costo);
			p6.add(new JLabel("Volume: "));
			p6.add(volume);
			p7.add(new JLabel("Materiale: "));
			p7.add(materiale);
			p8.add(new JLabel("Patente richiesta: "));
			p8.add(box);
			p9.add(att);
			p9.add(macc);
			p10.add(aggiungi);
			p11.add(registra);

			p1.add(p2);
			p1.add(p3);
			p1.add(p4);
			p1.add(p5);
			p1.add(p6);
			p1.add(p9);
			p1.add(p7);
			p1.add(p8);
			p1.add(p10);
			p1.add(p11);

			add(bar);
			add(p1, BorderLayout.WEST);
		}

	}

	private class AcquistoFrame extends JFrame {

		double saldo;
		double capa;

		public AcquistoFrame() {

			saldo = 0;
			capa = azienda.getMagazzino().getCapacitaMax() - azienda.getMagazzino().getCapacitaOccupata();
			setTitle("Acquista materiali per il magazzino");
			setSize(700, 440);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
			setResizable(false);
			setVisible(true);

			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();
			JPanel p5 = new JPanel();
			JLabel sald = new JLabel("Totale: $" + saldo + "  Capacitï¿½ rimasta: " + capa);

			JTextArea area = new JTextArea("CARRELLO: \n");
			JScrollPane bar = new JScrollPane(area);
			JButton b1 = new JButton("Aggiungi al carrello");
			JButton b2 = new JButton("Completa acquisto");
			JButton b3 = new JButton("Svuota carrello");

			ArrayList<String> nomiF = new ArrayList<String>();

			ArrayList<Prodotto> carrello = new ArrayList<Prodotto>();
			for (Fornitore f : azienda.getEsterno().getListaFornitori())
				nomiF.add(f.getNome());
			area.setEditable(false);
			b1.setEnabled(false);
			JComboBox box1 = new JComboBox(nomiF.toArray());
			JComboBox box2 = new JComboBox();

			box1.addActionListener((e) -> {

				String s = (String) box1.getSelectedItem();
				b1.setEnabled(true);
				for (Fornitore f : azienda.getEsterno().getListaFornitori())
					if (f.getNome().equals(s)) {

						box2.removeAllItems();
						for (Prodotto p : f.getCatalogo())
							box2.addItem(p);
					}

				box2.setVisible(false);
				box2.setVisible(true);

			});

			b1.addActionListener((e) -> {

				Prodotto p = (Prodotto) box2.getSelectedItem();
				carrello.add(p);
				saldo += p.getPrezzo();
				capa -= p.getVolume();
				sald.setText("Totale: $" + saldo + "  Capacitï¿½ rimasta: " + capa);
				area.append(p.stampa() + "\n");

			});

			b2.addActionListener((e) -> {

				if (carrello.size() > 0)
					azienda.getEsterno().pagaProdotti(carrello);

				for (Prodotto p : carrello) {
					try {
						azienda.getMagazzino().aggiungiProdotto(p);
					} catch (CapacitaInsufficienteException e1) {

						JOptionPane.showMessageDialog(null, "Capacitï¿½ magazzino insufficiente!",
								"CapacitaInsufficienteException", JOptionPane.ERROR_MESSAGE, null);
					}
				}
				carrello.removeAll(carrello);
				saldo = 0;
				sald.setText("Totale: $" + saldo + "  Capacitï¿½ rimasta: " + capa);
				area.setText("CARRELLO: \n");
				sald.setText("Totale: $" + saldo + "  Capacitï¿½ rimasta: " + capa);
				fondi2.setText("Gestisci sezione esterna    Fondi: $" + (int) azienda.getFondiEsterno());

				cap.setText("Capcita occupata del magazzino: " + (int) azienda.getMagazzino().getCapacitaOccupata()
						+ " / " + (int) azienda.getMagazzino().getCapacitaMax() + " m^3");

			});

			b3.addActionListener((e) -> {

				for (Prodotto p : carrello)
					capa += p.getVolume();
				carrello.removeAll(carrello);
				saldo = 0;
				sald.setText("Totale: $" + saldo + "  Capacitï¿½ rimasta: " + capa);
				area.setText("CARRELLO: \n");
				sald.setText("Totale: $" + saldo + "  Capacitï¿½ rimasta: " + capa);

			});

			p1.setPreferredSize(new Dimension(700, 150));
			p1.setBorder((new TitledBorder(new EtchedBorder(), "Scegli prodotti")));
			p1.setLayout(new GridLayout(4, 1));

			p4.add(b1);
			p4.add(b2);
			p4.add(b3);

			p2.add(new JLabel("Fornitore: "));
			p2.add(box1);
			p3.add(new JLabel("Prodotti: "));
			p3.add(box2);

			p5.add(sald);

			p1.add(p2);
			p1.add(p3);
			p1.add(p5);
			p1.add(p4);

			add(bar);
			add(p1, BorderLayout.SOUTH);
		}
	}
}
