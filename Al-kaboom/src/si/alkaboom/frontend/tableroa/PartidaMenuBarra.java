package si.alkaboom.frontend.tableroa;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.AlKaboomConstants;
import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class PartidaMenuBarra extends JMenuBar {
	private static final long serialVersionUID = 6278678590130942938L;

	private JMenu fitxategia, partida, gehiago, ranking;
	private JMenuItem partidaBerria, partidaKargatu, partidaGorde, itxi, pausa, laguntza, alKaboomBuruz, alKaboomWeb,
			rankingBistaratu, rankingEguneratu, partidaEzabatu, informazioa;
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

	private void alKaboomBuruz() {

	}

	private void alKaboomWeb() {
		try {
			Desktop.getDesktop().browse(new URI("https://github.com/Porobu/Al-kaboom"));
		} catch (IOException | URISyntaxException e) {
			throw new AlKaboomSalbuespena("Ezin da nabigatzailea ireki!", e);
		}
	}

	private void fitxategiMenuaEraiki() {
		fitxategia = new JMenu("Fitxategia");
		partidaBerria = new JMenuItem("Partida berria");
		partidaBerria.addActionListener(gureAE -> AlKaboom.getAlKaboom().getUI().partidaBerria());
		itxi = new JMenuItem("Programa itxi");
		itxi.addActionListener(gureAE -> this.programaItxi());
		fitxategia.add(partidaBerria);
		fitxategia.add(itxi);
	}

	private void gehiagoMenuaEraiki() {
		gehiago = new JMenu("Gehiago");
		laguntza = new JMenuItem("Laguntza");
		alKaboomBuruz = new JMenuItem("AlKaboom-eri buruz");
		alKaboomBuruz.addActionListener(gureAE -> this.alKaboomBuruz());
		alKaboomWeb = new JMenuItem("AlKaboom-eri buruz interneten");
		alKaboomWeb.addActionListener(gureAE -> this.alKaboomWeb());
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
		pausa.addActionListener(gureAE -> this.pausa());
		partidaEzabatu = new JMenuItem("Gordetako partida ezabatu");
		partidaEzabatu.addActionListener(gureAE -> pmg.partidaEzabatu());
		informazioa = new JMenuItem("Partidaren Informazioa");
		informazioa.addActionListener(gureAE -> pmg.informazioaPantailaratu());
		partida.add(informazioa);
		partida.add(partidaGorde);
		partida.add(partidaKargatu);
		partida.add(partidaEzabatu);
		partida.add(pausa);
	}

	private void pausa() {
		TableroPanela tp = AlKaboom.getAlKaboom().getUI().getTp();
		if (!tp.isPausa()) {
			tp.setPausa(true);
			this.pausa.setText("Jarraitu");
			AlKaboom.getAlKaboom().getErabiltzailea().getErlojua().erlojuaPausatu();
		} else {
			tp.setPausa(false);
			this.pausa.setText("Pausa");
			AlKaboom.getAlKaboom().getErabiltzailea().getErlojua().erlojuaMartxanIpini();
		}

	}

	private void programaItxi() {
		int aukera = JOptionPane.showConfirmDialog(AlKaboom.getAlKaboom().getUI(),
				"Al-Kaboom itxi nahi duzu?\nGorde ez den partida galduko da", AlKaboomConstants.IZENBURUA,
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (aukera == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	private void rankingMenuaEraiki() {
		this.ranking = new JMenu("Ranking");
		rankingBistaratu = new JMenuItem("Ranking Bistaratu");
		this.rankingEguneratu = new JMenuItem("Rankinga eguneratu");
		this.rankingEguneratu.addActionListener(gureAE -> AlKaboom.getAlKaboom().getUI().rankingEguneratu());
		rankingBistaratu.addActionListener(gureAE -> this.menuaAldatu());
		ranking.add(rankingBistaratu);
		ranking.add(rankingEguneratu);
	}

}
