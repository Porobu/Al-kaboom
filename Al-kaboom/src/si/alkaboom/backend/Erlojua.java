package si.alkaboom.backend;

import org.apache.commons.lang3.time.StopWatch;

public class Erlojua {
	private StopWatch s;

	public Erlojua() {
		this.s = new StopWatch();
	}

	public void erlojuaHasi() {
		s.start();
	}

	public long getDenbora() {
		return s.getTime();
	}

	public void erlojuaGarbitu() {
		s.reset();
	}

	public void erlojuaPausatu() {
		s.split();
	}

	public void erlojuaMartxanIpini() {
		s.unsplit();
	}

	public void erlojuaGelditu() {
		s.stop();
	}

}
