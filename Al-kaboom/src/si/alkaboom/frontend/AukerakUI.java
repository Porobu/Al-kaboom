package si.alkaboom.frontend;

import javax.swing.JOptionPane;

import si.alkaboom.backend.DBKS;
import si.alkaboom.backend.Nagusia;

public class AukerakUI {

	private String path;
	private int aukera;
	private FitxategiOperazioakUI nireFOUI;

	public AukerakUI() {
		aukera = JOptionPane.YES_OPTION;
		nireFOUI = new FitxategiOperazioakUI();
	}

	private void aukeratu() {
		String luzea = "Gogoratu datu basea zure karpeta pertsonalean badago eta\nWinterTwitter izena badu, automatikoki kargatuko da.";
		switch (aukera) {
		case JOptionPane.YES_OPTION:
			path = nireFOUI.getPath();
			break;
		case JOptionPane.NO_OPTION:
			JOptionPane.showMessageDialog(null, luzea, Nagusia.IZENBURUA, JOptionPane.INFORMATION_MESSAGE);
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
		aukera = JOptionPane.showOptionDialog(null, "Defektuzko datu basea " + path + " karpetan aurkitu da.",
				Nagusia.IZENBURUA, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak,
				aukerak[2]);
		this.aukeratu();
	}

	private void hasiEzDefektuzkoDB() {
		String[] aukerak = { "Ireki", "Berria Sortu" };
		aukera = JOptionPane.showOptionDialog(null,
				"Datu Basea beste leku batetik ireki edo berria sortu nahi duzu?\nAplikazioa erabili duzun lehenengo aldia bada, berria sortu sakatu.",
				Nagusia.IZENBURUA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[1]);
		this.aukeratu();
	}
}
