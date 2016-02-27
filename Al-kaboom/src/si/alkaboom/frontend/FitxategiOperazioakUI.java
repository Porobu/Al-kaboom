package si.alkaboom.frontend;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import si.alkaboom.backend.FitxategiOperazioak;
import si.alkaboom.backend.Nagusia;
import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class FitxategiOperazioakUI {
	private FitxategiOperazioak nireFO;

	public FitxategiOperazioakUI() {
		nireFO = new FitxategiOperazioak();
	}

	public void kopiaEgin() {
		JOptionPane.showMessageDialog(null,
				"Hautatu non egin nahi duzun datu basearen kopia.\nTwitter-etik deskargatu duzun informazio guztia kopiatuko da.",
				Nagusia.IZENBURUA, JOptionPane.INFORMATION_MESSAGE);
		String path = Nagusia.getPath();
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setDialogTitle(Nagusia.IZENBURUA);
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("WinterTwitter Datu Baseak", "db"));
		while (Nagusia.getPath().equals(path)) {
			int gureZenbakia = gureFileChooser.showSaveDialog(null);
			if (gureZenbakia == JFileChooser.CANCEL_OPTION)
				return;
			try {
				path = gureFileChooser.getSelectedFile().getAbsolutePath();
				if (!path.contains(".db")) {
					path = path + ".db";
				}
			} catch (Exception salbuespena) {
				throw new AlKaboomSalbuespena("Fitxategiak ez du balio!!!!!", salbuespena);
			}
			if (Nagusia.getPath().equals(path))
				JOptionPane.showMessageDialog(gureFileChooser,
						"Hautatu duzun fitxategia kargatu duzunaren berdina da.\nMesedez, aukeratu beste bat",
						Nagusia.IZENBURUA, JOptionPane.WARNING_MESSAGE);
		}
		nireFO.kopiatu(path);
		JOptionPane.showMessageDialog(null, "Datu basearen kopia " + path + " karpetan gorde da.", Nagusia.IZENBURUA,
				JOptionPane.INFORMATION_MESSAGE);

	}

	public String datuBaseaGordetzekoPath() {
		String path;
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setDialogTitle(Nagusia.IZENBURUA);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("WinterTwitter Datu Baseak", "db"));
		gureFileChooser.setSelectedFile(new File("WinterTwitter"));
		int gureZenbakia = gureFileChooser.showSaveDialog(null);
		if (gureZenbakia == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		try {
			path = gureFileChooser.getSelectedFile().getAbsolutePath();
			if (!path.contains(".db")) {
				path = path + ".db";
			}
		} catch (Exception salbuespena) {
			throw new AlKaboomSalbuespena("Fitxategiak ez du balio!!!!!", salbuespena);
		}
		return path;
	}

	public void datuBaseaEraiki(String path) {
		int aukera = JOptionPane.YES_OPTION;
		File fitxategia = new File(path);
		if (fitxategia.exists())
			aukera = JOptionPane.showConfirmDialog(null,
					"Datu basea existitzen da izen horrekin, jarraitzen baduzu ezabatu egingo da.", Nagusia.IZENBURUA,
					JOptionPane.YES_NO_OPTION);
		if (aukera == JOptionPane.NO_OPTION || aukera == JOptionPane.CLOSED_OPTION)
			System.exit(1);
		fitxategia.delete();
		FitxategiOperazioak nireFO = new FitxategiOperazioak();
		try {
			nireFO.dbEsportatu("/isad/winteriscoming/DatuBasea.db", path);
		} catch (Exception e) {
			new AlKaboomSalbuespena("Ezin da fitxategia esportatu", e);
		}
		JOptionPane.showMessageDialog(null, "Datu basea " + path + " karpetan gorde da.", Nagusia.IZENBURUA,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public String getPath() {
		String path;
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setDialogTitle(Nagusia.IZENBURUA);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("WinterTwitter Datu Baseak", "db"));
		int gureZenbakia = gureFileChooser.showOpenDialog(null);
		if (gureZenbakia == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		try {
			path = gureFileChooser.getSelectedFile().getAbsolutePath();
		} catch (Exception salbuespena) {
			throw new AlKaboomSalbuespena("Fitxategiak ez du balio!!!!!", salbuespena);
		}
		return path;
	}
}