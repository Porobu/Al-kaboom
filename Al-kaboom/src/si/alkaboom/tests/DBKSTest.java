package si.alkaboom.tests;

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
	private Method deskonektatuMetodoa;
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
		deskonektatuMetodoa = dbks.getDeclaredMethod("deskonektatu");
		deskonektatuMetodoa.setAccessible(true);
	}

	@After
	public void tearDown() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		deskonektatuMetodoa.invoke(singletonDBKS);
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
				"INSERT INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
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
		deskonektatuMetodoa.invoke(singletonDBKS);
		String path = System.getProperty("user.home") + "/AlKaboomTest.db";
		File f = new File(path);
		if (f.exists())
			f.delete();
		FitxategiOperazioak fo = new FitxategiOperazioak();
		fo.kopiatu(DBKS.getDBKS().getDefaultPath(), path);
		f = new File(path);
		if (!f.exists())
			fail();
		deskonektatuMetodoa.invoke(singletonDBKS);
		DBKS.getDBKS().konektatu(f.getAbsolutePath());
		DBKS.getDBKS().aginduaExekutatu("drop table if exists Jokalaria");
		b = (boolean) dbKonprobatu.invoke(singletonDBKS);
		if (b)
			fail();
		f.delete();
	}

	@Test
	public void testDeskonektatu()
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		deskonektatuMetodoa.invoke(singletonDBKS);
		if (DBKS.getDBKS().konekatutaDago())
			fail();
	}

	@Test
	public void testEguneraketaExekutatu() {
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
		DBKS.getDBKS().aginduaExekutatu(
				"UPDATE Jokalaria SET PartidaGordeta='Ez', AzkenData='10-10-2010', IrabaziKop=5, GalduKop=5 WHERE Izena='Test'");
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select PartidaGordeta from Jokalaria where Izena = 'Test'");
		try {
			rs.next();
			String emaitza = rs.getString(1);
			if (!emaitza.equalsIgnoreCase("ez"))
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
		deskonektatuMetodoa.invoke(singletonDBKS);
		if (DBKS.getDBKS().konekatutaDago())
			fail();
	}

	@Test
	public void testKontsultaExekutatu() throws SQLException {
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select * from Jokalaria");
		if (!rs.next())
			fail();
	}

}
