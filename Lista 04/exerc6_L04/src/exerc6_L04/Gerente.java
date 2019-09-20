package exerc6_L04;

import java.util.concurrent.locks.*;

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
	
	public void trocarBotijao() {
			this.forno.trocaBotijao(Botijao.criaBotijao(this.botijao.obtemTamGas()));
			System.out.println("%%% O "+this.getName()+" trocou o botijão!");
	}
}
