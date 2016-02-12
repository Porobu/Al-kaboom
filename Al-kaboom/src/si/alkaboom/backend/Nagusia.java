package si.alkaboom.backend;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Nagusia {
	public static final float BERTSIOA = 0.0F;

	public static void main(String[] args) {
		if (!System.getProperty("os.name").contains("OS X"))
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
