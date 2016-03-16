package si.alkaboom.tests.frontend;

import org.assertj.core.api.Assertions;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import si.alkaboom.backend.DBKS;
import si.alkaboom.frontend.UI;

public class KautotuTest {
	private JPanelFixture ksPanela;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FailOnThreadViolationRepaintManager.install();
		DBKS.getDBKS().konektatu(DBKS.getDBKS().getDefaultPath());
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
	}

	private FrameFixture window;

	@Before
	public void setUp() throws Exception {
		UI frame = GuiActionRunner.execute(new GuiQuery<UI>() {
			@Override
			protected UI executeInEDT() {
				UI oraingoUI = new UI();
				oraingoUI.dekorazioGabeHasieratu();
				oraingoUI.kautotuHasieratu();
				return oraingoUI;
			}
		});
		window = new FrameFixture(frame);
		window.show();
	}

	@After
	public void tearDown() throws Exception {
		window.cleanUp();
	}

	@Test
	public void kautotuDefektuzkoAukerak() {
		ksPanela = window.panel("kautotu").panel("Kautotu Sartzeko Panela");
		ksPanela.textBox("Erabiltzailea Field").setText("Test");
		ksPanela.button("Sartu").click();
	}

	@Test
	public void kautotuZaila() {
		ksPanela = window.panel("kautotu").panel("Kautotu Sartzeko Panela");
		ksPanela.textBox("Erabiltzailea Field").setText("Test");
		Assertions.assertThat(ksPanela.comboBox("Aukerak").contents()).isNotNull().contains("Erreza", "Normala",
				"Zaila", "Custom...");
		ksPanela.comboBox("Aukerak").selectItem(2);
		ksPanela.button("Sartu").click();
	}

	@Test
	public void zailtasunaEskuzSartu() {
		ksPanela = window.panel("kautotu").panel("Kautotu Sartzeko Panela");
		ksPanela.textBox("Erabiltzailea Field").setText("Test");
		Assertions.assertThat(ksPanela.comboBox("Aukerak").contents()).isNotNull().contains("Erreza", "Normala",
				"Zaila", "Custom...");
		ksPanela.comboBox("Aukerak").selectItem(3);
		ksPanela.button("Zailtasuna Sartu").click();
		JPanelFixture zailtasunaSartu = window.panel("kautotu").panel("Zailtasuna Panela");

	}
}
