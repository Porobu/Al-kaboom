package si.alkaboom.frontend.ranking;

import java.awt.Color;

import javax.swing.JDesktopPane;

public class RankingDesktopPanela extends JDesktopPane {
	private static final long serialVersionUID = 5480444946815340109L;
	private RankingInternalFrame[] lehioak;
	private final String[] izenburuak = new String[] { "Erreza", "Normala", "Zaila", "Custom" };

	private int irekitakoLeihoKop;

	public RankingDesktopPanela() {
		irekitakoLeihoKop = 4;
		lehioak = new RankingInternalFrame[4];
	}

	public int getIrekitakoLeihoKop() {
		return irekitakoLeihoKop;
	}

	public void hasieratu() {
		for (int i = 0; i < lehioak.length; i++) {
			RankingInternalFrame r = new RankingInternalFrame(izenburuak[i]);
			this.add(r);
			lehioak[i] = r;
			irekitakoLeihoKop--;
		}
		this.setBackground(new Color(230, 230, 230));
		this.setOpaque(true);
	}
}
