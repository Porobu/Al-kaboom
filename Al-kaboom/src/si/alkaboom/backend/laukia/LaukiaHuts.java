package si.alkaboom.backend.laukia;

import si.alkaboom.backend.AlKaboomConstants;

public class LaukiaHuts implements ILaukia {
	private String marka;
	private boolean irekita;

	public LaukiaHuts() {
		this.marka = AlKaboomConstants.MARKARIK_EZ;
		this.irekita = false;
	}

	@Override
	public String daukanMarka() {
		return irekita ? AlKaboomConstants.MARKARIK_EZ : this.marka;
	}

	@Override
	public int getZenbakia() {
		return 0;
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

	}

}
