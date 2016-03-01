package si.alkaboom.salbuespenak;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.exception.ExceptionUtils;

import si.alkaboom.backend.AlKaboom;

public class AlKaboomSalbuespena extends RuntimeException {
	private static final long serialVersionUID = -9212558555853305997L;

	public AlKaboomSalbuespena(String mezua, Exception errorea) {
		String izenburua = "Al-Kaboom Errorea!!!!!";
		System.err.println(izenburua + "\n");
		String fitxategia = System.getProperty("user.home") + "/Al-KaboomErrorea.txt";
		String gorde = "\nStack trace " + fitxategia + " fitxategian gorde da.";
		errorea.printStackTrace();
		if (System.getProperty("os.name").toLowerCase().contains("win"))
			fitxategia = System.getProperty("user.home") + "\\Al-KaboomErrorea.txt";
		try {
			PrintWriter gurePW = new PrintWriter(new File(fitxategia));
			gurePW.println(izenburua);
			gurePW.println(mezua);
			gurePW.println("Stack Trace:");
			gurePW.write(ExceptionUtils.getStackTrace(errorea));
			gurePW.flush();
			gurePW.close();
		} catch (FileNotFoundException e) {
			gorde = "";
		}
		String stackLaburra = ExceptionUtils.getRootCauseStackTrace(errorea)[0] + "\n"
				+ ExceptionUtils.getRootCauseStackTrace(errorea)[1] + "\nthrown "
				+ ExceptionUtils.getRootCauseStackTrace(this)[1];
		JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(),
				mezua + "\nStack Trace Laburra:\n" + stackLaburra + gorde, izenburua, JOptionPane.ERROR_MESSAGE);
	}
}