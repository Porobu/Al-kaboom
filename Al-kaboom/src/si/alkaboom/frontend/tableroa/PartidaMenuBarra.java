package si.alkaboom.frontend.tableroa;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import si.alkaboom.backend.AlKaboom;

public class PartidaMenuBarra extends JMenuBar {
	private static final long serialVersionUID = 6278678590130942938L;

	private JMenu fitxategia, partida, gehiago, ranking;
	private JMenuItem partidaBerria, partidaKargatu, partidaGorde, itxi, pausa, partidaBertanBehera, laguntza,
			alKaboomBuruz, alKaboomWeb, rankingBistaratu;

	public PartidaMenuBarra() {
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

	private void partidaMenuaEraiki() {
		partida = new JMenu("Partida");
		partidaKargatu = new JMenuItem("Partida bat kargatu");
		partidaGorde = new JMenuItem("Partida gorde");
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

	private void menuaAldatu() {
		if (rankingBistaratu.getText().equalsIgnoreCase("Ranking Bistaratu")) {
			rankingBistaratu.setText("Rankingetik Itzuli");
			AlKaboom.getAlKaboom().getUI().rankingIpini();
		} else {
			rankingBistaratu.setText("Ranking Bistaratu");
			AlKaboom.getAlKaboom().getUI().rankingItxi();
		}
	}

}
