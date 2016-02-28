package si.alkaboom.frontend;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import si.alkaboom.externals.ZebraJTable;
import si.alkaboom.frontend.kautotu.KautotuTaulaModeloa;
import si.alkaboom.frontend.ranking.RankingTaulaModeloa;

public class TaulaPanela extends JPanel {
	private static final long serialVersionUID = -3255352445669446532L;

	public TaulaPanela(String mota, String izena) {
		this.setLayout(new BorderLayout());
		AbstractTableModel gureModeloa = null;
		if (mota.equalsIgnoreCase("Kautotu"))
			gureModeloa = new KautotuTaulaModeloa(izena);
		else
			gureModeloa = new RankingTaulaModeloa();
		JTable gureTaula = new ZebraJTable(gureModeloa);
		gureTaula.setRowSorter(new TableRowSorter<>(gureModeloa));
		JScrollPane gureJScrollPane = new JScrollPane(gureTaula);
		gureJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gureJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(gureJScrollPane, BorderLayout.CENTER);
	}
}
