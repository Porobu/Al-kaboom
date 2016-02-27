package si.alkaboom.backend;

import si.alkaboom.frontend.Kautotu;

public class AlKaboom {
	public static final float BERTSIOA = 0.05F;
	public static final String IZENBURUA = "Al-Kaboom " + BERTSIOA;
	private static AlKaboom gureAlKaboom;
	private String datubasePath;

	public String getDatubasePath() {
		return datubasePath;
	}

	public static AlKaboom getAlKaboom() {
		return (gureAlKaboom == null) ? gureAlKaboom = new AlKaboom() : gureAlKaboom;
	}

	private Kautotu kautotu;

	private AlKaboom() {
	}

	public Kautotu getKautotu() {
		return kautotu;
	}

	public void jokatu() {
		kautotu = new Kautotu();
	}

}
