package si.alkaboom.backend.laukia;

public class LaukiaZenb implements ILaukia {

		private int zenbakia;
		
		public LaukiaZenb(){
			zenbakia=1;
		}

		public int getZenbakia() {
			return zenbakia;
		}

		public void zenbPlus(){
			zenbakia++;
		}
		
		
}
