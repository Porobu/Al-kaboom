package si.alkaboom.backend;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class DBKS {
	private static DBKS gureDBKS;

	public static DBKS getDBKS() {
		return gureDBKS != null ? gureDBKS : (gureDBKS = new DBKS());
	}

	private Connection konexioa;

	protected DBKS() {

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
	private boolean datubaseaKonprobatu() {
		Statement st;
		try {
			st = konexioa.createStatement();
			st.executeQuery("SELECT Id, Izena,PartidaGordeta, AzkenData, IrabaziKop, GalduKop from Jokalaria");
			st.executeQuery("Select ErabiltzaileID, Tableroa, ErrenkadaKop, ZutabeKop from Partida");
			st.executeQuery("Select Id, Puntuak, PartidaKopurua, Denbora, Zailtasuna from Puntuazioa");
			st.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Konexioa ixten du datu basearekin
	 */
	protected void deskonektatu() {
		try {
			this.konexioa.close();
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da deskonexioa egin", e);
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
	 * Konektatuta dagoen edo ez itxultzen du.
	 * 
	 * @return {@code true} itzultzen du konektatuta badago, {@code false}
	 *         bestela.
	 * @throws SQLException
	 */
	public boolean konekatutaDago() throws SQLException {
		return !konexioa.isClosed();
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
		if (!this.datubaseaKonprobatu())
			throw new AlKaboomSalbuespena("Partiden fitxategia ez da baliozkoa!", new IllegalStateException());
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
}
