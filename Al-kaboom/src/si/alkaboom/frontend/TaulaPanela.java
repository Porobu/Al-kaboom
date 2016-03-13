package si.alkaboom.frontend;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.externals.ZebraJTable;
import si.alkaboom.frontend.kautotu.KautotuTaulaModeloa;
import si.alkaboom.frontend.ranking.RankingTaulaModeloa;

public class TaulaPanela extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = -3255352445669446532L;
	private ZebraJTable gureTaula;
	private JScrollPane gureJScrollPane;
	private AbstractTableModel gureModeloa;

	public TaulaPanela(String mota, String izena) {
		this.setLayout(new BorderLayout());
		if (mota.equalsIgnoreCase("Kautotu"))
			gureModeloa = new KautotuTaulaModeloa(izena);
		else
			gureModeloa = new RankingTaulaModeloa();
		gureTaula = new ZebraJTable(gureModeloa);
		gureTaula.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = gureTaula.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		cellSelectionModel.addListSelectionListener(this);
		gureTaula.setRowSorter(new TableRowSorter<>(gureModeloa));
		gureJScrollPane = new JScrollPane(gureTaula);
		gureJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gureJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(gureJScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (gureModeloa.getClass().getSimpleName().contains("Kautotu")) {
			String izena = (String) gureModeloa.getValueAt(gureTaula.getSelectedRow(), 0);
			AlKaboom.getAlKaboom().getUI().getKautotu().getKautotuPanela().getKSPanela().izenaAldatu(izena);
		}

	}

}
