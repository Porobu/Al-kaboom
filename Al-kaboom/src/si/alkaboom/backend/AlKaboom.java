package si.alkaboom.backend;

import si.alkaboom.frontend.kautotu.Kautotu;

public class AlKaboom implements AlKaboomConstants {

	private static AlKaboom gureAlKaboom;

	public static AlKaboom getAlKaboom() {
		return (gureAlKaboom == null) ? gureAlKaboom = new AlKaboom() : gureAlKaboom;
	}

	private String datubasePath;

	private int[] balioakCustom;

	private Kautotu kautotu;

	private AlKaboom() {
	}

	public int[] getBalioakCustom() {
		return balioakCustom;
	}

	public String getDatubasePath() {
		return datubasePath;
	}

	public Kautotu getKautotu() {
		return kautotu;
	}

	public void jokatu() {
		kautotu = new Kautotu();
	}

	public void setBalioakCustom(int[] balioakCustom) {
		this.balioakCustom = balioakCustom;
	}

}
