package exerc4_L04;

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
			this.assarPao();
		}
	}
	
	public void fazerMassa() {
		try {
			long tmpRnd = (long) (Math.random() * (10000 + 1));
			System.out.println("O "+this.getName()+" está fazendo a massa do pão...");
			Thread.sleep(tmpRnd);
			this.pao = Pao.criaPao(((int) tmpRnd));
			System.out.println("*** O "+this.getName()+" finalizou o "+this.pao.getNome()+", tem "+this.pao.getPeso()+"g! ***");
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public void assarPao() {
		this.forno.colocarPaoNoForno(this, this.pao);
		this.forno.assarPaoNoForno(this.getName());
	}
}
