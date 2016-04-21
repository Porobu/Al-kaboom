package si.alkaboom.frontend;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;

public class PartidaKargatuJOP {
	public boolean partidaKargatuGaldetu() {
		UI ui = AlKaboom.getAlKaboom().getUI();
		int aukera = JOptionPane.showConfirmDialog(ui, "Gordetako partida kargatu nahi duzu?",
				AlKaboomConstants.IZENBURUA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (aukera == JOptionPane.YES_OPTION) ? true : false;
	}

}
