package si.alkaboom.frontend.tableroa;

import java.awt.GridLayout;

import javax.swing.JPanel;

import si.alkaboom.backend.AlKaboomConstants;

public class TableroPanela extends JPanel {
	private static final long serialVersionUID = -6744712728807455322L;

	private static int[] zailtasunaAukeratu(String zailtasuna) {
		switch (zailtasuna.toLowerCase()) {
		case "erreza":
			return AlKaboomConstants.ERREZA;
		case "normala":
			return AlKaboomConstants.NORMALA;
		case "zaila":
			return AlKaboomConstants.ZAILA;
		default:
			break;
		}
		return new int[] {};
	}

	private AKLaukia[][] laukiak;

	public TableroPanela(int errenkadak, int zutabeak, int minak) {
		this.setLayout(new GridLayout(errenkadak, zutabeak));
		laukiak = new AKLaukia[errenkadak][zutabeak];
		for (int i = 0; i < errenkadak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				AKLaukia l = new AKLaukia();
				this.add(l);
				laukiak[i][j] = l;
			}
		}
		this.setOpaque(true);
	}

	public TableroPanela(String mota) {
		this(zailtasunaAukeratu(mota)[0], zailtasunaAukeratu(mota)[1], zailtasunaAukeratu(mota)[2]);
	}
}
