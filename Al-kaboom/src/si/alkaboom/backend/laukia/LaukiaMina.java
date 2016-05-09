package si.alkaboom.backend.laukia;

import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.TableroModeloa;

public class LaukiaMina implements ILaukia {

	private String marka;
	private boolean irekita;

	public LaukiaMina() {
		this.marka = AlKaboomConstants.MARKARIK_EZ;
		this.irekita = false;
	}

	@Override
	public String daukanMarka() {
		return irekita ? AlKaboomConstants.IREKITA : this.marka;
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
		TableroModeloa.getTableroModeloa().partidaGaldu();
	}

	@Override
	public void markaIpini(String marka) {
		this.marka = marka;

	}

	@Override
	public void setZenbakia(int berria) {

	}

}
