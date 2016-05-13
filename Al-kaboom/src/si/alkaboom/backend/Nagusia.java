package si.alkaboom.backend;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Nagusia {

	public static void main(String[] args) {
		try {
			if (!System.getProperty("os.name").toLowerCase().contains("os x"))
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			if (UIManager.getLookAndFeel().getName().toLowerCase().contains("metal"))
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
		AlKaboom.getAlKaboom().jokatu();
	}
}