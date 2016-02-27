package si.alkaboom.backend;

import si.alkaboom.frontend.Kautotu;

public class AlKaboom {
	private static AlKaboom gureAlKaboom;

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
