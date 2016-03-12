package si.alkaboom.frontend;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.DBKS;

public final class DBKSMezuGrafikoak extends DBKS {
	private static DBKSMezuGrafikoak gureMezuGrafikoak;

	public static DBKSMezuGrafikoak getDBKSPantailaratu() {
		return gureMezuGrafikoak == null ? gureMezuGrafikoak = new DBKSMezuGrafikoak() : gureMezuGrafikoak;
	}

	private DBKSMezuGrafikoak() {

	}

	/**
	 * Konexioa ixten du datu basearekin
	 */
	@Override
	public void deskonektatu() {
		super.deskonektatu();
		try {
			if (!super.konekatutaDago()) {
				JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Datu basetik deskonektatu zara",
						AlKaboomConstants.IZENBURUA, JOptionPane.WARNING_MESSAGE);
			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}
}
