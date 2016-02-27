package si.alkaboom.frontend;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import si.alkaboom.backend.Nagusia;
import si.alkaboom.externals.SpringUtilities;

public class Login extends JFrame {
	private static final long serialVersionUID = -7323271088278163192L;
	private JLabel erabiltzailea, zailtasuna;
	private JTextField erabiltzaileaField;
	private JButton sartu, aukerazkoa;
	private JComboBox<String> aukerak;
	private String[] aukerakArray;
	private String erabiltzaileaString;

	public Login() {
		this.setTitle(Nagusia.IZENBURUA);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new SpringLayout());
		this.erabiltzailea = new JLabel("Erabiltzailea:", SwingConstants.TRAILING);
		this.erabiltzaileaField = new JTextField();
		this.erabiltzailea.setLabelFor(erabiltzaileaField);
		this.add(erabiltzailea);
		this.add(erabiltzaileaField);
		this.zailtasuna = new JLabel("Zailtasuna:", SwingConstants.TRAILING);
		aukerakArray = new String[] { "Erreza", "Normala", "Zaila", "Custom..." };
		aukerak = new JComboBox<>(aukerakArray);
		aukerak.addItemListener(gureIL -> this.botoiaAldatu());
		this.zailtasuna.setLabelFor(aukerak);
		this.add(zailtasuna);
		this.add(aukerak);
		this.sartu = new JButton("Sartu");
		this.sartu.addActionListener(gureAE -> this.datuakGorde());
		this.add(this.sartu);
		this.aukerazkoa = new JButton("Zailtasuna Aukeratu");
		this.aukerazkoa.setEnabled(false);
		this.aukerazkoa.addActionListener(gureAE -> this.aukerazkoZailtasunaHautatu());
		this.add(aukerazkoa);
		SpringUtilities.makeCompactGrid(this.getContentPane(), 3, 2, 1, 1, 3, 3);
		this.setMinimumSize(new Dimension(300, 60));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void datuakGorde() {
		this.erabiltzaileaString = this.erabiltzaileaField.getText();
	}

	private void botoiaAldatu() {
		if (aukerak.getSelectedIndex() == 3)
			aukerazkoa.setEnabled(true);
		else
			aukerazkoa.setEnabled(false);
	}

	private void aukerazkoZailtasunaHautatu() {
		
	}

}
