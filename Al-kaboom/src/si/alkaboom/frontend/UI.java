package si.alkaboom.frontend;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class UI extends JFrame {
	private static final long serialVersionUID = 6902848848036097903L;
	public UI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("Al-Kaboom");
		this.pack();
		this.setVisible(true);
	}
}
