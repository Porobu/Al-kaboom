package si.alkaboom.backend;

import si.alkaboom.frontend.UI;

public class AlKaboom implements AlKaboomConstants {

	private static AlKaboom gureAlKaboom;

	public static AlKaboom getAlKaboom() {
		return (gureAlKaboom == null) ? gureAlKaboom = new AlKaboom() : gureAlKaboom;
	}

	private String datubasePath;
	private String erabiltzailea;

	public String getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	private int[] balioakCustom;

	private UI ui;

	private AlKaboom() {
	}

	public int[] getBalioakCustom() {
		return balioakCustom;
	}

	public String getDatubasePath() {
		return datubasePath;
	}

	public UI getUI() {
		return ui;
	}

	public void jokatu() {
		ui = new UI();
		ui.kautotuHasieratu();
	}

	public void setBalioakCustom(int[] balioakCustom) {
		this.balioakCustom = balioakCustom;
	}

}
