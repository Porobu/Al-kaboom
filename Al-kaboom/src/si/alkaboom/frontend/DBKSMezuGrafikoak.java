package si.alkaboom.frontend;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.DBKS;

public final class DBKSMezuGrafikoak {

	/**
	 * Konexioa ixten du datu basearekin
	 */

	public void deskonektatu() {
		DBKS.getDBKS().deskonektatu();
		try {
			if (!DBKS.getDBKS().konekatutaDago()) {
				JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Datu basetik deskonektatu zara",
						AlKaboomConstants.IZENBURUA, JOptionPane.WARNING_MESSAGE);
			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}
}
