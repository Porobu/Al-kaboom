package si.alkaboom.tests.backend;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.DBKS;
import si.alkaboom.backend.FitxategiOperazioak;

public class DBKSTest {
	private DBKS singletonDBKS;
	private Class<DBKS> dbks;

	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException {
		String path = DBKS.getDBKS().getDefaultPath();
		if (path == null)
			fail("Datu basea ez da aurkitu");
		DBKS.getDBKS().konektatu(path);
		dbks = DBKS.class;
		Field singleton = dbks.getDeclaredField("gureDBKS");
		singleton.setAccessible(true);
		singletonDBKS = (DBKS) singleton.get(dbks);
	}

	@After
	public void tearDown() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DBKS.getDBKS().deskonektatu();
	}

	@Test
	public void testAginduaExekutatu() {
		DBKS.getDBKS().aginduaExekutatu("Delete from Jokalaria where Izena = 'Test'");
		int erantzuna = 0, erantzuna2 = 0;
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select count(*) from Jokalaria");
		try {
			rs.next();
			erantzuna = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT INTO Jokalaria(Izena, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', '01-01-2010', 10, 20)");
		rs = DBKS.getDBKS().kontsultaExekutatu("Select count(*) from Jokalaria");
		try {
			rs.next();
			erantzuna2 = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (erantzuna >= erantzuna2)
			fail();
	}

	@Test
	public void testDatuBaseaKonprobatu() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IOException {
		Method dbKonprobatu = dbks.getDeclaredMethod("datubaseaKonprobatu");
		dbKonprobatu.setAccessible(true);
		boolean b = (boolean) dbKonprobatu.invoke(singletonDBKS);
		if (!b)
			fail();
		DBKS.getDBKS().deskonektatu();
		String path = System.getProperty("user.home") + "/AlKaboomTest.db";
		File f = new File(path);
		if (f.exists())
			f.delete();
		FitxategiOperazioak fo = new FitxategiOperazioak();
		fo.kopiatu(DBKS.getDBKS().getDefaultPath(), path);
		f = new File(path);
		if (!f.exists())
			fail();
		DBKS.getDBKS().deskonektatu();
		DBKS.getDBKS().konektatu(f.getAbsolutePath());
		DBKS.getDBKS().aginduaExekutatu("drop table if exists Jokalaria");
		b = (boolean) dbKonprobatu.invoke(singletonDBKS);
		if (b)
			fail();
		f.delete();
	}

	@Test
	public void testDeskonektatu() throws SQLException {
		DBKS.getDBKS().deskonektatu();
		if (DBKS.getDBKS().konekatutaDago())
			fail();
	}

	@Test
	public void testEguneraketaExekutatu() {
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', '01-01-2010', 10,20 )");
		DBKS.getDBKS().aginduaExekutatu(
				"UPDATE Jokalaria SET AzkenData='10-10-2010', IrabaziKop=5, GalduKop=5 WHERE Izena='Test'");
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select IrabaziKop from Jokalaria where Izena = 'Test'");
		try {
			rs.next();
			int emaitza = rs.getInt(1);
			if (emaitza != 5)
				fail();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testKonekatutaDago()
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!DBKS.getDBKS().konekatutaDago())
			fail();
		DBKS.getDBKS().deskonektatu();
		if (DBKS.getDBKS().konekatutaDago())
			fail();
	}

	@Test
	public void testKontsultaExekutatu() throws SQLException {
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', '01-01-2010', 10,20 )");
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select * from Jokalaria");
		if (!rs.next())
			fail();
	}

}
