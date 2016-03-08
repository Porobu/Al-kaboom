package si.alkaboom.frontend.tableroa;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PartidaMenuBarra extends JMenuBar {
	private static final long serialVersionUID = 6278678590130942938L;
	
	private JMenu fitxategia, partida, gehiago;
	private JMenuItem partidaBerria, partidaKargatu, partidaGorde, itxi, pausa, partidaBertanBehera, laguntza, alKaboomBuruz, alKaboomWeb;

	public PartidaMenuBarra() {
		
		this.fitxategiMenuaEraiki();
		this.partidaMenuaEraiki();
		this.gehiagoMenuaEraiki();
		this.add(fitxategia);
		this.add(partida);
		this.add(gehiago);

	}
	
	private void fitxategiMenuaEraiki(){
		fitxategia = new JMenu("Fitxategia");
		partidaBerria = new JMenuItem("Partida berria");
		itxi = new JMenuItem("Programa itxi");
		
		fitxategia.add(partidaBerria);
		fitxategia.add(itxi);
		
		//partidaBerria.addActionListener(null);
	}
	
	private void partidaMenuaEraiki(){
		
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
	
	private void gehiagoMenuaEraiki(){
		
		gehiago = new JMenu("Gehiago");
		laguntza = new JMenuItem("Laguntza");
		alKaboomBuruz = new JMenuItem("AlKaboom-eri buruz");
		alKaboomWeb = new JMenuItem("AlKaboom-eri buruz interneten");
				
		gehiago.add(laguntza);
		gehiago.add(alKaboomBuruz);
		gehiago.add(alKaboomWeb);
	}
}
