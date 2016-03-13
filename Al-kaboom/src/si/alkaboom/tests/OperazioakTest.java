package si.alkaboom.tests;

import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.DBKS;
import si.alkaboom.backend.Operazioak;

public class OperazioakTest {
	private DBKS singletonDBKS;
	private Method deskonektatuMetodoa;

	@Before
	public void setUp() throws Exception {
		DBKS.getDBKS().konektatu(DBKS.getDBKS().getDefaultPath());
		if (!DBKS.getDBKS().konekatutaDago())
			fail();
		Class<DBKS> dbks = DBKS.class;
		Field singleton = dbks.getDeclaredField("gureDBKS");
		singleton.setAccessible(true);
		singletonDBKS = (DBKS) singleton.get(dbks);
		deskonektatuMetodoa = dbks.getDeclaredMethod("deskonektatu");
		deskonektatuMetodoa.setAccessible(true);
	}

	@After
	public void tearDown() throws Exception {
		deskonektatuMetodoa.invoke(singletonDBKS);
	}

	@Test
	public void testErabiltzaileakBistaratu() {
		System.out.println("Test Erabiltzaileak Bistaratu");
		System.out.println("izena like t");
		Operazioak op = new Operazioak();
		ArrayList<String[]> array = op.erabiltzaileakBistaratu("t");
		for (Iterator<String[]> iterator = array.iterator(); iterator.hasNext();) {
			String[] strings = iterator.next();
			for (String string : strings) {
				System.out.print(string + " - ");
			}
			System.out.println();
		}
		System.out.println("izena like %");
		array = op.erabiltzaileakBistaratu("");
		for (Iterator<String[]> iterator = array.iterator(); iterator.hasNext();) {
			String[] strings = iterator.next();
			for (String string : strings) {
				System.out.print(string + " - ");
			}
			System.out.println();
		}
	}

	@Test
	public void testRSKopiatu() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException {
		Operazioak o = new Operazioak();
		Class<Operazioak> c = Operazioak.class;
		Method rsKop = c.getDeclaredMethod("rsKopiatu", ResultSet.class, int.class);
		rsKop.setAccessible(true);
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select * from Jokalaria");
		ResultSetMetaData rsdata = rs.getMetaData();
		Object object = rsKop.invoke(o, rs, rsdata.getColumnCount());
		ArrayList<?> array = (ArrayList<?>) object;
		System.out.println("Test RS Kopiatu");
		for (Iterator<?> iterator = (Iterator<?>) array.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			for (String string : strings) {
				System.out.print(string + " - ");
			}
			System.out.println();
		}
	}
}
