package si.alkaboom.backend;

public class Erabiltzailea {
	private String izena, zailtasuna;
	private int errenkadak, zutabeak, minak;
	private Erlojua e;

	public Erabiltzailea(String izena, int errenkadak, int zutabeak, int minak) {
		this.izena = izena;
		this.zailtasuna = "Custom";
		this.errenkadak = errenkadak;
		this.zutabeak = zutabeak;
		this.minak = minak;
		this.e = new Erlojua();
	}

	public Erlojua getErlojua() {
		return e;
	}

	public Erabiltzailea(String izena, String zailtasuna) {
		this.izena = izena;
		this.zailtasuna = zailtasuna;
		this.e = new Erlojua();
	}

	public void erabiltzaileaEguneratu() {
		DBOperazioak o = new DBOperazioak();
		if (!o.erabiltzaileaDago(this.izena))
			o.erabiltzaileaErregistratu(this.izena);
		else
			o.azkenDataEguneratu(this.izena);
	}

	public int getErrenkadak() {
		return errenkadak;
	}

	public String getIzena() {
		return izena;
	}

	public int getMinak() {
		return minak;
	}

	public String getZailtasuna() {
		return zailtasuna;
	}

	public int getZutabeak() {
		return zutabeak;
	}

	public boolean partidaDauka() {
		DBOperazioak o = new DBOperazioak();
		return o.partidaGordetaDago(izena);
	}
}
