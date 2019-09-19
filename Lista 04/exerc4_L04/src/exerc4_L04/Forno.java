package exerc4_L04;

public class Forno {
	
	//private static int QTDMAX_PAES = 1;
	private static int cntFornos = 1;
	private String nome;
	private Botijao botijao;
	
	public Forno(Botijao botijao) {
		this.nome = "Forno "+cntFornos;
		this.botijao = botijao;
		cntFornos++;
	}
	
	public synchronized void assarPao(Padeiro padeiro, Pao pao) {
		if(this.botijao.obtemQntGasDisponivel() < (pao.getPeso() * 10)/1000) {
			System.out.println("*** [QNTD DE GÁS "+this.botijao.obtemQntGasDisponivel()+
					" INSUFICIENTE PARA ASSAR O "+pao.getNome()+"(tempo="+((pao.getPeso() * 10)/1000)+"s)!] ***");
			synchronized (this.botijao) {
				System.out.println("!!! Notificando o gerente !!!");
				this.botijao.notify();
			}
			try {
				System.out.println("*** O "+padeiro.getName()+" esta aguardando para assar o pao "+pao.getNome()+"! ***");
				this.wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			System.out.println("O "+pao.getNome()+" foi colocado no "+this.nome+" pelo "+padeiro.getName()+"!");
			System.out.println("*** [O "+this.nome+" ESTÁ OCUPADO PELO "+padeiro.getName()+"!] ***");
			try {
				long tmpAssnd = pao.getPeso() * 10;
				System.out.println("O "+pao.getNome()+" vai assar no forno por "+(tmpAssnd/1000)+"s.");
				Thread.sleep(tmpAssnd);
				this.botijao.consomeGas(tmpAssnd/1000);
				pao.setAssado(true);
				System.out.println("*** "+pao.getNome()+" está pronto! ***");
				System.out.println("*** [O "+this.nome+" FOI LIBERADO PELO "+padeiro.getName()+"!] ***\n\n");
				pao = null;
				System.out.println("Qtd de gás restante: "+this.botijao.obtemQntGasDisponivel());
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public synchronized void trocaBotijao(Botijao botijao) {
		this.botijao = botijao;
		System.out.println("*** [O "+this.botijao.obtemNome()+" FOI COLOCADO NO "+this.nome+"!] ***");
		this.notify();
		System.out.println("*** [TODOS OS PADEIROS FORAM NOTIFICADOS!] ***");
	}
}
