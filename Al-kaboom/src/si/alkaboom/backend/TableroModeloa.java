package si.alkaboom.backend;

import java.util.Random;

import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaHutsa;
import si.alkaboom.backend.laukia.LaukiaMina;
import si.alkaboom.backend.laukia.LaukiaZenb;

public class TableroModeloa {
	private ILaukia[][] tableroa;

	public TableroModeloa(int errenkadak, int zutabeak, int minak, int klikErrenkada, int klikZutabea) {
		tableroa = new ILaukia[errenkadak][zutabeak];
		tableroa[klikErrenkada][klikZutabea] = new LaukiaHutsa();
		Random r = new Random();
		for (int i = 0; i < minak; i++) {
			int a = r.nextInt(errenkadak), b = r.nextInt(zutabeak);
			if (tableroa[a][b] == null) {
				tableroa[a][b] = new LaukiaMina();
			} else {
				i--;
			}
		}
		for (int i = 0; i < errenkadak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				if (tableroa[i][j] != null)
					if (tableroa[i][j].getClass().getName().contains("Mina")) {
						this.zenbakiagehitu(i, j);
					}

			}
		}
		for (int i = 0; i < errenkadak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				if (tableroa[i][j] == null) {
					tableroa[i][j] = new LaukiaHutsa();
				}
			}
		}
	}

	private void zenbakiagehitu(int errenkada, int zutabea) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (errenkada + i >= tableroa.length || zutabea + j >= tableroa[0].length || errenkada + i < 0
						|| zutabea + j < 0)
					;
				else {
					if (tableroa[errenkada + i][zutabea + j] == null) {
						tableroa[errenkada + i][zutabea + j] = new LaukiaZenb();
					} else if (tableroa[errenkada + i][zutabea + j].getClass().getName().contains("Zenb")) {
						LaukiaZenb lz = (LaukiaZenb) tableroa[errenkada + i][zutabea + j];
						lz.zenbPlus();
					}
				}
			}
		}
	}

}
