package si.alkaboom.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import si.alkaboom.backend.AlKaboomConstants;

public class Kautotu extends JFrame {
	private static final long serialVersionUID = -7323271088278163192L;
	private ZailtasunaPanela zailtasunaPanela;
	private KautotuPanela kautotuPanela;

	public Kautotu() {
		this.setTitle(AlKaboomConstants.IZENBURUA);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.kautotuPanela = new KautotuPanela();
		this.zailtasunaPanela = new ZailtasunaPanela();
		this.add(this.kautotuPanela, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(280, 110));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void kautotuIpini(boolean aldatu) {
		this.remove(zailtasunaPanela);
		this.add(kautotuPanela, BorderLayout.CENTER);
		if (aldatu)
			kautotuPanela.listaAldatu();
		this.revalidate();
		this.pack();
		this.repaint();
	}

	public void zailtasunaIpini() {
		this.remove(kautotuPanela);
		this.add(zailtasunaPanela, BorderLayout.CENTER);
		this.revalidate();
		this.pack();
		this.repaint();
	}
}
