package exerc2_L03;

import java.util.concurrent.locks.*;

public class Cadeira {
	
	private static int QTDMAX_CADEIRAS = 10;
	private int[] buffer;
	private int cntBuffer;
	private Lock lock;
	private Condition podeLer;
	private Condition podeAlterar;
	
	public Cadeira() {
		this.buffer = new int[QTDMAX_CADEIRAS];
		this.lock = new ReentrantLock(true);
		this.podeLer = this.lock.newCondition();
		this.podeAlterar = this.lock.newCondition();
	}
	
}
