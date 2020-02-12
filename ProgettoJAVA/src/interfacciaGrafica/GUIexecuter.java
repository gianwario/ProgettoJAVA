package interfacciaGrafica;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GUIexecuter {

	public static void main(String[] args) {
		InitGUI i = new InitGUI();
		i.setTitle("Azienda");
		i.setDefaultCloseOperation(i.EXIT_ON_CLOSE);
		i.setSize(580,280);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		i.setLocation(dim.width/2-i.getSize().width/2, dim.height/2-i.getSize().height/2);
		i.setVisible(true);
		
	}

}
