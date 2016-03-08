package si.alkaboom.backend;

import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public final class DBKS {
	private static DBKS gureDBKS;

	public static DBKS getDBKS() {
		return gureDBKS != null ? gureDBKS : (gureDBKS = new DBKS());
	}

	private Connection konexioa;

	private DBKS() {
	}

	/**
	 * Datu Basearen erantzunik behar ez duten sql aginduak exekutatzen ditu.
	 * 
	 * @param agindua
	 *            Exekutatzeko agindua
	 */
	public void aginduaExekutatu(String agindua) {
		try {
			Statement st = this.konexioa.createStatement();
			st.execute(agindua);
		} catch (Exception salbuespena) {

			throw new AlKaboomSalbuespena("Ezin da " + agindua + " exekutatu", salbuespena);
		}
	}

	/**
	 * Datu basea baliozkoa den frogatzen du
	 */
	private void datubaseaKonprobatu() {
		Statement st;
		try {
			st = konexioa.createStatement();
			st.executeQuery("SELECT Id, Izena,PartidaGordeta, AzkenData, IrabaziKop, GalduKop from Jokalaria");
			st.executeQuery(
					"Select ErabiltzaileID, Minak, Zenbakiak, Hutsak, Egindakoak, MarkatutakoakBanderita, MarkatutakoaGaldera, ErrenkadaKop, ZutabeKop,MinaKop from Partida");
			st.executeQuery("Select Id, Puntuak, PartidaKopurua from Puntuazioa");
			st.close();
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Partiden fitxategia ez da baliozkoa!", e);
		}
	}

	/**
	 * Konexioa ixten du datu basearekin
	 */
	public void deskonektatu() {
		try {
			this.konexioa.close();
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da deskonexioa egin", e);
		}
		try {
			if (konexioa.isClosed()) {
				JOptionPane.showMessageDialog(AlKaboom.getAlKaboom().getUI(), "Datu basetik deskonektatu zara",
						AlKaboomConstants.IZENBURUA, JOptionPane.WARNING_MESSAGE);

			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void eguneraketaExekutatu(String agindua) {
		try {
			Statement st = this.konexioa.createStatement();
			st.executeUpdate(agindua);
		} catch (Exception salbuespena) {
			throw new AlKaboomSalbuespena("Ezin da " + agindua + " exekutatu", salbuespena);
		}
	}

	/**
	 * Metodo honek defektuzko datu basearen path-a itzultzen du,
	 * erabiltzaileraren karpeta pertsonala + Al-Kaboom.db
	 * 
	 * @return Datu basea egoteko defektuzko lekua (null ez bada aurkitu)
	 */
	public String getDefaultPath() {
		File f = new File(System.getProperty("user.home") + "/Al-Kaboom.db");
		if (f.exists())
			return f.getAbsolutePath();
		else
			return null;
	}

	/**
	 * Datu base batetara konekatzen da, SQLite erabiliz
	 * 
	 * @param path
	 *            Datu basea dagoen lekua (ezin da {@code null} izan)
	 */
	public void konektatu(String path) {
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException salbuespena) {
			salbuespena.printStackTrace();
		}
		try {
			this.konexioa = DriverManager.getConnection("jdbc:sqlite:" + path);
			this.konexioa.setAutoCommit(true);
		} catch (SQLException salbuespena) {
			throw new AlKaboomSalbuespena("Ezin da datu basera konektatu", salbuespena);
		}
		this.datubaseaKonprobatu();
	}

	/**
	 * ResultSet bat bueltatzen duten sql aginduak exekutatzen ditu.
	 * 
	 * @param agindua
	 *            Exekutatzeko agindua
	 * @return Datu Baseari eskatutako datuak bueltatzen ditu.
	 */
	public ResultSet kontsultaExekutatu(String agindua) {
		ResultSet emaitza = null;
		try {
			Statement st = this.konexioa.createStatement();
			emaitza = st.executeQuery(agindua);
		} catch (Exception salbuespena) {
			throw new AlKaboomSalbuespena("Ezin da " + agindua + " exekutatu", salbuespena);
		}
		return emaitza;
	}

	/**
	 * Konektatuta dagoen edo ez itxultzen du.
	 * 
	 * @return {@code true} itzultzen du konektatuta badago, {@code false}
	 *         bestela.
	 * @throws SQLException
	 */
	public boolean konekatutaDago() throws SQLException {
		return !konexioa.isClosed();
	}
}
