package si.alkaboom.frontend.kautotu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import si.alkaboom.frontend.TaulaPanela;

public class KautotuPanela extends JPanel {
	private static final long serialVersionUID = 4842340182997453654L;
	private KautotuSartzekoPanela ksPanela;

	private TaulaPanela taula;

	public KautotuPanela() {
		this.setLayout(new BorderLayout());
		ksPanela = new KautotuSartzekoPanela();
		this.add(ksPanela, BorderLayout.PAGE_START);
		taula = new TaulaPanela("Kautotu", "");
		this.add(taula, BorderLayout.CENTER);
	}

	public void listaAldatu() {
		ksPanela.listaAldatu();
	}

}
