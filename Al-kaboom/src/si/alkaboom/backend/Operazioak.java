package si.alkaboom.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operazioak {

	public ArrayList<String[]> erabiltzaileakBistaratu(int kop, String izena) {
		izena = "%" + izena + "%";
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(
				"Select Izena, PartidaGordeta, AzkenData from Jokalaria where Izena like '" + izena + "'");
		return this.rsKopiatu(rs, kop);
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
