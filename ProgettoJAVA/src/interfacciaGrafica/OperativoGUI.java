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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import azienda.Azienda;
import repartoOperativo.Cantiere;

public class OperativoGUI extends JFrame{
	
	private Azienda azienda;
	
	private JTextArea tarea;

	
	
	public OperativoGUI(Azienda azienda) {
		this.azienda = azienda;
		

		setTitle("Gestisci Reparto Amministrativo");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)dim.getWidth(),(int)dim.getHeight()-100);
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
		pleft.setBorder((new TitledBorder(new EtchedBorder(), "Visualizza singolo")));
		pleft.setPreferredSize(new Dimension(300,600));
		pleft.setLayout(new GridLayout(8,1));
		
		//BOTTONI
		JButton com = new JButton("Visualizza commissione");
		
		//GENERAZIONE E AGGIUNTA LISTA
		ArrayList<String> al = new ArrayList<String>();
		for(Cantiere c : azienda.getOperativo().getCantieri()) {
			al.add("Cantiere per : "+c.getCommissione().getNominativoCliente());
		}
		JComboBox list = new JComboBox(al.toArray());
		JPanel tmplist = new JPanel();
		list.addActionListener((e) -> {
			tarea.setText("Cantiere commissionato da  " + azienda.getOperativo().getCantiere(list.getSelectedIndex()).getCommissione().getNominativoCliente() 
							+ " con valore di $" +azienda.getOperativo().getCantiere(list.getSelectedIndex()).getValore());
			com.setEnabled(true);
		});
		tmplist.add(list);
		pleft.add(tmplist);
		
		
		//BOTTONE COMMISSIONE
		JPanel tmpcom = new JPanel();
		com.setEnabled(false);
		tmpcom.add(com);
		
		com.addActionListener((e) ->{
			tarea.append("\n"+azienda.getOperativo().getCantiere(list.getSelectedIndex()).getCommissione().stampa());
		});
		
		pleft.add(tmpcom);

		
		
		return pleft;
	}
	
	public JPanel rightPanel() {
		JPanel pright= new JPanel();
		pright.setBorder((new TitledBorder(new EtchedBorder(), "Visualizza tutti")));
		pright.setPreferredSize(new Dimension(300,600));

		
		return pright;
	}
	
	public JPanel reportPanel() {
		JPanel preport = new JPanel();
		preport.setPreferredSize(new Dimension(600,600));
		tarea = new JTextArea(35,50);
		JScrollPane areapane = new JScrollPane(tarea);
		preport.add(areapane, BorderLayout.CENTER);
		
		
		
		preport.setBorder((new TitledBorder(new EtchedBorder(), "Report")));
		return preport;
		
	}

}
