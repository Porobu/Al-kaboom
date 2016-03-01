package si.alkaboom.frontend;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.FitxategiOperazioak;
import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class FitxategiOperazioakUI {
	private FitxategiOperazioak nireFO;

	public FitxategiOperazioakUI() {
		nireFO = new FitxategiOperazioak();
	}

	public void datuBaseaEraiki(String path) {
		int aukera = JOptionPane.YES_OPTION;
		File fitxategia = new File(path);
		if (fitxategia.exists())
			aukera = JOptionPane.showConfirmDialog(AlKaboom.getAlKaboom().getUI(),
					"Partiden fitxategia existitzen da izen horrekin, jarraitzen baduzu ezabatu egingo da.",
					AlKaboomConstants.IZENBURUA, JOptionPane.YES_NO_OPTION);
		if (aukera == JOptionPane.NO_OPTION || aukera == JOptionPane.CLOSED_OPTION)
			System.exit(1);
		fitxategia.delete();
		FitxategiOperazioak nireFO = new FitxategiOperazioak();
		nireFO.dbEsportatu("/si/alkaboom/Al-Kaboom.db", path);
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(),
				"Partiden fitxategia " + path + " karpetan gorde da.", AlKaboomConstants.IZENBURUA,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public String datuBaseaGordetzekoPath() {
		String path;
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setDialogTitle(AlKaboomConstants.IZENBURUA);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("Al-Kaboom partidak", "db"));
		gureFileChooser.setSelectedFile(new File("Al-Kaboom"));
		int gureZenbakia = gureFileChooser.showSaveDialog(AlKaboom.getAlKaboom().getUI());
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

	public String getPath() {
		String path;
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setDialogTitle(AlKaboomConstants.IZENBURUA);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("Al-Kaboom partidak", "db"));
		int gureZenbakia = gureFileChooser.showOpenDialog(AlKaboom.getAlKaboom().getUI());
		if (gureZenbakia == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		try {
			path = gureFileChooser.getSelectedFile().getAbsolutePath();
		} catch (Exception salbuespena) {
			throw new AlKaboomSalbuespena("Fitxategiak ez du balio!!!!!", salbuespena);
		}
		return path;
	}

	public void kopiaEgin() {
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(),
				"Hautatu non egin nahi duzun datu basearen kopia.\nPartida guztiak kopiatuko dira.",
				AlKaboomConstants.IZENBURUA, JOptionPane.INFORMATION_MESSAGE);
		String path = AlKaboom.getAlKaboom().getDatubasePath();
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setDialogTitle(AlKaboomConstants.IZENBURUA);
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("Al-Kaboom partidak", "db"));
		while (AlKaboom.getAlKaboom().getDatubasePath().equals(path)) {
			int gureZenbakia = gureFileChooser.showSaveDialog(AlKaboom.getAlKaboom().getUI());
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
			if (AlKaboom.getAlKaboom().getDatubasePath().equals(path))
				JOptionPane.showMessageDialog(gureFileChooser,
						"Hautatu duzun fitxategia kargatu duzunaren berdina da.\nMesedez, aukeratu beste bat",
						AlKaboomConstants.IZENBURUA, JOptionPane.WARNING_MESSAGE);
		}
		nireFO.kopiatu(path);
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(),
				"Partiden fitxategiaren kopia " + path + " karpetan gorde da.", AlKaboomConstants.IZENBURUA,
				JOptionPane.INFORMATION_MESSAGE);

	}
}