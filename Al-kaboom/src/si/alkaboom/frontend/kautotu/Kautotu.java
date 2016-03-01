package si.alkaboom.frontend.kautotu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import si.alkaboom.backend.AlKaboom;

public class Kautotu extends JPanel {
	private static final long serialVersionUID = -7323271088278163192L;
	private ZailtasunaPanela zailtasunaPanela;
	private KautotuPanela kautotuPanela;

	public Kautotu() {
		this.setLayout(new BorderLayout());
		this.kautotuPanela = new KautotuPanela();
		this.zailtasunaPanela = new ZailtasunaPanela();
		this.add(this.kautotuPanela, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(300, 140));
		this.setVisible(true);
	}

	public KautotuPanela getKautotuPanela() {
		return kautotuPanela;
	}

	public void kautotuIpini(boolean aldatu) {
		this.remove(zailtasunaPanela);
		this.add(kautotuPanela, BorderLayout.CENTER);
		if (aldatu)
			kautotuPanela.listaAldatu();
		this.revalidate();
		AlKaboom.getAlKaboom().getUI().tamainaAldatu();
		this.repaint();
	}

	public void zailtasunaIpini() {
		this.remove(kautotuPanela);
		this.add(zailtasunaPanela, BorderLayout.CENTER);
		this.revalidate();
		AlKaboom.getAlKaboom().getUI().tamainaAldatu();
		this.repaint();
	}
}
