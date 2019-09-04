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
		while(true)
			checarGaiola();
	}

	public void checarGaiola() {
		if(gaiola.getPombos().size() < QTDMIN_POMBOS) { //Qtd de pombos insuficiente...
			try {
				Thread.sleep(1000 * QTD_TEMPOS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else { //Qtd suficiente, autorizando saída dos pombos!
			for (Pombo pombo : gaiola.getPombos())
				pombo.autorizaVolta(); //Autoriza a volta do pombo!
			gaiola.abreGaiola(gaiola.getPombos()); //Remove todos os pombos da gaiola!
		}
	}
}
