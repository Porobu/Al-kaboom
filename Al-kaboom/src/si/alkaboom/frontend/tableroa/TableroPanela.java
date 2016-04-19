package si.alkaboom.frontend.tableroa;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.TableroModeloa;
import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaZenb;

public class TableroPanela extends JPanel implements MouseListener {
	private static final long serialVersionUID = -6744712728807455322L;

	private static int[] zailtasunaAukeratu(String zailtasuna) {
		switch (zailtasuna.toLowerCase()) {
		case "erreza":
			return AlKaboomConstants.ERREZA;
		case "normala":
			return AlKaboomConstants.NORMALA;
		case "zaila":
			return AlKaboomConstants.ZAILA;
		default:
			break;
		}
		return new int[] {};
	}

	private boolean hasiera;
	private TableroModeloa tm;

	private int minaKop;

	private AKLaukia[][] laukiak;

	public TableroPanela(int errenkadak, int zutabeak, int minak) {
		hasiera = true;
		minaKop = minak;
		this.setLayout(new GridLayout(errenkadak, zutabeak));
		laukiak = new AKLaukia[errenkadak][zutabeak];
		for (int i = 0; i < errenkadak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				AKLaukia l = new AKLaukia();
				l.setName(i + "," + j);
				l.addMouseListener(this);
				this.add(l);
				laukiak[i][j] = l;
			}
		}
		this.setOpaque(true);
	}

	public TableroPanela(String mota) {
		this(zailtasunaAukeratu(mota)[0], zailtasunaAukeratu(mota)[1], zailtasunaAukeratu(mota)[2]);
	}

	private void laukiakIpini(String hutsa) {
		String[] posizioa = hutsa.split(",");
		tm = TableroModeloa.getTableroModeloa();
		hasiera = false;
		tm.hasieratu(laukiak.length, laukiak[0].length, minaKop, Integer.parseInt(posizioa[0]),
				Integer.parseInt(posizioa[1]));
		laukiak[Integer.parseInt(posizioa[0])][Integer.parseInt(posizioa[1])].setEnabled(false);
		for (int i = 0; i < laukiak.length; i++) {
			for (int j = 0; j < laukiak[0].length; j++) {
				ILaukia oraingoa = tm.getPos(i, j);
				String klasea = oraingoa.getClass().getSimpleName();
				switch (klasea.toLowerCase()) {
				case "laukiahutsa":
					laukiak[i][j].setText("H");
					break;
				case "laukiamina":
					laukiak[i][j].setText("M");
					break;
				case "laukiazenb":
					LaukiaZenb l = (LaukiaZenb) oraingoa;
					laukiak[i][j].setText(String.valueOf(l.getZenbakia()));
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		AKLaukia l = (AKLaukia) e.getSource();
		if (hasiera) {
			hasiera = false;
			this.laukiakIpini(l.getName());
		} else
			;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
