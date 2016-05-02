package si.alkaboom.backend;

import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaHuts;
import si.alkaboom.backend.laukia.LaukiaMina;
import si.alkaboom.backend.laukia.LaukiaZenb;

public final class LaukiFaktoria {
	private static LaukiFaktoria gureLaukiFaktoria;

	private LaukiFaktoria() {

	}

	public static LaukiFaktoria getLaukiFaktoria() {
		return gureLaukiFaktoria != null ? gureLaukiFaktoria : (gureLaukiFaktoria = new LaukiFaktoria());
	}

	private ILaukia laukiaLortu(String izena) {
		switch (izena) {
		case "LaukiaHuts":
			return new LaukiaHuts();
		case "LaukiaMina":
			return new LaukiaMina();
		default:
			return new LaukiaZenb();
		}

	}

	public ILaukia laukiaEgindaLortu(String izena, int zenb, String marka) {
		ILaukia l = this.laukiaLortu(izena);
		l.setZenbakia(zenb);
		l.markaIpini(marka);
		return l;
	}

}
