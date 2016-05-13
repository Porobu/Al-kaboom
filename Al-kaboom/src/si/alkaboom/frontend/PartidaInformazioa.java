package si.alkaboom.frontend;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.Erlojua;

public class PartidaInformazioa {
	public void informazioaPantailaratu() {
		Erlojua e = AlKaboom.getAlKaboom().getErabiltzailea().getErlojua();
		e.erlojuaPausatu();
		long denbora = e.getDenbora();
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Igarotako denbora: " + denbora / 1000F,
				AlKaboomConstants.IZENBURUA, JOptionPane.INFORMATION_MESSAGE);
		e.erlojuaMartxanIpini();
	}
}
