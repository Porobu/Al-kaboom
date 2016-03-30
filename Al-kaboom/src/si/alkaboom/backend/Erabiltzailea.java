package si.alkaboom.backend;

public class Erabiltzailea {
	private String izena, zailtasuna;
	private int errenkadak, zutabeak, minak; 

	public Erabiltzailea(String izena, String zailtasuna) {
		this.izena = izena;
		this.zailtasuna = zailtasuna;
	}
	
	public Erabiltzailea(String izena, int errenkadak, int zutabeak, int minak) {
		this.izena = izena;
		this.zailtasuna = "Custom";
		this.errenkadak = errenkadak;
		this.zutabeak = zutabeak;
		this.minak = minak;
	}

	public String getIzena() {
		return izena;
	}

	public String getZailtasuna() {
		return zailtasuna;
	}

	public int getErrenkadak() {
		return errenkadak;
	}

	public int getZutabeak() {
		return zutabeak;
	}

	public int getMinak() {
		return minak;
	}
	
}
