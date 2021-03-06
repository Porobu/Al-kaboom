package si.alkaboom.frontend.kautotu;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import si.alkaboom.backend.DBOperazioak;

public class KautotuTaulaModeloa extends AbstractTableModel {
	private static final long serialVersionUID = -6370469542217113325L;
	private ArrayList<String[]> datuak;
	private String[] izenak;

	public KautotuTaulaModeloa(String izena) {
		izenak = new String[] { "Erabiltzailearen izena", "Partida gordeta?", "Azkeneko data", "Irabazitako Partidak",
				"Galdutako Partidak" };
		DBOperazioak gureOP = new DBOperazioak();
		datuak = gureOP.erabiltzaileakBistaratu(izena);
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return this.getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		return izenak.length;
	}

	@Override
	public String getColumnName(int i) {
		return izenak[i];
	}

	@Override
	public int getRowCount() {
		return datuak.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return datuak.get(row)[col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
