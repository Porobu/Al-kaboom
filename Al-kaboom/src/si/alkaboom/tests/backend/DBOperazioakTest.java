package si.alkaboom.tests.backend;

import static org.junit.Assert.fail;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.DBKS;
import si.alkaboom.backend.DBOperazioak;

public class DBOperazioakTest {

	@Before
	public void setUp() throws Exception {
		DBKS.getDBKS().konektatu(DBKS.getDBKS().getDefaultPath());
		if (!DBKS.getDBKS().konekatutaDago())
			fail();
	}

	@After
	public void tearDown() throws Exception {
		DBKS.getDBKS().deskonektatu();
	}

	@Test
	public void testErabiltzaileakBistaratu() {
		System.out.println("\nTest Erabiltzaileak Bistaratu");
		System.out.println("izena like t");
		DBOperazioak op = new DBOperazioak();
		ArrayList<String[]> array = op.erabiltzaileakBistaratu("\nizena like %t%");
		Assertions.assertThat(array).isNotNull();
		for (Iterator<String[]> iterator = array.iterator(); iterator.hasNext();) {
			String[] strings = iterator.next();
			for (String string : strings) {
				System.out.print(string + " - ");
			}
			System.out.println();
		}
		System.out.println("\nizena like %%");
		array = op.erabiltzaileakBistaratu("");
		Assertions.assertThat(array).isNotNull();
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
		DBOperazioak o = new DBOperazioak();
		Class<DBOperazioak> c = DBOperazioak.class;
		Method rsKop = c.getDeclaredMethod("rsKopiatu", ResultSet.class, int.class);
		rsKop.setAccessible(true);
		ResultSet rs = DBKS.getDBKS().kontsultaExekutatu("Select * from Jokalaria");
		ResultSetMetaData rsdata = rs.getMetaData();
		Object object = rsKop.invoke(o, rs, rsdata.getColumnCount());
		ArrayList<? extends Object> array = (ArrayList<?>) object;
		Assertions.assertThat(array).isNotNull();
		System.out.println("Test RS Kopiatu");
		for (Iterator<? extends Object> iterator = array.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			for (String string : strings) {
				System.out.print(string + " - ");
			}
			System.out.println();
		}
	}
}
