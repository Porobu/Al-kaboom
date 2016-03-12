package si.alkaboom.frontend.kautotu;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import si.alkaboom.backend.Operazioak;

public class KautotuTaulaModeloa extends AbstractTableModel {
	private static final long serialVersionUID = -6370469542217113325L;
	private ArrayList<String[]> datuak;
	private String[] izenak;

	public KautotuTaulaModeloa(String izena) {
		izenak = new String[] { "Erabiltzailearen izena", "Partida gordeta?", "Azkeneko data" };
		Operazioak gureOP = new Operazioak();
		datuak = gureOP.erabiltzaileakBistaratu(izena);
	}

	@Override
	public Class<String> getColumnClass(int c) {
		return String.class;
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
