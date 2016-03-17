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
		ksPanela.setName("Kautotu Sartzeko Panela");
		this.add(ksPanela, BorderLayout.PAGE_START);
		taula = new TaulaPanela(KAUTOTU, "");
		taula.setName("Taula Panela");
		this.add(taula, BorderLayout.CENTER);
	}

	public KautotuSartzekoPanela getKSPanela() {
		return ksPanela;
	}

	public void listaAldatu() {
		ksPanela.listaAldatu();
	}

	public void taulaEguneratu(String izenBerria) {
		this.remove(taula);
		taula = new TaulaPanela(KAUTOTU, izenBerria);
		taula.setName("Taula Panela");
		this.add(taula, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

}
