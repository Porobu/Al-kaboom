package si.alkaboom.backend.laukia;

import si.alkaboom.backend.AlKaboomConstants;

public class LaukiaZenb implements ILaukia {

	private int zenbakia;
	private String marka;
	private boolean irekita;

	public LaukiaZenb() {
		zenbakia = 1;
		this.marka = AlKaboomConstants.MARKARIK_EZ;
		this.irekita = false;
	}

	@Override
	public String daukanMarka() {
		return irekita ? AlKaboomConstants.MARKARIK_EZ : this.marka;
	}

	public int getZenbakia() {
		return zenbakia;
	}

	@Override
	public boolean irekitaDago() {
		return irekita;
	}

	public void laukiaIreki() {
		irekita = true;
	}

	public void zenbPlus() {
		zenbakia++;
	}

}
