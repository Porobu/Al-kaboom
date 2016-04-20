package si.alkaboom.frontend.ranking;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import si.alkaboom.backend.DBOperazioak;

public class RankingTaulaModeloa extends AbstractTableModel {

	private static final long serialVersionUID = 4361943585330570345L;
	private String[] izenak;
	private ArrayList<String[]> datuak;

	public RankingTaulaModeloa(String zailtasuna) {
		izenak = new String[] { "Erabiltzailearen izena", "Puntuak", "Denbora" };
		datuak = new DBOperazioak().erabiltzaileenPuntuazioakLortu(zailtasuna);
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
	public Object getValueAt(int arg0, int arg1) {
		return datuak.get(arg0)[arg1];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
