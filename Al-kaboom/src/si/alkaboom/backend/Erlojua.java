package si.alkaboom.backend;

import org.apache.commons.lang3.time.StopWatch;

public class Erlojua {
	private StopWatch s;
	private long denboraPartGordeta;

	public Erlojua() {
		this.s = new StopWatch();

	}

	public void erlojuaHasi() {
		s.start();
	}

	public long getDenbora() {
		return s.getTime() + this.denboraPartGordeta;
	}

	public void erlojuaGarbitu() {
		s.reset();
	}

	public void erlojuaPausatu() {
		s.suspend();
	}

	public void erlojuaMartxanIpini() {
		s.resume();
	}

	public void erlojuaGelditu() {
		s.stop();
	}

	public void setDenboraPartGordeta(long denobraPartGordeta) {
		this.denboraPartGordeta = denobraPartGordeta;
	}

}
