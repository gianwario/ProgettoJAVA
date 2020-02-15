package interfacciaGrafica;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import azienda.Azienda;

public class OperativoGUI extends JFrame{
	
	Azienda azienda;
	
	public OperativoGUI(Azienda azienda) {
		this.azienda = azienda;

		setTitle("Gestisci Reparto Amministrativo");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)dim.getWidth(),(int)dim.getHeight()-100);
		setResizable(false);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
	}
	
	

}
