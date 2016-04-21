package si.alkaboom.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class DBOperazioak {

	public void azkenDataEguneratu(String erabiltzailea) {
		SimpleDateFormat formatua = new SimpleDateFormat("dd-MM-yyyy");
		Date data = new Date();
		String agindua = "UPdate  Jokalaria Set Azkendata = '" + formatua.format(data) + "' where Izena = '"
				+ erabiltzailea + "'";
		DBKS.getDBKS().eguneraketaExekutatu(agindua);
	}

	public boolean erabiltzaileaDago(String erabiltzailea) {
		String agindua = "Select * from Jokalaria where Izena = '" + erabiltzailea + "'";
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(agindua);
		try {
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	public void erabiltzaileaErregistratu(String erabiltzailea) {
		SimpleDateFormat formatua = new SimpleDateFormat("dd-MM-yyyy");
		Date data = new Date();
		String agindua = "Insert into Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) values ('"
				+ erabiltzailea + "', 'Ez', '" + formatua.format(data) + "', 0, 0)";
		DBKS.getDBKS().aginduaExekutatu(agindua);
	}

	public ArrayList<String[]> erabiltzaileakBistaratu(String izena) {
		izena = "%" + izena + "%";
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(
				"Select Izena, PartidaGordeta, AzkenData from Jokalaria where Izena like '" + izena + "'");
		try {
			return this.rsKopiatu(rs, rs.getMetaData().getColumnCount());
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da zutabe kopurua kalkulatu", e);
		}
	}

	public ArrayList<String[]> erabiltzaileenPuntuazioakLortu(String mota) {
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(
				"Select Izena, Puntuak, Denbora from Jokalaria, Puntuazioa where Puntuazioa.Zailtasuna like '%" + mota
						+ "%' and Jokalaria.ID = Puntuazioa.ID");
		try {
			return this.rsKopiatu(rs, rs.getMetaData().getColumnCount());
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da zutabe kopurua kalkulatu", e);
		}
	}

	public void partidaGorde(String partida, int errenkadak, int zutabeak) {

	}

	public boolean partidaGordetaDago(String erabiltzailea) {
		String agindua = "Select PartidaGordeta from Jokalaria where Izena = '" + erabiltzailea + "'";
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(agindua);
		String partida = "";
		try {
			rs.next();
			partida = rs.getString(1);
		} catch (SQLException e) {
			return false;
		}
		return (partida.trim().equalsIgnoreCase("Bai") ? true : false);
	}

	private ArrayList<String[]> rsKopiatu(ResultSet rs, int kop) {
		ArrayList<String[]> emaitza = new ArrayList<>();
		try {
			while (rs.next()) {
				String[] oraingoa = new String[kop];
				for (int i = 0; i < kop; i++) {
					oraingoa[i] = rs.getString(i + 1);
				}
				emaitza.add(oraingoa);
			}
		} catch (SQLException e) {
		}
		return emaitza;
	}
}
