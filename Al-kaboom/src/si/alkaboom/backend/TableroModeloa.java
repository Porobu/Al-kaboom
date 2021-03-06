package si.alkaboom.backend;

import java.util.Random;

import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaHuts;
import si.alkaboom.backend.laukia.LaukiaMina;
import si.alkaboom.backend.laukia.LaukiaZenb;

public class TableroModeloa {
	private static TableroModeloa gureTableroModeloa;

	public static TableroModeloa getTableroModeloa() {
		return gureTableroModeloa != null ? gureTableroModeloa : (gureTableroModeloa = new TableroModeloa());
	}

	private int minaKop;

	private DBOperazioak dbo;

	private boolean partidaGalduta;

	private ILaukia[][] tableroa;
	private int irekitakoLaukiak;
	private boolean partidaIrabazita;

	private TableroModeloa() {
		this.partidaIrabazita = false;
		this.partidaGalduta = false;
		this.irekitakoLaukiak = 0;
		this.dbo = new DBOperazioak();
	}

	public ILaukia getPos(int errenkada, int zutabea) {
		return tableroa[errenkada][zutabea];
	}

	public int[] getTamaina() {
		return new int[] { tableroa.length, tableroa[0].length };
	}

	public void hasieratu(int errenkadak, int zutabeak, int minak, int klikErrenkada, int klikZutabea) {
		this.minaKop = minak;
		this.partidaGalduta = false;
		tableroa = new ILaukia[errenkadak][zutabeak];
		tableroa[klikErrenkada][klikZutabea] = new LaukiaHuts();
		Random r = new Random();
		this.minakIpini(minak, errenkadak, zutabeak, r);
		this.lehenengoLaukiaKonpondu(klikErrenkada, klikZutabea);
		this.zenbakiakIpini(errenkadak, zutabeak);
		this.laukiHutsakIpini(errenkadak, zutabeak);
		this.laukiakIreki(klikErrenkada, klikZutabea);
	}

	private void irekitakoLaukiakKontatu() {
		irekitakoLaukiak = 0;
		for (int i = 0; i < tableroa.length; i++) {
			for (int j = 0; j < tableroa[0].length; j++) {
				if (tableroa[i][j].irekitaDago()) {
					irekitakoLaukiak++;
				}
			}
		}

	}

	public boolean isPartidaIrabazita() {
		return partidaIrabazita;
	}

	public void laukiakIreki(int errenkada, int zutabea) {
		if (tableroa[errenkada][zutabea].getClass().getSimpleName().toLowerCase().contains("mina")) {
			partidaGalduta = true;
			for (int i = 0; i < tableroa.length; i++) {
				for (int j = 0; j < tableroa[0].length; j++) {
					tableroa[i][j].markaIpini(AlKaboomConstants.MARKARIK_EZ);
					tableroa[i][j].laukiaIreki();
				}
			}
			return;
		}
		this.laukiakIrekiErrekurtsiboa(errenkada, zutabea);
		this.irekitakoLaukiakKontatu();
		if (this.irekitakoLaukiak == (tableroa.length * tableroa[0].length) - (this.minaKop)) {
			this.partidaIrabazita = true;
			DBOperazioak dbo = new DBOperazioak();
			dbo.partidaIrabazi();
		}
	}

	private void laukiakIrekiErrekurtsiboa(int errenkada, int zutabea) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				ILaukia l = tableroa[errenkada][zutabea];
				if (errenkada + i >= tableroa.length || zutabea + j >= tableroa[0].length || errenkada + i < 0
						|| zutabea + j < 0)
					;
				else {
					if (l.getClass().getSimpleName().equalsIgnoreCase("LaukiaMina"))
						return;
					else if (l.getClass().getSimpleName().equalsIgnoreCase("LaukiaZenb")) {
						l.laukiaIreki();
					} else {
						l.laukiaIreki();
						if (!tableroa[errenkada + i][zutabea + j].irekitaDago())
							laukiakIrekiErrekurtsiboa(errenkada + i, zutabea + j);
					}
				}
			}

		}

	}

	private void laukiHutsakIpini(int errenkadak, int zutabeak) {
		for (int i = 0; i < errenkadak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				if (tableroa[i][j] == null)
					tableroa[i][j] = new LaukiaHuts();
			}
		}
	}

	private void lehenengoLaukiaKonpondu(int klikErrenkada, int klikZutabea) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (klikErrenkada + i >= tableroa.length || klikZutabea + j >= tableroa[0].length
						|| klikErrenkada + i < 0 || klikZutabea + j < 0)
					;
				else {
					ILaukia l = tableroa[klikErrenkada + i][klikZutabea + j];
					if (l != null && l.getClass().getSimpleName().toLowerCase().contains("laukiamina"))
						tableroa[klikErrenkada][klikZutabea] = new LaukiaZenb();
				}
			}
		}
	}

	private void minakIpini(int minak, int errenkadak, int zutabeak, Random r) {
		for (int i = 0; i < minak; i++) {
			int a = r.nextInt(errenkadak), b = r.nextInt(zutabeak);
			if (tableroa[a][b] == null)
				tableroa[a][b] = new LaukiaMina();
			else
				i--;

		}
	}

	public void partidaGaldu() {
		this.partidaGalduta = true;
	}

	public boolean partidaGaldutaDago() {
		return this.partidaGalduta;
	}

	public void partidaGorde() {
		StringBuilder gordetzeko = new StringBuilder("");
		int errenkadak = tableroa.length;
		int zutabeak = tableroa[0].length;
		for (int i = 0; i < tableroa.length; i++) {
			for (int j = 0; j < tableroa[0].length; j++) {
				ILaukia l = tableroa[i][j];
				gordetzeko.append(l.getClass().getSimpleName());
				gordetzeko.append(l.getZenbakia());
				gordetzeko.append(l.daukanMarka());
				gordetzeko.append("-");
			}
		}
		dbo.partidaGorde(gordetzeko.toString(), errenkadak, zutabeak);
	}

	public void partidaKargatu() {
		this.partidaGalduta = false;
		this.partidaIrabazita = false;
		String[] datuak = dbo.partidaKargatu();
		String partida = datuak[0];
		int errenkadak = Integer.parseInt(datuak[1]);
		int zutabeak = Integer.parseInt(datuak[2]);
		AlKaboom.getAlKaboom().getErabiltzailea().getErlojua().setDenboraPartGordeta(0);
		AlKaboom.getAlKaboom().getErabiltzailea().getErlojua().setDenboraPartGordeta(Integer.parseInt(datuak[3]));
		minaKop = 0;
		String[] partidaLista = partida.split("-");
		tableroa = new ILaukia[errenkadak][zutabeak];
		int k = 0;
		for (int i = 0; i < tableroa.length; i++) {
			for (int j = 0; j < tableroa[0].length; j++) {
				String oraingoa = partidaLista[k];
				tableroa[i][j] = LaukiFaktoria.getLaukiFaktoria().laukiaEgindaLortu(oraingoa.substring(0, 10),
						Integer.parseInt(oraingoa.substring(10, 11)), oraingoa.substring(11, 12));
				if (tableroa[i][j].getClass().getSimpleName().equalsIgnoreCase("LaukiaMina"))
					minaKop++;
				k++;
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

	private void zenbakiakIpini(int errenkadak, int zutabeak) {
		for (int i = 0; i < errenkadak; i++) {
			for (int j = 0; j < zutabeak; j++) {
				if (tableroa[i][j] != null)
					if (tableroa[i][j].getClass().getName().contains("Mina"))
						this.zenbakiagehitu(i, j);
			}
		}
	}

}
