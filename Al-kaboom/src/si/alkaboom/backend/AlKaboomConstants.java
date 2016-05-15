package si.alkaboom.backend;

public interface AlKaboomConstants {
	public static final String BERTSIOA = "1.0.0";
	public static final String IZENBURUA = "Al-Kaboom " + BERTSIOA;
	public static final int ERRENKADA_KOPURUA_ERREZA = 10;
	public static final int ZUTABE_KOPURUA_ERREZA = 10;
	public static final int MINA_KOPURUA_ERREZA = 10;
	public static final int ERRENKADA_KOPURUA_NORMALA = 15;
	public static final int ZUTABE_KOPURUA_NORMALA = 12;
	public static final int MINA_KOPURUA_NORMALA = 50;
	public static final int ERRENKADA_KOPURUA_ZAILA = 25;
	public static final int ZUTABE_KOPURUA_ZAILA = 15;
	public static final int MINA_KOPURUA_ZAILA = 100;
	public static final int[] ERREZA = new int[] { ERRENKADA_KOPURUA_ERREZA, ZUTABE_KOPURUA_ERREZA,
			MINA_KOPURUA_ERREZA };
	public static final int[] NORMALA = new int[] { ERRENKADA_KOPURUA_NORMALA, ZUTABE_KOPURUA_NORMALA,
			MINA_KOPURUA_NORMALA };
	public static final int[] ZAILA = new int[] { ERRENKADA_KOPURUA_ZAILA, ZUTABE_KOPURUA_ZAILA, MINA_KOPURUA_ZAILA };
	public static final int TABLERO_TAMAINA_HORIZONTALA = 800;
	public static final int TABLERO_TAMAINA_BERTIKALA = 700;
	public static final String MARKARIK_EZ = "E";
	public static final String BANDERITA = "B";
	public static final String GALDERA = "G";
	public static final String IREKITA = "I";
}
