package si.alkaboom.frontend.tableroa;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.frontend.PartidaMezuGrafikoak;

public class PartidaMenuBarra extends JMenuBar {
	private static final long serialVersionUID = 6278678590130942938L;

	private JMenu fitxategia, partida, gehiago, ranking;
	private JMenuItem partidaBerria, partidaKargatu, partidaGorde, itxi, pausa, partidaBertanBehera, laguntza,
			alKaboomBuruz, alKaboomWeb, rankingBistaratu;
	private PartidaMezuGrafikoak pmg;

	public PartidaMenuBarra() {
		pmg = new PartidaMezuGrafikoak();
		this.fitxategiMenuaEraiki();
		this.partidaMenuaEraiki();
		this.rankingMenuaEraiki();
		this.gehiagoMenuaEraiki();
		this.add(fitxategia);
		this.add(partida);
		this.add(ranking);
		this.add(gehiago);
	}

	private void fitxategiMenuaEraiki() {
		fitxategia = new JMenu("Fitxategia");
		partidaBerria = new JMenuItem("Partida berria");
		itxi = new JMenuItem("Programa itxi");
		itxi.addActionListener(gureAE -> this.programaItxi());
		fitxategia.add(partidaBerria);
		fitxategia.add(itxi);
	}

	private void gehiagoMenuaEraiki() {
		gehiago = new JMenu("Gehiago");
		laguntza = new JMenuItem("Laguntza");
		alKaboomBuruz = new JMenuItem("AlKaboom-eri buruz");
		alKaboomWeb = new JMenuItem("AlKaboom-eri buruz interneten");
		gehiago.add(laguntza);
		gehiago.add(alKaboomBuruz);
		gehiago.add(alKaboomWeb);
	}

	private void menuaAldatu() {
		if (rankingBistaratu.getText().equalsIgnoreCase("Ranking Bistaratu")) {
			rankingBistaratu.setText("Rankingetik Itzuli");
			AlKaboom.getAlKaboom().getUI().rankingIpini();
		} else {
			rankingBistaratu.setText("Ranking Bistaratu");
			AlKaboom.getAlKaboom().getUI().rankingItxi();
		}
	}

	private void partidaMenuaEraiki() {
		partida = new JMenu("Partida");
		partidaKargatu = new JMenuItem("Partida bat kargatu");
		partidaKargatu.addActionListener(gureAE -> pmg.partidaKargatu());
		partidaGorde = new JMenuItem("Partida gorde");
		partidaGorde.addActionListener(gureAE -> pmg.patidaGorde());
		pausa = new JMenuItem("Pausa");
		partidaBertanBehera = new JMenuItem("Partida bertan ehera utzi");
		partida.add(partidaKargatu);
		partida.add(partidaGorde);
		partida.add(pausa);
		partida.add(partidaBertanBehera);
	}

	private void rankingMenuaEraiki() {
		this.ranking = new JMenu("Ranking");
		rankingBistaratu = new JMenuItem("Ranking Bistaratu");
		rankingBistaratu.addActionListener(gureAE -> this.menuaAldatu());
		ranking.add(rankingBistaratu);
	}

	private void programaItxi() {
		int aukera = JOptionPane.showConfirmDialog(AlKaboom.getAlKaboom().getUI(), "Al-Kaboom itxi nahi duzu?\nGorde ez den partida galduko da",
				AlKaboomConstants.IZENBURUA, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(aukera == JOptionPane.YES_OPTION)
			System.exit(0);
	}

}
