package exerc4_L04;

public class Forno {
	
	private static int cntFornos = 1;
	private String nome;
	private Botijao botijao;
	private Pao pao;
	
	public Forno() {
		this.nome = "Forno "+cntFornos;
		this.botijao = Botijao.criaBotijao();
		cntFornos++;
	}
	
	public synchronized void colocarPaoNoForno(Padeiro padeiro, Pao pao) {
		if(this.botijao.obtemQntGasDisponivel() < (pao.getPeso() * 10)/1000) {
			System.out.println("*** [QNTD DE G�S "+this.botijao.obtemQntGasDisponivel()+" INSUFICIENTE PARA ASSAR O P�O(tempo="+((pao.getPeso() * 10)/1000)+"s)!] ***");
			/*synchronized (padeiro) {
				try {
					padeiro.wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
			synchronized (this.gerente) {
				this.gerente.notify();
				System.out.println("O "+this.gerente.getName()+" foi notificado para trocar o botij�o!");
			}*/
		} else {
			System.out.println("O "+pao.getNome()+" foi colocado no "+this.nome+" pelo "+padeiro.getName()+"!");
			this.pao = pao;
			System.out.println("*** [O "+this.nome+" EST� OCUPADO PELO "+padeiro.getName()+"!] ***");
		}
	}
	
	public synchronized void assarPaoNoForno(String nome) {
		try {
			long tmpAssnd = this.pao.getPeso() * 10;
			System.out.println("O "+this.pao.getNome()+" vai assar no forno por "+(tmpAssnd/1000)+"s.");
			Thread.sleep(tmpAssnd);
			this.botijao.consomeGas(tmpAssnd/1000);
			this.pao.setAssado(true);
			System.out.println("*** "+this.pao.getNome()+" est� pronto! ***");
			System.out.println("*** [O "+this.nome+" FOI LIBERADO PELO "+nome+"!] ***");
			this.pao = null;
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public synchronized void trocaBotijao(Botijao botijao) {
		this.botijao = botijao;
		System.out.println("*** [O "+this.botijao.obtemNome()+" FOI COLOCADO NO "+this.nome+"!] ***");
		this.notifyAll();
		System.out.println("*** [TODOS OS PADEIROS FORAM NOTIFICADOS!] ***");
	}
}
