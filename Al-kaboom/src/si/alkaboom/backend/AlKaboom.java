package si.alkaboom.backend;

import si.alkaboom.frontend.AukerakUI;
import si.alkaboom.frontend.UI;

public final class AlKaboom implements AlKaboomConstants {

	private static AlKaboom gureAlKaboom;

	public static AlKaboom getAlKaboom() {
		return (gureAlKaboom == null) ? gureAlKaboom = new AlKaboom() : gureAlKaboom;
	}

	private String datubasePath;
	private Erabiltzailea erabiltzailea;

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

	public Erabiltzailea getErabiltzailea() {
		return erabiltzailea;
	}

	public UI getUI() {
		return ui;
	}

	public void jokatu() {
		ui = new UI();
		ui.dekorazioGabeHasieratu();
		AukerakUI gureAUI = new AukerakUI();
		datubasePath = gureAUI.hasi();
		DBKS.getDBKS().konektatu(datubasePath);
		ui.kautotuHasieratu();
	}

	public void setBalioakCustom(int[] balioakCustom) {
		this.balioakCustom = balioakCustom;
	}

	public void setDatubasePath(String datubasePath) {
		this.datubasePath = datubasePath;
	}

	public void setErabiltzailea(Erabiltzailea erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}

}
