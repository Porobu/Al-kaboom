package si.alkaboom.frontend.tableroa;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.DBOperazioak;
import si.alkaboom.backend.TableroModeloa;
import si.alkaboom.frontend.UI;

public class PartidaMezuGrafikoak {
	public void partidaEzabatu() {
		DBOperazioak o = new DBOperazioak();
		if (!o.partidaGordetaDago(AlKaboom.getAlKaboom().getErabiltzailea().getIzena())) {
			JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Ez da aurkitu gordetako partidarik!",
					AlKaboomConstants.IZENBURUA, JOptionPane.ERROR_MESSAGE);
			return;
		}
		int aukera = JOptionPane.showConfirmDialog(AlKaboom.getAlKaboom().getUI(),
				"Gordetako partida ezabatu nahi duzu?\nBetiko galduko da!", AlKaboomConstants.IZENBURUA,
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (aukera == JOptionPane.NO_OPTION)
			return;
		o.partidaEzabatu();
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Partida ezabatu da.",
				AlKaboomConstants.IZENBURUA, JOptionPane.INFORMATION_MESSAGE);
	}

	public void partidaKargatu() {
		DBOperazioak o = new DBOperazioak();
		if (!o.partidaGordetaDago(AlKaboom.getAlKaboom().getErabiltzailea().getIzena())) {
			JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Ez da aurkitu gordetako partidarik!",
					AlKaboomConstants.IZENBURUA, JOptionPane.ERROR_MESSAGE);
			return;
		}
		int aukera = JOptionPane.showConfirmDialog(AlKaboom.getAlKaboom().getUI(),
				"Partida kargatzean oraingoa galduko duzu\nKargatzen jarraitu nahi duzu?", AlKaboomConstants.IZENBURUA,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (aukera == JOptionPane.NO_OPTION)
			return;
		TableroModeloa.getTableroModeloa().partidaKargatu();
		AlKaboom.getAlKaboom().getUI().getTp().listaHasieratu(TableroModeloa.getTableroModeloa().getTamaina()[0],
				TableroModeloa.getTableroModeloa().getTamaina()[1]);
		AlKaboom.getAlKaboom().getUI().getTp().tableroaEguneratu();
	}

	public boolean partidaKargatuGaldetu() {
		UI ui = AlKaboom.getAlKaboom().getUI();
		int aukera = JOptionPane.showConfirmDialog(ui, "Gordetako partida kargatu nahi duzu?",
				AlKaboomConstants.IZENBURUA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (aukera == JOptionPane.YES_OPTION) ? true : false;
	}

	public void patidaGorde() {
		if (AlKaboom.getAlKaboom().getUI().getTp().isHasiera()) {
			JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Ezin da partida gorde hasi ez delako!",
					AlKaboomConstants.IZENBURUA, JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (TableroModeloa.getTableroModeloa().partidaGaldutaDago()
				|| TableroModeloa.getTableroModeloa().isPartidaIrabazita()) {
			JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Ezin da bukatutako partida bat gorde!",
					AlKaboomConstants.IZENBURUA, JOptionPane.ERROR_MESSAGE);
			return;
		}
		int aukera = JOptionPane.showConfirmDialog(AlKaboom.getAlKaboom().getUI(),
				"Partida gorde nahi duzu?\nGordetako beste partidaren bat ezabatu daiteke", AlKaboomConstants.IZENBURUA,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (aukera == JOptionPane.NO_OPTION)
			return;
		TableroModeloa.getTableroModeloa().partidaGorde();
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Partida gorde da", AlKaboomConstants.IZENBURUA,
				JOptionPane.INFORMATION_MESSAGE);

	}

}
