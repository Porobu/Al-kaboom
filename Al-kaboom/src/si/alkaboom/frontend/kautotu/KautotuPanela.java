package si.alkaboom.frontend.kautotu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import si.alkaboom.frontend.TaulaPanela;

public class KautotuPanela extends JPanel {
	private static final long serialVersionUID = 4842340182997453654L;
	private KautotuSartzekoPanela ksPanela;
	private final String KAUTOTU = "Kautotu";

	private TaulaPanela taula;

	public KautotuPanela() {
		this.setLayout(new BorderLayout());
		ksPanela = new KautotuSartzekoPanela();
		this.add(ksPanela, BorderLayout.PAGE_START);
		taula = new TaulaPanela(KAUTOTU, "");
		this.add(taula, BorderLayout.CENTER);
	}

	public KautotuSartzekoPanela getKsPanela() {
		return ksPanela;
	}

	public void listaAldatu() {
		ksPanela.listaAldatu();
	}

	public void taulaEguneratu(String izenBerria) {
		this.remove(taula);
		taula = new TaulaPanela(KAUTOTU, izenBerria);
		this.add(taula, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

}
