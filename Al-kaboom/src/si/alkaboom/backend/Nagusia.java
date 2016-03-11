package si.alkaboom.backend;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Nagusia {

	public static void main(String[] args) {
		if (!System.getProperty("os.name").toLowerCase().contains("mac os x")) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {

			}
			if (UIManager.getLookAndFeel().getName().toLowerCase().contains("metal"))
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
				}
		}
		AlKaboom.getAlKaboom().jokatu();
	}
}