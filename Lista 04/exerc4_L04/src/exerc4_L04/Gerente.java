package exerc4_L04;

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
			this.botijao.wait();
			this.forno.trocaBotijao(Botijao.criaBotijao());
		} catch (InterruptedException e) { e.printStackTrace(); }
		this.forno.trocaBotijao(botijao);
		this.forno.notify();
	}
}
