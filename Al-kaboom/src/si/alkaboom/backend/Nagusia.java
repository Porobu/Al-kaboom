package si.alkaboom.backend;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import si.alkaboom.frontend.Kautotu;

public class Nagusia {
	private static Kautotu kautotu;
	public static final float BERTSIOA = 0.05F;
	public static final String IZENBURUA = "Al-Kaboom " + BERTSIOA;

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
		kautotu = new Kautotu();
	}

	public static Kautotu getKautotu() {
		return kautotu;
	}
}
