package correio;

public class Tratador extends Thread {

	private static int QTDMIN_POMBOS = 2;
	private static int QTD_TEMPOS = 20;
	private Gaiola gaiola;
	
	public Tratador(String nome, Gaiola gaiola) {
		super(nome);
		this.gaiola = gaiola;
	}
	
	@Override
	public void run() {
		setPriority(NORM_PRIORITY);
		while(true)
			checarGaiola();
	}

	public void checarGaiola() {
		try {
			Thread.sleep(1000 * QTD_TEMPOS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(gaiola.getPombos().size() < QTDMIN_POMBOS) { //Qtd de pombos insuficiente...
			System.out.println("*** [Não há pombos suficientes para abrir a gaiola!] ***");
			System.out.println("*** [O Tratador vai aguardar 20s...] ***");
		} else { //Qtd suficiente, autorizando saída dos pombos!
			System.out.println("*** [Há pombos suficientes, abrindo gaiola!] ***");
			for (Pombo pombo : gaiola.getPombos())
				pombo.autorizaVolta(); //Autoriza a volta do pombo!
			gaiola.abreGaiola(gaiola.getPombos()); //Remove todos os pombos da gaiola!
		}
	}
}
