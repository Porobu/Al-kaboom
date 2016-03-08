package si.alkaboom.tests;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.DBKS;

public class DBKSTest {
	
	@Before
	public void setUp() {
		String path = DBKS.getDBKS().getDefaultPath();
		if (path == null)
			fail("Datu basea ez da aurkitu");
		DBKS.getDBKS().konektatu(path);
	}

	@After
	public void tearDown() {
		DBKS.getDBKS().deskonektatu();
	}

	@Test
	public void testAginduaExekutatu() {
		DBKS.getDBKS().aginduaExekutatu(
				"INSERT OR REPLACE INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
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
				"INSERT OR REPLACE INTO Jokalaria(Izena, PartidaGordeta, AzkenData, IrabaziKop, GalduKop) VALUES ('Test', 'Bai', '01-01-2010', 10,20 )");
		DBKS.getDBKS().aginduaExekutatu(
				"UPDATE Jokalaria SET PartidaGordeta='Ez', AzkenData='10-10-2010', IrabaziKop=5, GalduKop=5 WHERE Izena='Test'");
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
