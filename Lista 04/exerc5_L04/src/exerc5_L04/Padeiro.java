package exerc5_L04;

public class Padeiro extends Thread {

	private static int cntPadeiros = 1;
	private Pao pao;
	private Forno forno;
	
	public Padeiro(Forno forno) {
		super("Padeiro "+cntPadeiros);
		this.forno = forno;
		cntPadeiros++;
	}
	
	@Override
	public void run() {
		while(true) {
			this.fazerMassa();
			if(this.forno.estaEmUso()) {
				synchronized (this.forno) {
					try {
						this.forno.wait();
					} catch (InterruptedException e) { e.printStackTrace(); }
				}
				this.setPriority(MIN_PRIORITY);
			}
			this.assarPao();
			this.setPriority(NORM_PRIORITY);
		}
	}
	
	public void fazerMassa() {
		try {
			long tmpRnd = (long) (Math.random() * (10000 + 1));
			System.out.println("### O "+this.getName()+" est� fazendo a massa do p�o...");
			Thread.sleep(tmpRnd);
			this.pao = Pao.criaPao(((int) tmpRnd));
			System.out.println("### O "+this.getName()+" finalizou o "+this.pao.getNome()+", tem "+this.pao.getPeso()+"g!");
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public void assarPao() {
		this.forno.colocarPaoNoForno(this, this.pao);
	}
}
