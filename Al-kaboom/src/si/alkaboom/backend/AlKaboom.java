package si.alkaboom.backend;

import si.alkaboom.frontend.Kautotu;

public class AlKaboom {
	private Kautotu kautotu;
	private static AlKaboom gureAlKaboom;

	private AlKaboom() {
	}

	public static AlKaboom getAlKaboom() {
		return (gureAlKaboom == null) ? gureAlKaboom = new AlKaboom() : gureAlKaboom;
	}

	public void jokatu() {
		kautotu = new Kautotu();
	}

	public Kautotu getKautotu() {
		return kautotu;
	}

}
