package si.alkaboom.frontend;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.DBKS;

public class AukerakUI {

	private String path;
	private int aukera;
	private FitxategiOperazioakUI nireFOUI;

	public AukerakUI() {
		aukera = JOptionPane.YES_OPTION;
		nireFOUI = new FitxategiOperazioakUI();
	}

	private void aukeratu() {
		String luzea = "Gogoratu partiden fitxategia zure karpeta pertsonalean badago eta\nAl-Kaboom izena badu, automatikoki kargatuko da.";
		switch (aukera) {
		case JOptionPane.YES_OPTION:
			path = nireFOUI.getPath();
			break;
		case JOptionPane.NO_OPTION:
			JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), luzea, AlKaboomConstants.IZENBURUA,
					JOptionPane.INFORMATION_MESSAGE);
			path = nireFOUI.datuBaseaGordetzekoPath();
			nireFOUI.datuBaseaEraiki(path);
			break;
		case JOptionPane.CANCEL_OPTION:
			break;
		default:
			System.exit(0);
			break;
		}
	}

	public String hasi() {
		path = DBKS.getDBKS().getDefaultPath();
		if (path == null)
			hasiEzDefektuzkoDB();
		else
			hasiDefektuzkoDB();
		return path;
	}

	private void hasiDefektuzkoDB() {
		String[] aukerak = { "Beste Bat Erabili", "Berri bat sortu", "Defektuzkoa Kargatu" };
		aukera = JOptionPane.showOptionDialog(AlKaboom.getAlKaboom().getUI(),
				"Defektuzko partiden fitxategia " + path + " karpetan aurkitu da.", AlKaboomConstants.IZENBURUA,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[2]);
		this.aukeratu();
	}

	private void hasiEzDefektuzkoDB() {
		String[] aukerak = { "Ireki", "Berria Sortu" };
		aukera = JOptionPane.showOptionDialog(AlKaboom.getAlKaboom().getUI(),
				"Partiden fitxategia beste leku batetik ireki edo berria sortu nahi duzu?\nAl-Kaboom erabiltzen duzun lehenengo aldia bada, berria sortu sakatu.",
				AlKaboomConstants.IZENBURUA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak,
				aukerak[1]);
		this.aukeratu();
	}
}
