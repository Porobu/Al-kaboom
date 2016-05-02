package si.alkaboom.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.Erabiltzailea;
import si.alkaboom.frontend.kautotu.Kautotu;
import si.alkaboom.frontend.ranking.RankingDesktopPanela;
import si.alkaboom.frontend.tableroa.PartidaMenuBarra;
import si.alkaboom.frontend.tableroa.TableroPanela;

public class UI extends JFrame {
	public TableroPanela getTp() {
		return tp;
	}

	private static final long serialVersionUID = 6902848848036097903L;
	private Kautotu kautotu;
	private PartidaMenuBarra menuBarraAK;
	private TableroPanela tp;
	private RankingDesktopPanela ranking;

	public UI() {
		this.setLayout(new BorderLayout());
		this.setTitle(AlKaboomConstants.IZENBURUA);
	}

	public void alKaboomHasieratu() {
		this.remove(kautotu);
		this.setTitle(AlKaboomConstants.IZENBURUA + " - " + AlKaboom.getAlKaboom().getErabiltzailea().getIzena());
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new GureWindowListener());
		this.menuBarraAK = new PartidaMenuBarra();
		this.setJMenuBar(menuBarraAK);
		Erabiltzailea e = AlKaboom.getAlKaboom().getErabiltzailea();
		if (e.getZailtasuna().equalsIgnoreCase("Custom"))
			this.tp = new TableroPanela(e.getErrenkadak(), e.getZutabeak(), e.getMinak());
		else
			this.tp = new TableroPanela(e.getZailtasuna());
		this.add(tp);
		this.ranking = new RankingDesktopPanela();
		this.ranking.hasieratu();
		this.revalidate();
		this.setMinimumSize(new Dimension(AlKaboomConstants.TABLERO_TAMAINA_HORIZONTALA,
				AlKaboomConstants.TABLERO_TAMAINA_BERTIKALA));
		this.repaint();
	}

	public void dekorazioGabeHasieratu() {
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Kautotu getKautotu() {
		return kautotu;
	}

	public RankingDesktopPanela getRanking() {
		return ranking;
	}

	public void kautotuHasieratu() {
		this.dispose();
		this.setUndecorated(false);
		kautotu = new Kautotu();
		kautotu.setName("Kautotu Panela");
		this.setMinimumSize(new Dimension(280, 140));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(kautotu, BorderLayout.CENTER);
		this.setTitle(AlKaboomConstants.IZENBURUA + " - " + AlKaboom.getAlKaboom().getDatubasePath());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void rankingIpini() {
		this.remove(tp);
		this.add(ranking);
		this.setMinimumSize(new Dimension(AlKaboomConstants.TABLERO_TAMAINA_HORIZONTALA,
				AlKaboomConstants.TABLERO_TAMAINA_BERTIKALA));
		this.revalidate();
		this.repaint();
	}

	public void rankingItxi() {
		this.remove(ranking);
		this.add(tp);
		this.revalidate();
		this.repaint();
	}

	public void tamainaAldatu() {
		this.pack();
	}

}
