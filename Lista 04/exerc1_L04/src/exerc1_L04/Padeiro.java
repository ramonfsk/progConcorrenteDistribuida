package exerc1_L04;

public class Padeiro extends Thread {

	private static int cntPao = 1;
	private Pao pao;
	private Forno forno;
	
	public Padeiro(String nome, Forno forno) {
		super(nome);
		this.forno = forno;
	}
	
	@Override
	public void run() {
		while(true) {
			this.fazerMassa();
			this.assarPao();
		}
	}
	
	public void fazerMassa() {
		long tmpRnd = (long) (Math.random() * (10000 + 1));
		try {
			System.out.println("O "+this.getName()+" está fazendo a massa do pão...");
			Thread.sleep(tmpRnd);
			this.pao = Pao.criaPao("Pao "+(cntPao++));
			System.out.println("*** O "+this.getName()+" finalizou o "+this.pao.getNome()+"! ***");
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public void assarPao() {
		this.forno.colocarPaoNoForno(this.getName(), this.pao);
		this.forno.assarPaoNoForno();
	}
}
