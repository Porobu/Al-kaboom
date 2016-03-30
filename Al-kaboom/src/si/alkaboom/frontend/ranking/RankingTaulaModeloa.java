package si.alkaboom.frontend.ranking;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import si.alkaboom.backend.Operazioak;

public class RankingTaulaModeloa extends AbstractTableModel {

	private static final long serialVersionUID = 4361943585330570345L;
	private String[] izenak;
	private ArrayList<String[]> datuak;
	
	public RankingTaulaModeloa(String zailtasuna) {
		izenak = new String[] { "Erabiltzailearen izena", "Puntuak", "Denbora" };
		datuak = new Operazioak().erabiltzaileenPuntuazioakLortu(zailtasuna);
	}

	@Override
	public int getColumnCount() {
		return izenak.length;
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
	public Class<String> getColumnClass(int c) {
		return String.class;
	}
	
	@Override
	public String getColumnName(int i) {
		return izenak[i];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
