package si.alkaboom.backend.laukia;

import si.alkaboom.backend.AlKaboomConstants;

public class LaukiaHutsa implements ILaukia {
	private String marka;
	private boolean irekita;

	public LaukiaHutsa() {
		this.marka = AlKaboomConstants.MARKARIK_EZ;
		this.irekita = false;
	}

	@Override
	public String daukanMarka() {
		return irekita ? AlKaboomConstants.MARKARIK_EZ : this.marka;
	}

	@Override
	public boolean irekitaDago() {
		return irekita;
	}

	public void laukiaIreki() {
		irekita = true;
	}

}
