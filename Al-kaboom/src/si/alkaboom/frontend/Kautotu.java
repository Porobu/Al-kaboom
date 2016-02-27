package si.alkaboom.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import javax.swing.JPanel;

import si.alkaboom.backend.Nagusia;

public class Kautotu extends JFrame {
	private JPanel zailtasunaPanela;
	private JPanel kautotuPanela;
	private static final long serialVersionUID = -7323271088278163192L;

	public Kautotu() {
		this.setTitle(Nagusia.IZENBURUA);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.kautotuPanela = new KautotuPanela();
		this.zailtasunaPanela = new ZailtasunaPanela();
		this.add(this.kautotuPanela, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(300, 60));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void zailtasunaIpini() {
		this.remove(kautotuPanela);
		this.add(zailtasunaPanela, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();

	}

	public void kautotuIpini() {
		this.remove(zailtasunaPanela);
		this.add(kautotuPanela, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();

	}

}
