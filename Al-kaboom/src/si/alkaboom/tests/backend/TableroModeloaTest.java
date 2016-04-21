package si.alkaboom.tests.backend;

import java.lang.reflect.Field;

import org.junit.Test;

import si.alkaboom.backend.TableroModeloa;
import si.alkaboom.backend.laukia.ILaukia;
import si.alkaboom.backend.laukia.LaukiaZenb;

public class TableroModeloaTest {

	private void bistaratu(TableroModeloa gureTableroa)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<TableroModeloa> tm = TableroModeloa.class;
		Field f = tm.getDeclaredField("tableroa");
		f.setAccessible(true);
		ILaukia[][] tableroa = (ILaukia[][]) f.get(gureTableroa);
		for (int i = 0; i < tableroa.length; i++) {
			for (int j = 0; j < tableroa[0].length; j++) {
				System.out.print(tableroa[i][j].getClass().getSimpleName() + " ");
				if (tableroa[i][j].getClass().getSimpleName().contains("LaukiaZenb")) {
					LaukiaZenb l = (LaukiaZenb) tableroa[i][j];
					System.out.print(l.getZenbakia() + "  ");
				} else
					System.out.print("   ");
			}
			System.out.println();
		}
	}

	@Test
	public void testTableroModeloa()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		TableroModeloa gureTableroa = null;// = new TableroModeloa(10, 10, 10,
											// 3, 3);
		this.bistaratu(gureTableroa);
		System.out.println();
		System.out.println();
		// gureTableroa = new TableroModeloa(12, 10, 70, 0, 0);
		this.bistaratu(gureTableroa);
	}

}
