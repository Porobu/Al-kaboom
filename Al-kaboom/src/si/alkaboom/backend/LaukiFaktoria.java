package si.alkaboom.backend;

import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaHutsa;
import si.alkaboom.backend.laukia.LaukiaMina;
import si.alkaboom.backend.laukia.LaukiaZenb;

public final class LaukiFaktoria {
	private static LaukiFaktoria gureLaukiFaktoria;

	private LaukiFaktoria() {

	}

	public static LaukiFaktoria getLaukiFaktoria() {
		return gureLaukiFaktoria != null ? gureLaukiFaktoria : (gureLaukiFaktoria = new LaukiFaktoria());
	}

	public ILaukia laukiaLortu(String izena) {
		switch (izena) {
		case "LaukiaHutsa":
			return new LaukiaHutsa();
		case "LaukiaMina":
			return new LaukiaMina();
		default:
			return new LaukiaZenb();
		}

	}

}
