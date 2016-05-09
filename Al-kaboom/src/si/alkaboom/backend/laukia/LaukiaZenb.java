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
		return irekita ? AlKaboomConstants.IREKITA : this.marka;
	}

	@Override
	public int getZenbakia() {
		return zenbakia;
	}

	@Override
	public boolean irekitaDago() {
		return irekita;
	}

	@Override
	public void laukiaIreki() {
		irekita = true;
	}

	@Override
	public void markaIpini(String marka) {
		this.marka = marka;
	}

	@Override
	public void setZenbakia(int berria) {
		this.zenbakia = berria;
	}

	public void zenbPlus() {
		zenbakia++;
	}

}
