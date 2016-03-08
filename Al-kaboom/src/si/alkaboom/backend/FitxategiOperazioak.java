package si.alkaboom.backend;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class FitxategiOperazioak {

	public FitxategiOperazioak() {

	}

	/**
	 * Datu basea ateratzen du Jar fitxategitik, eta esandako lekura idazten du
	 * 
	 * @param resourceName
	 *            Zein fitxategi kopiatu
	 * @param path
	 *            Nora kopiatu
	 * @return Kopiatutako fitxategiaren lekua
	 */
	public String dbEsportatu(String resourceName, String path) {
		InputStream in = null;
		OutputStream out = null;
		String jarFolder;
		try {
			in = DBKS.class.getResourceAsStream(resourceName);
			if (in == null) {
				throw new AlKaboomSalbuespena("Ez da partiden fitxategia aurkitu .jar fitxategian",
						new FileNotFoundException());
			}
			int readBytes;
			byte[] buffer = new byte[4096];
			File f = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI());
			jarFolder = f.toString();
			out = new FileOutputStream(path);
			while ((readBytes = in.read(buffer)) > 0) {
				out.write(buffer, 0, readBytes);
			}
		} catch (Exception ex) {
			throw new AlKaboomSalbuespena("Ezin da partiden fitxategia sortu", ex);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {

			}
		}
		return jarFolder + resourceName;
	}

	/**
	 * Datu basea kopiatzen du esandako lekura
	 * 
	 * @param pathZaharra
	 *            oraingo fitxategiaren path-a
	 * @param pathBerria
	 *            kopiaren path-a
	 */
	public void kopiatu(String pathZaharra, String pathBerria) {
		File oraingoa = new File(pathZaharra);
		File berria = new File(pathBerria);
		try {
			Files.copy(oraingoa.toPath(), berria.toPath(), COPY_ATTRIBUTES);
		} catch (IOException e) {
			throw new AlKaboomSalbuespena("Ezin da fitxategia kopiatu", e);
		}
	}
}
