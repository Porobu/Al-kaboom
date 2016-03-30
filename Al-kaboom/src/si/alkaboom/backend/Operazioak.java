package si.alkaboom.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class Operazioak {

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
}
