package si.alkaboom.frontend.tableroa;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.backend.DBOperazioak;
import si.alkaboom.backend.TableroModeloa;
import si.alkaboom.backend.laukia.LaukiaZenb;

public class TableroPanela extends JPanel implements MouseListener {
	public boolean isHasiera() {
		return hasiera;
	}

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
			return new int[] {};
		}
	}

	private boolean pausa;

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
		this.pausa = false;
	}

	public TableroPanela(String mota) {
		this(zailtasunaAukeratu(mota)[0], zailtasunaAukeratu(mota)[1], zailtasunaAukeratu(mota)[2]);
	}

	public boolean isPausa() {
		return pausa;
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
				this.motaIpini(i, j, false);
			}
		}
	}

	public void listaHasieratu(int errenkadak, int zutabeak) {
		this.hasiera = false;
		this.removeAll();
		this.setLayout(new GridLayout(errenkadak, zutabeak));
		laukiak = new AKLaukia[errenkadak][zutabeak];
		for (int i = 0; i < laukiak.length; i++) {
			for (int j = 0; j < laukiak[0].length; j++) {
				laukiak[i][j] = new AKLaukia();
				laukiak[i][j].addMouseListener(this);
				laukiak[i][j].setName(i + "," + j);
				this.add(laukiak[i][j]);
			}
		}
		this.tableroaEguneratu();
		this.markaIrakurri();
		this.revalidate();
		this.repaint();
	}

	private void motaIpini(int i, int j, boolean ezer) {
		if (ezer) {
			laukiak[i][j].setText("");
			return;
		}
		String klasea = TableroModeloa.getTableroModeloa().getPos(i, j).getClass().getSimpleName();
		if (TableroModeloa.getTableroModeloa().getPos(i, j).irekitaDago())
			switch (klasea.toLowerCase()) {
			case "laukiahuts":
				laukiak[i][j].setText("");
				break;
			case "laukiamina":
				laukiak[i][j].setText("M");
				break;
			case "laukiazenb":
				LaukiaZenb l = (LaukiaZenb) TableroModeloa.getTableroModeloa().getPos(i, j);
				laukiak[i][j].setText(String.valueOf(l.getZenbakia()));
				break;
			default:
				break;
			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.pausa) {
			this.pausatutaMezua();
			return;
		}
		AKLaukia l = (AKLaukia) e.getSource();
		String[] pos = l.getName().split(",");
		int errenkada = Integer.parseInt(pos[0]);
		int zutabea = Integer.parseInt(pos[1]);
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (!hasiera && !TableroModeloa.getTableroModeloa().getPos(errenkada, zutabea).daukanMarka()
					.equals(AlKaboomConstants.MARKARIK_EZ))
				return;
			if (hasiera) {
				hasiera = false;
				this.laukiakIpini(l.getName());
			} else
				TableroModeloa.getTableroModeloa().laukiakIreki(errenkada, zutabea);
			if (TableroModeloa.getTableroModeloa().partidaGaldutaDago())
				this.partidaGaldu();
			else if (TableroModeloa.getTableroModeloa().isPartidaIrabazita()) {
				this.partidaIrabazi();
			}
			this.tableroaEguneratu();
		} else {
			this.motaIpini(errenkada, zutabea, true);
			this.markaErabili(errenkada, zutabea, l);
		}

	}

	private void markaErabili(int errenkada, int zutabea, AKLaukia l) {
		l.setText("");
		switch (TableroModeloa.getTableroModeloa().getPos(errenkada, zutabea).daukanMarka()) {
		case AlKaboomConstants.MARKARIK_EZ:
			l.setText(l.getText() + " " + AlKaboomConstants.BANDERITA);
			TableroModeloa.getTableroModeloa().getPos(errenkada, zutabea).markaIpini(AlKaboomConstants.BANDERITA);
			break;
		case AlKaboomConstants.BANDERITA:
			l.setText(l.getText() + " " + AlKaboomConstants.GALDERA);
			TableroModeloa.getTableroModeloa().getPos(errenkada, zutabea).markaIpini(AlKaboomConstants.GALDERA);
			break;
		default:
			l.setText(l.getText());
			TableroModeloa.getTableroModeloa().getPos(errenkada, zutabea).markaIpini(AlKaboomConstants.MARKARIK_EZ);
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private void partidaGaldu() {
		for (int i = 0; i < laukiak.length; i++)
			for (int j = 0; j < laukiak[0].length; j++)
				laukiak[i][j].setEnabled(false);
		DBOperazioak dbo = new DBOperazioak();
		dbo.partidaGaldu();
		JOptionPane.showMessageDialog(this, "Partida galdu duzu!", AlKaboomConstants.IZENBURUA,
				JOptionPane.ERROR_MESSAGE);
	}

	private void partidaIrabazi() {
		for (int i = 0; i < laukiak.length; i++)
			for (int j = 0; j < laukiak[0].length; j++)
				laukiak[i][j].setEnabled(false);
		JOptionPane.showMessageDialog(this, "Zorionak! Partida irabazi duzu!", AlKaboomConstants.IZENBURUA,
				JOptionPane.INFORMATION_MESSAGE);

	}

	private void pausatutaMezua() {
		JOptionPane.showMessageDialog(this, "Partida pausan dago", AlKaboomConstants.IZENBURUA,
				JOptionPane.WARNING_MESSAGE);

	}

	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}

	public void tableroaEguneratu() {
		for (int i = 0; i < laukiak.length; i++) {
			for (int j = 0; j < laukiak[0].length; j++) {
				if (TableroModeloa.getTableroModeloa().getPos(i, j).irekitaDago())
					laukiak[i][j].setEnabled(false);
				this.motaIpini(i, j, false);
			}
		}
	}

	private void markaIrakurri() {
		for (int i = 0; i < laukiak.length; i++) {
			for (int j = 0; j < laukiak[0].length; j++) {
				if (!TableroModeloa.getTableroModeloa().getPos(i, j).irekitaDago())
					switch (TableroModeloa.getTableroModeloa().getPos(i, j).daukanMarka()) {
					case AlKaboomConstants.GALDERA:
						laukiak[i][j].setText(laukiak[i][j].getText() + " " + AlKaboomConstants.GALDERA);
						break;
					case AlKaboomConstants.BANDERITA:
						laukiak[i][j].setText(laukiak[i][j].getText() + " " + AlKaboomConstants.BANDERITA);
						break;
					default:
						break;
					}
			}
		}

	}

}
