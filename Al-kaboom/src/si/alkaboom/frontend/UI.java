package si.alkaboom.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.frontend.kautotu.Kautotu;

public class UI extends JFrame {
	private static final long serialVersionUID = 6902848848036097903L;
	private Kautotu kautotu;

	public UI() {
		this.setLayout(new BorderLayout());
		this.setTitle(AlKaboomConstants.IZENBURUA);
	}

	public void dekorazioGabeHasieratu() {
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void alKaboomHasieratu() {
		this.remove(kautotu);
		this.setTitle(AlKaboomConstants.IZENBURUA + " - " + AlKaboom.getAlKaboom().getErabiltzailea());
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new GureWindowListener());
		this.revalidate();
		this.pack();
		this.repaint();
	}

	public Kautotu getKautotu() {
		return kautotu;
	}

	public void kautotuHasieratu() {
		this.dispose();
		this.setUndecorated(false);
		kautotu = new Kautotu();
		this.setMinimumSize(new Dimension(280, 140));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(kautotu, BorderLayout.CENTER);
		this.setTitle(AlKaboomConstants.IZENBURUA + " - " + AlKaboom.getAlKaboom().getDatubasePath());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void tamainaAldatu() {
		this.pack();
	}
}
