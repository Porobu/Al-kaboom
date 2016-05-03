package si.alkaboom.frontend.kautotu;

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
import si.alkaboom.backend.Erabiltzailea;
import si.alkaboom.backend.TableroModeloa;
import si.alkaboom.externals.SpringUtilities;
import si.alkaboom.frontend.PartidaMezuGrafikoak;

public class KautotuSartzekoPanela extends JPanel implements KeyListener {
	private static final long serialVersionUID = 3676006022124213986L;
	private JLabel erabiltzailea, zailtasuna;
	private JTextField erabiltzaileaField;
	private JButton sartu, aukerazkoa;
	private JComboBox<String> aukerak;
	private String[] aukerakArray;

	public KautotuSartzekoPanela() {
		this.setLayout(new SpringLayout());
		this.erabiltzailea = new JLabel("Erabiltzaile izena:", SwingConstants.TRAILING);
		this.erabiltzaileaField = new JTextField();
		this.erabiltzaileaField.setName("Erabiltzailea Field");
		this.erabiltzaileaField.addActionListener(gureAE -> AlKaboom.getAlKaboom().getUI().getKautotu()
				.getKautotuPanela().taulaEguneratu(this.erabiltzaileaField.getText()));
		this.erabiltzaileaField.addKeyListener(this);
		this.erabiltzailea.setLabelFor(erabiltzaileaField);
		this.zailtasuna = new JLabel("Zailtasuna:", SwingConstants.TRAILING);
		aukerakArray = new String[] { "Erreza", "Normala", "Zaila", "Custom..." };
		this.aukerakEraiki(0);
		this.zailtasuna.setLabelFor(aukerak);
		this.sartu = new JButton("Sartu");
		this.sartu.setName("Sartu");
		this.sartu.setEnabled(false);
		this.sartu.addActionListener(gureAE -> this.datuakGorde());
		this.aukerazkoa = new JButton("Zailtasuna Aukeratu...");
		this.aukerazkoa.setEnabled(false);
		this.aukerazkoa.setName("Zailtasuna Sartu");
		this.aukerazkoa.addActionListener(gureAE -> AlKaboom.getAlKaboom().getUI().getKautotu().zailtasunaIpini());
		this.guztiaGehitu();
	}

	private void aukerakEraiki(int aukeratua) {
		aukerak = new JComboBox<>(aukerakArray);
		this.aukerak.setName("Aukerak");
		aukerak.addItemListener(gureIL -> this.botoiaAldatu());
		aukerak.setSelectedIndex(aukeratua);
	}

	private void botoiaAldatu() {
		if (aukerak.getSelectedIndex() == 3)
			aukerazkoa.setEnabled(true);
		else
			aukerazkoa.setEnabled(false);
	}

	private void datuakGorde() {
		AlKaboom ak = AlKaboom.getAlKaboom();
		if (this.aukerak.getSelectedIndex() == 3)
			ak.setErabiltzailea(new Erabiltzailea(this.erabiltzaileaField.getText(), ak.getBalioakCustom()[0],
					ak.getBalioakCustom()[1], ak.getBalioakCustom()[2]));
		else
			ak.setErabiltzailea(
					new Erabiltzailea(this.erabiltzaileaField.getText(), (String) this.aukerak.getSelectedItem()));
		ak.getErabiltzailea().datubaseaEguneratu();
		PartidaMezuGrafikoak pmg = new PartidaMezuGrafikoak();
		ak.getUI().alKaboomHasieratu();
		if (ak.getErabiltzailea().partidaDauka())
			if (pmg.partidaKargatuGaldetu()) {
				TableroModeloa.getTableroModeloa().partidaKargatu();
				AlKaboom.getAlKaboom().getUI().getTp().listaHasieratu(
						TableroModeloa.getTableroModeloa().getTamaina()[0],
						TableroModeloa.getTableroModeloa().getTamaina()[1]);
				AlKaboom.getAlKaboom().getUI().getTp().tableroaEguneratu();
			}

	}

	private void guztiaGehitu() {
		this.add(erabiltzailea);
		this.add(erabiltzaileaField);
		this.add(zailtasuna);
		this.add(aukerak);
		this.add(this.sartu);
		this.add(aukerazkoa);
		SpringUtilities.makeCompactGrid(this, 3, 2, 3, 3, 3, 3);
	}

	public void izenaAldatu(String izenBerria) {
		this.erabiltzaileaField.setText(izenBerria);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER && arg0.getSource() == sartu)
			this.datuakGorde();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == erabiltzaileaField)
			AlKaboom.getAlKaboom().getUI().getKautotu().getKautotuPanela()
					.taulaEguneratu(this.erabiltzaileaField.getText());
		this.sartuEgoeraAldatu();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void listaAldatu() {
		int[] balioakCustomArray = AlKaboom.getAlKaboom().getBalioakCustom();
		String balioakCustom = balioakCustomArray[0] + "x" + balioakCustomArray[1] + ", " + balioakCustomArray[2];
		this.removeAll();
		aukerakArray[3] = "Custom(" + balioakCustom + ")";
		this.aukerakEraiki(3);
		this.guztiaGehitu();
	}

	public void sartuEgoeraAldatu() {
		if (erabiltzaileaField.getText().trim().equals(""))
			this.sartu.setEnabled(false);
		else
			this.sartu.setEnabled(true);
	}
}
