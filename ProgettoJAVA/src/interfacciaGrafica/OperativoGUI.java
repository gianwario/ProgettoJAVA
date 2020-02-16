package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import azienda.Azienda;
import eccezioni.AperturaCantiereInvalidaException;
import esterni.Commissione;
import personale.Dipendente;
import personale.Operaio;
import personale.Quadro;
import personale.Responsabile;
import repartoOperativo.Cantiere;
import repartoOperativo.Squadra;
import risorse.Prodotto;
import utils.Selezionabile;
import utils.Selezionatore;

public class OperativoGUI extends JFrame {

	private Azienda azienda;

	private JTextArea tarea;

	public OperativoGUI(Azienda azienda) {
		this.azienda = azienda;

		setTitle("Gestisci Reparto Amministrativo");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) dim.getWidth(), (int) dim.getHeight() - 100);
		setResizable(false);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);

		JPanel bigp = new JPanel();
		bigp.add(leftPanel(), BorderLayout.WEST);
		bigp.add(reportPanel(), BorderLayout.CENTER);
		bigp.add(rightPanel(), BorderLayout.EAST);

		add(bigp);
	}

	public JPanel leftPanel() {
		JPanel pleft = new JPanel();
		pleft.setBorder((new TitledBorder(new EtchedBorder(), "Gestione singolo cantiere")));
		pleft.setPreferredSize(new Dimension(300, 600));
		pleft.setLayout(new GridLayout(8, 1));

		// BOTTONI
		JButton com = new JButton("Visualizza commissione");
		JButton sq = new JButton("Visualizza squadre");
		JButton mat = new JButton("Visualizza materili");
		JButton cancella = new JButton("Pulisci report");
		JButton newsq = new JButton("Aggiungi squadra");
		JButton chiudi = new JButton("Chiudi cantiere");

		// GENERAZIONE E AGGIUNTA LISTA
		ArrayList<String> al = new ArrayList<String>();
		for (Cantiere c : azienda.getOperativo().getCantieri()) {
			al.add("Cantiere per : " + c.getCommissione().getNominativoCliente());
		}
		JComboBox list = new JComboBox(al.toArray());
		JPanel tmplist = new JPanel();
		list.addActionListener((e) -> {
			tarea.setText("Cantiere commissionato da  "
					+ azienda.getOperativo().getCantiere(list.getSelectedIndex()).getCommissione()
							.getNominativoCliente()
					+ " con valore di $" + azienda.getOperativo().getCantiere(list.getSelectedIndex()).getValore());
			com.setEnabled(true);
			sq.setEnabled(true);
			mat.setEnabled(true);
			chiudi.setEnabled(true);
			newsq.setEnabled(true);
		});
		tmplist.add(list);
		pleft.add(tmplist);

		// BOTTONE COMMISSIONE
		JPanel tmpcom = new JPanel();
		com.setEnabled(false);
		tmpcom.add(com);
		com.addActionListener((e) -> {
			tarea.append("\n" + azienda.getOperativo().getCantiere(list.getSelectedIndex()).stampaCommissione());
		});

		// BOTTONE SQUADRE
		JPanel tmpsq = new JPanel();
		sq.setEnabled(false);
		tmpsq.add(sq);
		sq.addActionListener((e) -> {
			tarea.append("\n" + azienda.getOperativo().getCantiere(list.getSelectedIndex()).stampaSquadre());

		});

		// BOTTONE MATERIALI
		JPanel tmpmat = new JPanel();
		mat.setEnabled(false);
		tmpmat.add(mat);
		mat.addActionListener((e) -> {
			tarea.append("\n" + azienda.getOperativo().getCantiere(list.getSelectedIndex()).stampaMateriali());

		});

		// BOTTONE PULISCI REPORt
		JPanel can = new JPanel();
		can.add(cancella);
		cancella.addActionListener((e) -> {
			tarea.setText("");
			com.setEnabled(false);
			sq.setEnabled(false);
			mat.setEnabled(false);
			chiudi.setEnabled(false);
			newsq.setEnabled(false);
		});

		// BOTTONE AGGIUNGI SQUADRA
		JPanel tmpnsq = new JPanel();
		newsq.setEnabled(false);
		tmpnsq.add(newsq);
		newsq.addActionListener((e) -> {
			new NuovaSquadraGUI(azienda.getOperativo().getCantiere(list.getSelectedIndex()));
		});

		// BOTTONE CHIUDI CANTIERE
		JPanel tmpclose = new JPanel();
		chiudi.setEnabled(false);
		tmpclose.add(chiudi);
		chiudi.addActionListener((e) -> {
			azienda.getOperativo().chiudiCantiere(azienda.getOperativo().getCantiere(list.getSelectedIndex()));
			list.removeItemAt(list.getSelectedIndex());
			tarea.setText("Cantiere eliminato ");
			com.setEnabled(false);
			sq.setEnabled(false);
			mat.setEnabled(false);
			chiudi.setEnabled(false);
			newsq.setEnabled(false);
		});

		pleft.add(tmpcom);
		pleft.add(tmpsq);
		pleft.add(tmpmat);
		pleft.add(tmpnsq);
		pleft.add(tmpclose);
		pleft.add(can);

		return pleft;
	}

	public JPanel rightPanel() {
		JPanel pright = new JPanel();
		pright.setBorder((new TitledBorder(new EtchedBorder(), "Gestione intero reparto")));
		pright.setPreferredSize(new Dimension(300, 600));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		// BOTTONE CREA
		JButton crea = new JButton("Nuovo cantiere");
		crea.addActionListener((e) -> {
			new NuovoCantiereGUI();
		});
		p1.add(crea);

		pright.add(p1);
		return pright;
	}

	public JPanel reportPanel() {
		JPanel preport = new JPanel();
		preport.setPreferredSize(new Dimension(600, 600));
		tarea = new JTextArea(35, 50);
		JScrollPane areapane = new JScrollPane(tarea);
		preport.add(areapane, BorderLayout.CENTER);

		preport.setBorder((new TitledBorder(new EtchedBorder(), "Report")));
		return preport;

	}

	private class NuovaSquadraGUI extends JFrame {
		Cantiere c;
		Quadro capo;
		ArrayList<Operaio> membri = new ArrayList<Operaio>();

		public NuovaSquadraGUI(Cantiere c) {
			this.c = c;
			setVisible(true);
			setSize(750, 400);
			setTitle("Gestisci Reparto Amministrativo");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setResizable(false);
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

			JPanel p = new JPanel();
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();
			JPanel p5 = new JPanel();
			JPanel p6 = new JPanel();
			JPanel p7 = new JPanel();

			JLabel lab = new JLabel("Aggiungi squadra al cantiere");
			p1.add(lab);

			JLabel lab1 = new JLabel("Scegli il caposquadra");
			p4.add(lab1);

			Selezionabile<Dipendente> s = (q) -> q.getClass().getSimpleName().equals("Quadro")
					&& !q.controllaStatoDipendente();
			Selezionatore s1 = new Selezionatore(azienda.getInterno().listaDipendenti(), s);
			ArrayList<Quadro> quadri = s1.seleziona();
			JComboBox comb = new JComboBox(quadri.toArray());
			p2.add(comb);

			JLabel lab2 = new JLabel("Scegli gli operai da inserire in squadra");
			p5.add(lab2);
			s = (o) -> o.getClass().getSimpleName().equals("Operaio") && !o.controllaStatoDipendente();
			s1 = new Selezionatore(azienda.getInterno().listaDipendenti(), s);
			ArrayList<Operaio> operai = s1.seleziona();
			JComboBox op = new JComboBox(operai.toArray());
			p3.add(op);

			comb.addActionListener((e) -> {
				capo = (Quadro) comb.getSelectedItem();
			});

			JButton add = new JButton("Aggiungi Operaio");
			add.addActionListener((e) -> {
				membri.add((Operaio) op.getSelectedItem());
				op.removeItemAt(op.getSelectedIndex());
			});
			p6.add(add);

			JButton fine = new JButton("Aggiungi squadra al cantiere");
			fine.addActionListener((e) -> {
				Squadra sq = new Squadra(capo, membri);
				c.aggiungiSquadra(sq);
				this.setVisible(false);
			});
			p7.add(fine);

			p.add(p1);
			p.add(p4);
			p.add(p2);
			p.add(p5);
			p.add(p3);
			p.add(p6);
			p.add(p7);
			add(p);

		}
	}

	private class NuovoCantiereGUI extends JFrame {

		ArrayList<Prodotto> out;
		Commissione commissione;
		Responsabile responsabile;
		int valore;
		boolean dirigente;

		public NuovoCantiereGUI() {
			out = new ArrayList<Prodotto>();
			dirigente = false;

			setVisible(true);
			setSize(1200, 600);
			setTitle("Gestisci Reparto Amministrativo");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setResizable(false);
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
			JPanel p1 = new JPanel();
			p1.setBorder((new TitledBorder(new EtchedBorder(), "Creazione cantiere")));
			p1.setPreferredSize(new Dimension(580, 550));
			JPanel p2 = new JPanel();
			p2.setBorder((new TitledBorder(new EtchedBorder(), "Scelta materiali dal magazzino")));
			p2.setPreferredSize(new Dimension(580, 550));

			// PANNELLO DI DESTRA - MATERIALI
			JTextArea rp = new JTextArea(28, 45);
			JScrollPane sp = new JScrollPane(rp);
			JComboBox cb = new JComboBox(azienda.getMagazzino().getListaProdotti().toArray());
			JButton add = new JButton("Aggiungi");
			add.addActionListener((e) -> {
				rp.append((Prodotto) cb.getSelectedItem() + "\n");
				out.add((Prodotto) cb.getSelectedItem());
				cb.removeItem(cb.getSelectedItem());
			});

			// PANNELLO DI SINISTRA - INSERIMENTO

			// SCELTA COMMISSIONE
			ArrayList<Commissione> lc = azienda.getEsterno().getListaCommissioni();
			ArrayList<Commissione> ac = new ArrayList<Commissione>();
			for(Cantiere c : azienda.getOperativo().getCantieri()) {
				ac.add(c.getCommissione());
			}
			lc.removeAll(ac);
			Selezionabile<Commissione> sc = (c) -> c.getOttenimentoPermessi() && !c.getCompletamento();
			Selezionatore sc1 = new Selezionatore(lc, sc);
			ArrayList<Commissione> resc = sc1.seleziona();
			ArrayList<String> st = new ArrayList<String>();
			for (Commissione c : resc) {
					st.add("Cliente : " + c.getNominativoCliente() + ", prezzo : " + c.getPagamento() + ", permessi : "
						+ c.getOttenimentoPermessi());
			}
			JComboBox cm = new JComboBox(st.toArray());
			cm.addActionListener((e) -> {
				commissione = resc.get(cm.getSelectedIndex());
			});
			JPanel pan2 = new JPanel();
			pan2.setPreferredSize(new Dimension(560, 60));
			pan2.add(cm);

			// INSERIMENTO VALORE
			JLabel l = new JLabel("Inserisci valore cantiere");
			JTextField jt = new JTextField(20);
			JPanel pan3 = new JPanel();
			pan3.setPreferredSize(new Dimension(560, 60));
			pan3.add(l);
			pan3.add(jt);
			JPanel pan1 = new JPanel();
			
			// CREA
			JButton crea = new JButton("Crea cantiere");
			crea.addActionListener((e) -> {
				try {
					azienda.getOperativo().apriCantiere(valore, responsabile, out, commissione);
				} catch (AperturaCantiereInvalidaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			});
			
			// CONFERMA
			JButton jb = new JButton("Conferma");
			JPanel pan4 = new JPanel();
			JPanel pan5 = new JPanel();
			pan4.setPreferredSize(new Dimension(560, 60));
			jb.addActionListener((e) -> {
				valore = Integer.parseInt(jt.getText());
				if (valore >= 500000) {
					dirigente = true;
				}

				jb.setVisible(false);
				// SCELTA RESPONSABILE
				Selezionabile<Dipendente> s = (q) -> (dirigente ? q.getClass().getSimpleName().equals("Dirigente")
						: (q.getClass().getSimpleName().equals("Dirigente")
								|| q.getClass().getSimpleName().equals("Quadro")))
						&& !((Dipendente) q).controllaStatoDipendente();
				Selezionatore s1 = new Selezionatore(azienda.getInterno().listaDipendenti(), s);
				ArrayList<Dipendente> res = s1.seleziona();
				JComboBox rs = new JComboBox(res.toArray());
				rs.setPreferredSize(new Dimension(560, 60));

				rs.addActionListener((e1) -> {
					responsabile = (Responsabile) rs.getSelectedItem();
				});
				pan1.add(rs);
				pan5.add(crea);
				
			});
			pan4.add(jb);



			p1.add(pan2);
			p1.add(pan3);
			p1.add(pan4);
			p1.add(pan1);
			p1.add(pan5);
			p2.add(sp);
			p2.add(cb);
			p2.add(add);
			JPanel bp = new JPanel();
			bp.add(p1);
			bp.add(p2);
			add(bp);
		}
	}

}
