package exerc5_L04;

public class Forno {
	
	//private static int QTDMAX_PAES = 1;
	private static int cntFornos = 1;
	private String nome;
	private boolean estaEmUso;
	private Botijao botijao;
	
	public Forno(Botijao botijao) {
		this.nome = "Forno "+cntFornos;
		this.estaEmUso = false;
		this.botijao = botijao;
		cntFornos++;
	}
	
	public synchronized void colocarPaoNoForno(Padeiro padeiro, Pao pao) {
		this.estaEmUso = true;
		if(this.botijao.obtemQntGasDisponivel() < (pao.getPeso() * 10)/1000) {
			System.out.println(">>> Qtd de gás "+this.botijao.obtemQntGasDisponivel()+" insuficiente para assar o "+pao.getNome()+"! (tempo="+((pao.getPeso() * 10)/1000)+"s)!] <<<");
			synchronized (this.botijao) {
				System.out.println("!!! Notificando o gerente !!!");
				this.botijao.notify();
			}
			try {
				System.out.println("### O "+padeiro.getName()+" esta aguardando para assar o pao "+pao.getNome()+"! ***");
				padeiro.setPriority(Thread.MAX_PRIORITY);
				System.out.println(">>> A prioridade do "+padeiro.getName()+" foi alterado para nível 10! <<<");
				this.wait();
				this.assarPao(padeiro, pao);
				this.estaEmUso = false;
			} catch (InterruptedException e) { e.printStackTrace(); }
		} else { 
			this.assarPao(padeiro, pao);
			this.estaEmUso = false;
		}
	}
	
	public synchronized void assarPao(Padeiro padeiro, Pao pao) {
		System.out.println("### O "+pao.getNome()+" foi colocado no "+this.nome+" pelo "+padeiro.getName()+"!");
		System.out.println(">>> [O "+this.nome+" ESTÁ OCUPADO PELO "+padeiro.getName()+"!] <<<");
		try {
			long tmpAssnd = pao.getPeso() * 10;
			System.out.println(">>> O "+pao.getNome()+" vai assar no forno por "+(tmpAssnd/1000)+"s. <<<");
			Thread.sleep(tmpAssnd);
			this.botijao.consomeGas(tmpAssnd/1000);
			pao.setAssado(true);
			System.out.println(">>> "+pao.getNome()+" está pronto! ***");
			System.out.println(">>> [O "+this.nome+" FOI LIBERADO PELO "+padeiro.getName()+"!] <<<");
			pao = null;
			System.out.println("+++ Qtd de gás restante: "+this.botijao.obtemQntGasDisponivel()+"s.");
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public void trocaBotijao(Botijao botijao) {
		this.botijao = botijao;
		System.out.println("+++ O "+this.botijao.obtemNome()+" foi colocado no "+this.nome+"!");		
	}
	
	public synchronized boolean estaEmUso() {
		return this.estaEmUso;
	}
}
