package si.alkaboom.frontend;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ZailtasunaPanela extends JPanel {
	private static final long serialVersionUID = -1087859968284089074L;
	private JLabel laukiErrenkada, laukiZutabe;
	private JTextField laukiErrenkadaField, laukiZutabeField;

	public ZailtasunaPanela() {
		this.setLayout(new SpringLayout());
	}
}
