package si.alkaboom.frontend;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class NagusiaUI extends JFrame {
	private static final long serialVersionUID = 6902848848036097903L;
	public NagusiaUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("Al-Kaboom");
		this.pack();
		this.setVisible(true);
	}
}
