package si.alkaboom.frontend.ranking;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.frontend.TaulaPanela;

public class RankingInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 6481265986429597754L;
	private final int xOffset = 40, yOffset = 40;

	public RankingInternalFrame(String izenburua) {
		super(izenburua, true, false, true, true);
		this.setLayout(new BorderLayout());
		TaulaPanela taula = new TaulaPanela(izenburua, "");
		this.add(taula, BorderLayout.CENTER);
		this.pack();
		setLocation(xOffset * AlKaboom.getAlKaboom().getUI().getRanking().getIrekitakoLeihoKop(),
				yOffset * AlKaboom.getAlKaboom().getUI().getRanking().getIrekitakoLeihoKop());
		this.setVisible(true);
	}
}
