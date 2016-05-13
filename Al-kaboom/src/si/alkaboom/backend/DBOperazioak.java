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
		String agindua = "Update Jokalaria Set Azkendata = '" + formatua.format(data) + "' where Izena = '"
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
		String agindua = "Insert into Jokalaria(Izena, AzkenData, IrabaziKop, GalduKop) values ('" + erabiltzailea
				+ "', '" + formatua.format(data) + "', 0, 0)";
		DBKS.getDBKS().aginduaExekutatu(agindua);
	}

	public ArrayList<String[]> erabiltzaileakBistaratu(String izena) {
		izena = "%" + izena + "%";
		ResultSet rs = DBKS.getDBKS()
				.kontsultaExekutatu("Select Izena from Jokalaria where izena like '" + izena + "'");
		ArrayList<String> izenak = new ArrayList<>();
		try {
			while (rs.next()) {
				izenak.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin dira izenak lortu", e);
		}
		ArrayList<String[]> lista = new ArrayList<>();
		try {
			for (int i = 0; i < izenak.size(); i++) {
				rs = DBKS.getDBKS().kontsultaExekutatu(
						"Select Izena, AzkenData, IrabaziKop, GalduKop from Jokalaria where Izena = '" + izenak.get(i)
								+ "'");
				String[] datuak = new String[5];
				rs.next();
				datuak[0] = rs.getString(1);
				datuak[2] = rs.getString(2);
				datuak[3] = rs.getString(3);
				datuak[4] = rs.getString(4);
				rs = DBKS.getDBKS().kontsultaExekutatu(
						"Select * from Partida where ErabiltzaileID = (Select Id from Jokalaria where Izena = '"
								+ izenak.get(i) + "')");
				datuak[1] = rs.next() ? "Bai" : "Ez";
				lista.add(datuak);
			}
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin dira erabiltzaileak bistaratu", e);
		}
		return lista;

	}

	public ArrayList<String[]> erabiltzaileenPuntuazioakLortu(String mota) {
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(
				"Select Izena, Denbora from Jokalaria, Puntuazioa where Puntuazioa.Zailtasuna like '%" + mota
						+ "%' and Jokalaria.ID = Puntuazioa.ID");
		try {
			return this.rsKopiatu(rs, rs.getMetaData().getColumnCount());
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da zutabe kopurua kalkulatu", e);
		}
	}

	private int getID() {
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select Id from Jokalaria where Izena = '"
				+ AlKaboom.getAlKaboom().getErabiltzailea().getIzena() + "'");
		try {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da ID-a lortu!", e);
		}
	}

	public void partidaEzabatu() {
		int id = this.getID();
		DBKS.getDBKS().aginduaExekutatu("DELETE FROM Partida where ErabiltzaileID = " + id);
	}

	public void partidaGaldu() {
		String erabiltzaileIzena = AlKaboom.getAlKaboom().getErabiltzailea().getIzena();
		DBKS.getDBKS().eguneraketaExekutatu(
				"UPDATE Jokalaria SET GalduKop = GalduKop + 1 where Izena = '" + erabiltzaileIzena + "'");
	}

	public void partidaGorde(String partida, int errenkadak, int zutabeak) {
		int id = this.getID();
		DBKS.getDBKS().aginduaExekutatu("INSERT OR REPLACE INTO Partida VALUES (" + id + ", '" + partida + "', "
				+ errenkadak + ", " + zutabeak + ")");
	}

	public boolean partidaGordetaDago(String erabiltzailea) {
		String agindua = "Select * from Partida where ErabiltzaileID = (Select Id from Jokalaria where Izena = '"
				+ erabiltzailea + "')";
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu(agindua);
		try {
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	public void partidaIrabazi() {
		Erabiltzailea e = AlKaboom.getAlKaboom().getErabiltzailea();
		String erabiltzaileIzena = e.getIzena();
		DBKS.getDBKS().eguneraketaExekutatu(
				"UPDATE Jokalaria SET IrabaziKop = IrabaziKop + 1 where Izena = '" + erabiltzaileIzena + "'");
		int id = this.getID();
		DBKS.getDBKS().aginduaExekutatu("INSERT INTO Puntuazioa VALUES(" + id + "," + e.getErlojua().getDenbora() / 1000
				+ ",'" + e.getZailtasuna() + "')");
	}

	public String[] partidaKargatu() {
		if (!this.partidaGordetaDago(AlKaboom.getAlKaboom().getErabiltzailea().getIzena()))
			return new String[1];
		int id = this.getID();
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("SELECT * from Partida Where ErabiltzaileID = " + id);
		try {
			rs.next();
			return new String[] { rs.getString(2), rs.getString(3), rs.getString(4) };
		} catch (SQLException e) {
			throw new AlKaboomSalbuespena("Ezin da partida kargatu!", e);
		}
	}

	private ArrayList<String[]> rsKopiatu(ResultSet rs, int kop) {
		ArrayList<String[]> emaitza = new ArrayList<>();
		try {
			while (rs.next()) {
				String[] oraingoa = new String[kop];
				for (int i = 0; i < kop; i++)
					oraingoa[i] = rs.getString(i + 1);
				emaitza.add(oraingoa);
			}
		} catch (SQLException e) {
		}
		return emaitza;
	}
}
