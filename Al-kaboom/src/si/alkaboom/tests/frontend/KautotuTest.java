package si.alkaboom.tests.frontend;

import org.assertj.core.api.Assertions;
import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.security.NoExitSecurityManagerInstaller;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.backend.DBKS;
import si.alkaboom.frontend.UI;

@GUITest
public class KautotuTest {
	private static NoExitSecurityManagerInstaller noExitSecurityManagerInstaller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FailOnThreadViolationRepaintManager.install();
		noExitSecurityManagerInstaller = NoExitSecurityManagerInstaller.installNoExitSecurityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		noExitSecurityManagerInstaller.uninstall();
	}

	private JPanelFixture ksPanela;
	private FrameFixture window;

	@Test
	public void kautotuDefektuzkoAukerak() {
		ksPanela = window.panel("Kautotu Panela").panel("Kautotu Sartzeko Panela");
		ksPanela.textBox("Erabiltzailea Field").enterText("Test");
		Assertions.assertThat( window.panel("Kautotu Panela").panel("Taula Panela").table().rowCount()).isEqualTo(1);
		ksPanela.button("Sartu").click();
	}

	@Test
	public void kautotuZaila() {
		ksPanela = window.panel("Kautotu Panela").panel("Kautotu Sartzeko Panela");
		ksPanela.textBox("Erabiltzailea Field").enterText("Test");
		Assertions.assertThat(ksPanela.comboBox("Aukerak").contents()).isNotNull().contains("Erreza", "Normala",
				"Zaila", "Custom...");
		ksPanela.comboBox("Aukerak").selectItem(2);
		ksPanela.button("Sartu").click();

	}

	@Before
	public void setUp() throws Exception {
		DBKS.getDBKS().konektatu(DBKS.getDBKS().getDefaultPath());
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
		AlKaboom.getAlKaboom().setDatubasePath(DBKS.getDBKS().getDefaultPath());
		UI frame = GuiActionRunner.execute(new GuiQuery<UI>() {
			@Override
			protected UI executeInEDT() {
				UI oraingoUI = new UI();
				oraingoUI.dekorazioGabeHasieratu();
				oraingoUI.kautotuHasieratu();
				AlKaboom.getAlKaboom().setUI(oraingoUI);
				return oraingoUI;
			}
		});
		window = new FrameFixture(frame);
		window.show();
	}

	@Test
	public void taulaIzenaHartu() throws InterruptedException {
		ksPanela = window.panel("Kautotu Panela").panel("Kautotu Sartzeko Panela");
		JPanelFixture taulaPanela = window.panel("Kautotu Panela").panel("Taula Panela");
		taulaPanela.table().cell("Test").click();
		Assertions.assertThat(ksPanela.textBox("Erabiltzailea Field").text()).contains("Test");
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void zailtasunaEskuzSartu() {
		ksPanela = window.panel("Kautotu Panela").panel("Kautotu Sartzeko Panela");
		ksPanela.textBox("Erabiltzailea Field").enterText("Test");
		Assertions.assertThat(ksPanela.comboBox("Aukerak").contents()).isNotNull().contains("Erreza", "Normala",
				"Zaila", "Custom...");
		ksPanela.comboBox("Aukerak").selectItem(3);
		ksPanela.button("Zailtasuna Sartu").click();
		JPanelFixture zailtasunaSartu = window.panel("Kautotu Panela").panel("Zailtasuna Panela");
		zailtasunaSartu.textBox("Errenkada Kopurua").enterText("10");
		Assertions.assertThat(zailtasunaSartu.textBox("Errenkada Kopurua").text()).contains("10");
		zailtasunaSartu.textBox("Zutabe Kopurua").enterText("abc");
		Assertions.assertThat(zailtasunaSartu.textBox("Zutabe Kopurua").text()).contains("");
		zailtasunaSartu.textBox("Zutabe Kopurua").enterText("9999");
		Assertions.assertThat(zailtasunaSartu.textBox("Zutabe Kopurua").text()).contains("999");
		zailtasunaSartu.textBox("Mina Kopurua").enterText("15");
		Assertions.assertThat(zailtasunaSartu.textBox("Mina Kopurua").text()).contains("15");
		zailtasunaSartu.button("OK").click();
		Assertions.assertThat(ksPanela.comboBox("Aukerak").contents()).contains("Custom(10x999, 15)");
		ksPanela.button("Sartu").click();
	}
}
