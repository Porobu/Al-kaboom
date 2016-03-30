package si.alkaboom.frontend.ranking;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

import si.alkaboom.frontend.TaulaPanela;

public class RankingInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 6481265986429597754L;

	public RankingInternalFrame(String izenburua) {
		super(izenburua, true, false, true, true);
		this.setLayout(new BorderLayout());
		TaulaPanela taula = new TaulaPanela(izenburua, "");
		this.add(taula, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
}
