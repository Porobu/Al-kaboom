package si.alkaboom.tests.backend;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.DBKS;
import si.alkaboom.backend.FitxategiOperazioak;

public class FitxategiOperazioakTest {
	private FitxategiOperazioak f;

	@Before
	public void setUp() throws Exception {
		String path = DBKS.getDBKS().getDefaultPath();
		if (path == null)
			fail("Fitxategia ez da existitzen");
		f = new FitxategiOperazioak();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testDbEsportatu() {
		f.dbEsportatu("/si/alkaboom/Al-Kaboom.db", System.getProperty("user.home") + "/test.db");
		File file = new File(System.getProperty("user.home") + "/test.db");
		if (!file.exists())
			fail();
		if (!file.delete())
			System.err.println("Ezin da fitxategia ezabatu");
	}

	@Test
	public void testKopiatu() {
		f.kopiatu(DBKS.getDBKS().getDefaultPath(), System.getProperty("user.home") + "/kopia.db");
		File file = new File(System.getProperty("user.home") + "/kopia.db");
		if (!file.exists())
			fail();
		if (!file.delete())
			System.err.println("Ezin da fitxategia ezabatu");
	}

}
