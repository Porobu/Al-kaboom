package si.alkaboom.frontend.ranking;

import javax.swing.JDesktopPane;

public class RankingDesktopPanela extends JDesktopPane {
	private static final long serialVersionUID = 5480444946815340109L;
	private RankingInternalFrame[] lehioak;
	private final String[] izenburuak = new String[]{"Erreza", "Normala", "Zaila", "Custom"};

	public RankingDesktopPanela() {
		lehioak = new RankingInternalFrame[4];
		for (int i = 0; i < lehioak.length; i++) {
			RankingInternalFrame r = new RankingInternalFrame(izenburuak[i]);
			this.add(r);
			lehioak[i] = r;
		}
		this.setOpaque(true);
	}
}
