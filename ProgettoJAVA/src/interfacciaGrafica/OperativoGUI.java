package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import risorse.Macchinario;
import risorse.Prodotto;
import utils.Selezionabile;
import utils.Selezionatore;

public class OperativoGUI extends JFrame {

	private JRadioButton rb1;
	private JPanel val;
	private JTextField jt1;
	private JTextField jt2;

	private JRadioButton rb2;
	private JPanel mat;
	private JTextField jt3;

	private JRadioButton rb3;
	private JPanel squa;
	private JTextField jt4;

	private JRadioButton rb4;
	private JPanel resp;
	private JTextField jt5;

	private JRadioButton rb5;
	private JPanel com;
	private JTextField jt6;
	private JTextField jt7;

	private Azienda azienda;

	private JTextArea tarea;

	public OperativoGUI(Azienda azienda) {
		this.azienda = azienda;

		setTitle("Gestione Reparto Operativo");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1150, 600);
		setResizable(false);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);

		JPanel bigp = new JPanel();
		bigp.add(leftPanel(), BorderLayout.WEST);
		bigp.add(reportPanel(), BorderLayout.CENTER);
		bigp.add(rightPanel(), BorderLayout.EAST);

		add(bigp);
	}

	private JPanel leftPanel() {
		JPanel pleft = new JPanel();
		pleft.setBorder((new TitledBorder(new EtchedBorder(), "Gestione singolo cantiere")));
		pleft.setPreferredSize(new Dimension(250, 550));
		pleft.setLayout(new GridLayout(9, 1));

		// BOTTONI
		JButton com = new JButton("Visualizza commissione");
		JButton sq = new JButton("Visualizza squadre");
		JButton mat = new JButton("Visualizza materili");
		JButton cancella = new JButton("Pulisci report");
		JButton newsq = new JButton("Aggiungi squadra");
		JButton macc = new JButton("Gestisci macchinari");
		JButton chiudi = new JButton("Chiudi cantiere");
		com.setPreferredSize(new Dimension(205, 30));
		sq.setPreferredSize(new Dimension(205, 30));
		mat.setPreferredSize(new Dimension(205, 30));
		macc.setPreferredSize(new Dimension(205, 30));
		cancella.setPreferredSize(new Dimension(205, 30));
		newsq.setPreferredSize(new Dimension(205, 30));
		chiudi.setPreferredSize(new Dimension(205, 30));

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
					+ " con valore di $" + azienda.getOperativo().getCantiere(list.getSelectedIndex()).getValore()+"\n"
					+"Responsabile : "+azienda.getOperativo().getCantiere(list.getSelectedIndex()).getResponsabile().getCognome()+" "
					+azienda.getOperativo().getCantiere(list.getSelectedIndex()).getResponsabile().getCognome());
			com.setEnabled(true);
			sq.setEnabled(true);
			mat.setEnabled(true);
			chiudi.setEnabled(true);
			newsq.setEnabled(true);
			macc.setEnabled(true);
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
			macc.setEnabled(false);
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

		// BOTTONE GESTIONE MACCHINARI
		JPanel tmpmac = new JPanel();
		macc.setEnabled(false);
		macc.addActionListener((e) -> {
			new GestisciMacchinariGUI(azienda.getOperativo().getCantiere(list.getSelectedIndex()));
		});
		tmpmac.add(macc);

		pleft.add(new JPanel());
		pleft.add(tmpcom);
		pleft.add(tmpsq);
		pleft.add(tmpmat);
		pleft.add(tmpnsq);
		pleft.add(tmpmac);
		pleft.add(tmpclose);
		pleft.add(can);

		return pleft;
	}

	private JPanel rightPanel() {
		JPanel pright = new JPanel();
		pright.setBorder((new TitledBorder(new EtchedBorder(), "Gestione intero reparto")));
		pright.setPreferredSize(new Dimension(250, 550));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();

		// BOTTONE CREA
		JButton crea = new JButton("Nuovo cantiere");
		crea.setPreferredSize(new Dimension(200, 30));
		crea.addActionListener((e) -> {
			new NuovoCantiereGUI();
		});
		p1.add(crea);

		JPanel vc = new JPanel();
		vc.setBorder((new TitledBorder(new EtchedBorder(), "Cerca per")));
		vc.setLayout(new GridLayout(5, 1));
		vc.setPreferredSize(new Dimension(200, 150));
		rb1 = new JRadioButton("Valore");
		rb2 = new JRadioButton("Materiali");
		rb3 = new JRadioButton("Squadre");
		rb4 = new JRadioButton("Responsabile");
		rb5 = new JRadioButton("Commissione");

		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);
		bg.add(rb5);
		vc.add(rb1);
		vc.add(rb2);
		vc.add(rb3);
		vc.add(rb4);
		vc.add(rb5);
		rb1.setSelected(true);

		JPanel criteri = new JPanel();
		criteri.setLayout(new GridLayout(5, 1));
		criteri.setPreferredSize(new Dimension(200, 260));
		criteri.setBorder((new TitledBorder(new EtchedBorder(), "Criteri")));
		val = new JPanel();
		mat = new JPanel();
		squa = new JPanel();
		resp = new JPanel();
		com = new JPanel();

		// VALORE
		val.setLayout(new GridLayout(1, 2));
		val.setBorder((new TitledBorder(new EtchedBorder(), "Range valore cantiere")));
		jt1 = new JTextField(10);
		jt2 = new JTextField(10);
		val.add(jt1);
		val.add(jt2);

		// MATERIALI
		mat.setLayout(new GridLayout(1, 1));
		mat.setBorder((new TitledBorder(new EtchedBorder(), "Cantiere che usa il prodotto")));
		jt3 = new JTextField(10);
		mat.add(jt3);

		// SQUADRE
		squa.setLayout(new GridLayout(1, 1));
		squa.setBorder((new TitledBorder(new EtchedBorder(), "Nome dipendente del cantiere")));
		jt4 = new JTextField(10);
		squa.add(jt4);

		// RESPONSABILE
		resp.setLayout(new GridLayout(1, 1));
		resp.setBorder((new TitledBorder(new EtchedBorder(), "Nome responsabile")));
		jt5 = new JTextField(10);
		resp.add(jt5);

		// COMMISSIONE
		com.setLayout(new GridLayout(1, 2));
		com.setBorder((new TitledBorder(new EtchedBorder(), "Range importo commissione")));
		jt6 = new JTextField(10);
		jt7 = new JTextField(10);
		com.add(jt6);
		com.add(jt7);

		JButton conf = new JButton("Genera report");
		conf.setPreferredSize(new Dimension(200, 30));
		conf.addActionListener((e) -> {
			Selezionabile<Cantiere> s = getCriterio();
			Selezionatore s1 = new Selezionatore(azienda.getOperativo().getCantieri(), s);
			ArrayList<Cantiere> selected = s1.seleziona();
			tarea.setText("Cantieri selezionati :  " + selected.size() + " \n\n\n");
			int i = 1;
			for (Cantiere c : selected) {
				tarea.append("Cantiere " + i + "\n");
				tarea.append("Valore : " + c.getValore() + "\n");
				tarea.append("Nome responsabile : " + c.getResponsabile().getNome() + " "
						+ c.getResponsabile().getCognome() + "\n");
				tarea.append("Commissione : \n" + c.stampaCommissione());
				tarea.append("Squadre : \n" + c.stampaSquadre());
				tarea.append("Materiali : \n" + c.stampaMateriali() + "\n\n\n");
				i++;
			}
		});

		rb1.addActionListener(new CriterioGUIListener());
		rb2.addActionListener(new CriterioGUIListener());
		rb3.addActionListener(new CriterioGUIListener());
		rb4.addActionListener(new CriterioGUIListener());
		rb5.addActionListener(new CriterioGUIListener());

		criteri.add(val);
		criteri.add(mat);
		criteri.add(squa);
		criteri.add(resp);
		criteri.add(com);

		pright.add(p1);
		pright.add(vc);
		pright.add(criteri);
		pright.add(conf);
		return pright;
	}

	private Selezionabile<Cantiere> getCriterio() {

		Selezionabile<Cantiere> s = null;
		if (rb1.isSelected()) {
			s = (c) -> c.getValore() >= Integer.parseInt(jt1.getText())
					&& c.getValore() <= Integer.parseInt(jt2.getText());
		} else if (rb2.isSelected()) {

			s = (c) -> {
				for (Prodotto pr : c.getMateriali())
					if (pr.getNome().equalsIgnoreCase(jt3.getText()))
						return true;
				return false;
			};
		} else if (rb3.isSelected()) {
			s = (c) -> {
				for (Squadra sq : c.getSquadre()) {
					if ((sq.getCaposquadra().getCognome() + " " + sq.getCaposquadra().getNome())
							.equalsIgnoreCase(jt4.getText())
							|| (sq.getCaposquadra().getNome() + " " + sq.getCaposquadra().getCognome())
									.equalsIgnoreCase(jt4.getText()))
						return true;
					else
						for (Operaio o : sq.getOperai()) {
							if ((o.getCognome() + " " + o.getNome()).equalsIgnoreCase(jt4.getText())
									|| (o.getNome() + " " + o.getCognome()).equalsIgnoreCase(jt4.getText()))
								return true;
						}
				}
				return false;
			};

		} else if (rb4.isSelected()) {
			s = (c) -> (c.getResponsabile().getNome() + " " + c.getResponsabile().getCognome())
					.equalsIgnoreCase(jt5.getText())
					|| (c.getResponsabile().getCognome() + " " + c.getResponsabile().getNome())
							.equalsIgnoreCase(jt5.getText());
		} else if (rb5.isSelected()) {

			s = (c) -> c.getCommissione().getPagamento()>=Integer.parseInt(jt6.getText()) &&
					c.getCommissione().getPagamento()<=Integer.parseInt(jt7.getText());
		}

		return s;

	}

	private class CriterioGUIListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (rb1.isSelected()) {
				val.setEnabled(true);
				mat.setEnabled(false);
				squa.setEnabled(false);
				resp.setEnabled(false);
				com.setEnabled(false);
				jt1.setEnabled(true);
				jt2.setEnabled(true);
				jt3.setEnabled(false);
				jt4.setEnabled(false);
				jt5.setEnabled(false);
				jt6.setEnabled(false);
				jt7.setEnabled(false);
			} else if (rb2.isSelected()) {
				mat.setEnabled(true);
				val.setEnabled(false);
				squa.setEnabled(false);
				resp.setEnabled(false);
				com.setEnabled(false);
				jt1.setEnabled(false);
				jt2.setEnabled(false);
				jt3.setEnabled(true);
				jt4.setEnabled(false);
				jt5.setEnabled(false);
				jt6.setEnabled(false);
				jt7.setEnabled(false);
			} else if (rb3.isSelected()) {
				squa.setEnabled(true);
				val.setEnabled(false);
				mat.setEnabled(false);
				resp.setEnabled(false);
				com.setEnabled(false);
				jt1.setEnabled(false);
				jt2.setEnabled(false);
				jt3.setEnabled(false);
				jt4.setEnabled(true);
				jt5.setEnabled(false);
				jt6.setEnabled(false);
				jt7.setEnabled(false);
			} else if (rb4.isSelected()) {
				resp.setEnabled(true);
				val.setEnabled(false);
				mat.setEnabled(false);
				squa.setEnabled(false);
				com.setEnabled(false);
				jt1.setEnabled(false);
				jt2.setEnabled(false);
				jt3.setEnabled(false);
				jt4.setEnabled(false);
				jt5.setEnabled(true);
				jt6.setEnabled(false);
				jt7.setEnabled(false);
			} else if (rb5.isSelected()) {
				com.setEnabled(true);
				val.setEnabled(false);
				mat.setEnabled(false);
				squa.setEnabled(false);
				resp.setEnabled(false);
				jt1.setEnabled(false);
				jt2.setEnabled(false);
				jt3.setEnabled(false);
				jt4.setEnabled(false);
				jt5.setEnabled(false);
				jt6.setEnabled(true);
				jt7.setEnabled(true);
			}

		}
	}

	private JPanel reportPanel() {
		JPanel preport = new JPanel();
		preport.setPreferredSize(new Dimension(600, 550));
		tarea = new JTextArea(30, 50);
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
			for (Cantiere c : azienda.getOperativo().getCantieri()) {
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

	private class GestisciMacchinariGUI extends JFrame {

		Cantiere c;

		public GestisciMacchinariGUI(Cantiere c) {
			this.c = c;
			setVisible(true);
			setSize(750, 400);
			setTitle("Gestisci Reparto Amministrativo");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setResizable(false);
			setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

			JPanel bigpanel = new JPanel();

			Selezionabile<Prodotto> s = (m) -> m.getClass().getSimpleName().equals("Macchinario");
			Selezionatore s1 = new Selezionatore(c.getMateriali(), s);
			ArrayList<Prodotto> list = s1.seleziona();

			JLabel lb = new JLabel("Seleziona il macchinario");
			JComboBox combo = new JComboBox(list.toArray());
			JComboBox op = new JComboBox();
			JPanel bp = new JPanel();
			JButton but = new JButton("Conferma");
			bp.add(but);
			but.setEnabled(false);

			combo.addActionListener((e) -> {
				op.setVisible(true);
				op.setEnabled(true);
				op.removeAllItems();
				ArrayList<Operaio> lo = new ArrayList<Operaio>();
				for (Squadra sq : c.getSquadre()) {
					lo.addAll(sq.getOperai());
				}

				Macchinario m = (Macchinario) combo.getSelectedItem();

				Selezionabile<Operaio> sel = (o) -> o.getPatente().equals(m.getTipoPatente()) && !o.isConducente();
				Selezionatore sel1 = new Selezionatore(lo, sel);

				ArrayList<Operaio> lop = sel1.seleziona();
				if (lop.size() != 0)
					for (Operaio oper : lop) {
						op.addItem(oper);
					}
				else {
					op.addItem("Nessun operaio disponibile");
					op.setEnabled(false);
				}
				but.setEnabled(true);
			});

			but.addActionListener((e) -> {
				c.assegnaConducente((Macchinario) combo.getSelectedItem(), (Operaio) op.getSelectedItem());
				setVisible(false);
			});

			bigpanel.setLayout(new GridLayout(4, 1));
			JPanel lbp = new JPanel();
			lbp.add(lb);
			bigpanel.add(lbp);
			JPanel combop = new JPanel();
			combop.add(combo);
			bigpanel.add(combop);
			JPanel opp = new JPanel();
			opp.add(op);
			bigpanel.add(opp);
			op.setVisible(false);
			bigpanel.add(bp);
			add(bigpanel, BorderLayout.CENTER);

		}
	}
}
