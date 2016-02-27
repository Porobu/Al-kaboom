package si.alkaboom.frontend;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import si.alkaboom.backend.AlKaboomConstants;

public class UI extends JFrame {
	private static final long serialVersionUID = 6902848848036097903L;

	public UI() {
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new GureWindowListener());
		this.setLayout(new BorderLayout());
		this.setTitle(AlKaboomConstants.IZENBURUA);
		this.pack();
		this.setVisible(true);
	}
}
