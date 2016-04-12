package si.alkaboom.tests.db;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import si.alkaboom.backend.DBKS;

public class DBTest {
	private Table jokalaria, partida, puntuazioa;

	@Test
	public void dbTest() {
		assertThat(jokalaria).hasNumberOfColumns(6);
		assertThat(partida).hasNumberOfColumns(10);
		assertThat(puntuazioa).hasNumberOfColumns(5);
	}

	@Before
	public void setUp() throws Exception {
		Source dataSource = new Source("jdbc:sqlite:" + DBKS.getDBKS().getDefaultPath(), "", "");
		jokalaria = new Table(dataSource, "Jokalaria");
		partida = new Table(dataSource, "Partida");
		puntuazioa = new Table(dataSource, "Puntuazioa");
	}

	@After
	public void tearDown() throws Exception {
	}

}
