package interfacciaGrafica;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import esterni.Fornitore;
import repartoAmministrativo.AmministrativoEsterno;

public class AmministrativoEsternoGUI extends JFrame {

	private AmministrativoEsterno reparto;

	public AmministrativoEsternoGUI(AmministrativoEsterno reparto) {

		this.reparto = reparto;
		this.setName("Gestisci eparto Amministrativo esterno");
		this.setSize(980, 600);
		this.setResizable(false);

		this.add(new SelectionPanel(), BorderLayout.WEST);

		this.setVisible(true);

	}

	public class SelectionPanel extends JPanel {

		JLabel scegli;
		JTextArea area;
		JComboBox selezioneFornitori;
		JButton seleziona = new JButton("Seleziona");

		public SelectionPanel() {

			ArrayList<String> nomi = new ArrayList<String>();
			for (Fornitore f : reparto.getListaFornitori())
				nomi.add(f.getNome());
			area = new JTextArea("Prodotti: \n");
			selezioneFornitori = new JComboBox(nomi.toArray());
			scegli = new JLabel("Scegli il fornitore");

			seleziona.addActionListener((selezione) -> {
				
				Fornitore tmp;
				for(Fornitore f : reparto.getListaFornitori())
					if(f.getNome().equals(selezioneFornitori.getSelectedItem()))
						tmp = f;
			});
			
			this.add(scegli);
			this.add(selezioneFornitori);
			this.add(seleziona);
			this.add(area);

		}
	}
	
	public class AreadiTesto extends JPanel {
		
		public AreadiTesto(Fornitore f) {
			
		}
	}

}
