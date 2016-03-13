package si.alkaboom.frontend;

import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.DBKS;

public final class DBKSMezuGrafikoak {
	private static DBKSMezuGrafikoak gureMezuGrafikoak;

	public static DBKSMezuGrafikoak getDBKSPantailaratu() {
		return gureMezuGrafikoak == null ? gureMezuGrafikoak = new DBKSMezuGrafikoak() : gureMezuGrafikoak;
	}

	private DBKSMezuGrafikoak() {

	}

	/**
	 * Konexioa ixten du datu basearekin
	 */
	public void deskonektatu() {
		try {
			Class<DBKS> dbks = DBKS.class;
			Field f = dbks.getDeclaredField("gureDBKS");
			f.setAccessible(true);
			Method deskonektatu = dbks.getDeclaredMethod("deskonektatu");
			deskonektatu.setAccessible(true);
			deskonektatu.invoke(f.get("DBKS"));
			if (!DBKS.getDBKS().konekatutaDago()) {
				JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Datu basetik deskonektatu zara",
						AlKaboomConstants.IZENBURUA, JOptionPane.WARNING_MESSAGE);
			}
		} catch (HeadlessException | SQLException | NoSuchFieldException | SecurityException | NoSuchMethodException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
