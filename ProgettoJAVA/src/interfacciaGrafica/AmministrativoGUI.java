package interfacciaGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import azienda.Azienda;

public class AmministrativoGUI extends JFrame {

	Azienda azienda;

	public AmministrativoGUI(Azienda azienda) {

		this.azienda = azienda;

		setTitle("Gestisci Reparto Amministrativo");
		setSize(1520, 790);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
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

		p.add(sp2, BorderLayout.CENTER);
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
		JPanel sp2 = new JPanel(); // pannello scelta del report
		JPanel tmp = new JPanel();
		
		
		JTextArea textArea = new JTextArea(30, 50);

		sp1.setLayout(new GridLayout(2, 1));
		p.setBorder((new TitledBorder(new EtchedBorder(), "Report")));
		p.setBackground(Color.CYAN);
		p.setPreferredSize(new Dimension(770, 530));
		tmp.add(textArea);
		tmp.setBackground(Color.red);
		
		
		sp1.add(tmp, BorderLayout.WEST);
		

		p.add(sp1, BorderLayout.WEST);
		p.add(criteriPanel(), BorderLayout.CENTER);

		return p;
	}

	private JPanel criteriPanel() {
		
		String[] ordine = {"Alfabetico", "Stipendio", "N volte di ano trapanato"};

		JPanel p = new JPanel();
		JPanel jp0 = new JPanel();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JButton b1 = new JButton("Genera report");
		
		JComboBox cbox = new JComboBox(ordine);
		
		JCheckBox cb1 = new JCheckBox("Impiegato");
		JCheckBox cb2 = new JCheckBox("Operaio");
		JCheckBox cb3 = new JCheckBox("Quadro");
		JCheckBox cb4 = new JCheckBox("Dirigente");
		
		JRadioButton rb1 = new JRadioButton("Pagato");
		JRadioButton rb2 = new JRadioButton("Non pagato");
		JRadioButton rb3 = new JRadioButton("Impegnato");
		JRadioButton rb4 = new JRadioButton("Non impegnato");
		JRadioButton rb5 = new JRadioButton("Crescente");
		JRadioButton rb6 = new JRadioButton("Decrescente");
		
		ButtonGroup g1 = new ButtonGroup();
		ButtonGroup g2 = new ButtonGroup();
		ButtonGroup g3 = new ButtonGroup();
		
		
		g1.add(rb1);
		g1.add(rb2);
		g2.add(rb3);
		g2.add(rb4);
		g3.add(rb5);
		g3.add(rb6);

		p.setPreferredSize(new Dimension(220, 920));
		p.setBackground(Color.yellow);
		
		jp0.setBorder((new TitledBorder(new EtchedBorder(), "Ordina per")));
		jp1.setBorder((new TitledBorder(new EtchedBorder(), "Tipo")));
		jp2.setBorder((new TitledBorder(new EtchedBorder(), "Stato")));
		jp3.setBorder((new TitledBorder(new EtchedBorder(), "Ordine")));
		
		jp0.setPreferredSize(new Dimension(200, 60));
		jp1.setPreferredSize(new Dimension(220, 120));
		jp2.setPreferredSize(new Dimension(220, 120));
		jp3.setPreferredSize(new Dimension(220, 80));
		
		jp0.add(cbox);
		jp1.setLayout(new GridLayout(4, 1));
		jp1.add(cb1);
		jp1.add(cb2);
		jp1.add(cb3);
		jp1.add(cb4);
		
		//jp2.setLayout(new GridLayout(2, 2));
		jp2.add(rb1);
		jp2.add(rb2);
		jp2.add(rb3);
		jp2.add(rb4);
		
		jp3.add(rb5);
		jp3.add(rb6);
		
		p.add(jp0);
		p.add(jp1);
		p.add(jp2);
		p.add(jp3);
		p.add(b1);

		return p;
	}

}
