package si.alkaboom.frontend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.Nagusia;
import si.alkaboom.externals.SpringUtilities;

public class KautotuPanela extends JPanel implements KeyListener {

	private JLabel erabiltzailea, zailtasuna;
	private JTextField erabiltzaileaField;
	private JButton sartu, aukerazkoa;
	private JComboBox<String> aukerak;
	private String[] aukerakArray;
	private String erabiltzaileaString;
	private static final long serialVersionUID = 4842340182997453654L;

	public KautotuPanela() {
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
		this.aukerazkoa.addActionListener(gureAE -> AlKaboom.getAlKaboom().getKautotu().zailtasunaIpini());
		this.add(aukerazkoa);
		SpringUtilities.makeCompactGrid(this, 3, 2, 1, 1, 3, 3);
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
			this.datuakGorde();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
