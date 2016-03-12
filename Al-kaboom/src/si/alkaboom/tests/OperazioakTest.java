package si.alkaboom.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.Operazioak;

public class OperazioakTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testErabiltzaileakBistaratu() {
		fail("Not yet implemented");
	}

	@Test
	public void testRSKopiatu() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Operazioak o = new Operazioak();
		Class<Operazioak> c = Operazioak.class;
		Method rsKop = c.getDeclaredMethod("rsKopiatu", ResultSet.class, int.class);
		rsKop.setAccessible(true);
		ResultSet rs = null;
		rsKop.invoke(o, rs, 0);
	}

}
