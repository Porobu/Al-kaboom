package si.alkaboom.backend;

import java.util.Random;

import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaHutsa;
import si.alkaboom.backend.laukia.LaukiaMina;
import si.alkaboom.backend.laukia.LaukiaZenb;

public class TableroModeloa {
	private static TableroModeloa gureTableroModeloa;

	public static TableroModeloa getTableroModeloa() {
		return gureTableroModeloa != null ? gureTableroModeloa : (gureTableroModeloa = new TableroModeloa());
	}

	private boolean partidaGalduta;

	private ILaukia[][] tableroa;

	private TableroModeloa() {
		this.partidaGalduta = false;
	}

	public ILaukia getPos(int errenkada, int zutabea) {
		return tableroa[errenkada][zutabea];
	}

	public void hasieratu(int errenkadak, int zutabeak, int minak, int klikErrenkada, int klikZutabea) {
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

	public void partidaGaldu() {
		this.partidaGalduta = true;
	}

	public void partidaGorde() {
		StringBuilder gordetzeko = new StringBuilder("");
		int errenkadak = tableroa.length;
		int zutabeak = tableroa[0].length;
		for (int i = 0; i < tableroa.length; i++) {
			for (int j = 0; j < tableroa[0].length; j++) {
				if (tableroa[i][j].getClass().getSimpleName().contains("Zenb")) {
					LaukiaZenb l = (LaukiaZenb) tableroa[i][j];
					gordetzeko.append(l.getClass().getSimpleName());
					gordetzeko.append(l.getZenbakia());

				} else
					gordetzeko.append(tableroa[i][j].getClass().getSimpleName());
				gordetzeko.append(tableroa[i][j].daukanMarka());
				gordetzeko.append("-");
			}
		}
		DBOperazioak dbo = new DBOperazioak();
		dbo.partidaGorde(gordetzeko.toString(), errenkadak, zutabeak);
	}

	public void partidaKargatu() {
		// TODO
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
