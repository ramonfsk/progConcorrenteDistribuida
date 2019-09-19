package exerc5_L04;

public class Gerente extends Thread {

	private static int cntGerentes = 1;
	private Botijao botijao;
	private Forno forno;
	
	public Gerente(Forno forno, Botijao botijao) {
		super("Gerente "+cntGerentes);
		this.forno = forno;
		this.botijao = botijao;
		cntGerentes++;
	}
	
	@Override
	public void run() {
		while(true)
			this.trocarBotijao();
	}
	
	public synchronized void trocarBotijao() {
		try {
			synchronized (this.botijao) {
				System.out.println("%%% O "+this.getName()+" está aguardando uma notificação...");
				this.botijao.wait();
			}
			this.forno.trocaBotijao(Botijao.criaBotijao(this.botijao.obtemTamGas()));
			System.out.println("%%% O "+this.getName()+" trocou o botijão!");
			synchronized (this.forno) {
				this.forno.notifyAll();
				System.out.println("!!! Notificando os padeiros !!!");
			}
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
}
