package si.alkaboom.frontend;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Laguntza extends JPanel {
	private JTextArea textuaArea;

	private static final long serialVersionUID = -5349220117486715768L;

	public Laguntza() {
		String laguntza = "Al-Kaboom jokora jolastea oso erraza da. Alde batetik, minak dauzkagu, beste alde batetik zenbakiak, eta zenbaki horiek, euren ondoan dauden mina kopurua zenbatekoa den esaten digute. Alde batetik, mina batean klikatzen baduzu, jokoa amaituko da, eta galduko duzu. Jokoa irabazteko, mina guztiak markatu behar dituzu. Markatzeko, saguaren eskumako botoiarekin klikatu behar duzu, mina bat dagoela uste baduzu. Minarik ez dagoela antzematen duzunean, ezkerreko botoiarekin sakatu behar izango duzu, eta zenbaki bat edo lauki huts bat agertuko zaizu. Zenbaki bat bada, lehen esandakoaren arabera, ondoan dauden mina kopurua esango digu.";
		this.setLayout(new BorderLayout());
		this.textuaArea = new JTextArea(laguntza);
		textuaArea.setLineWrap(true);
		textuaArea.setWrapStyleWord(true);
		textuaArea.setEditable(false);
		textuaArea.setOpaque(true);
		textuaArea.setBackground(new Color(240, 240, 240));
		JScrollPane sp = new JScrollPane(textuaArea);
		this.add(sp, BorderLayout.CENTER);
		this.setOpaque(true);
	}
}
