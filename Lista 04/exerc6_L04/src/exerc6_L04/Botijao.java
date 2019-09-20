package exerc6_L04;

import java.util.concurrent.locks.*;

public class Botijao {
	
	private static int cntBotijoes = 0;
	private String nome;
	private long qtdGas;
	private long tamGas;
	private Lock lock;
	private Condition fornoLivre;
	private Condition reporGas;
	private Condition esperarCliente;
	
	public Botijao(long tamGas, Lock lock, Condition fornoLivre, Condition reporGas, Condition esperarCliente) {
		this.nome = "Botijão "+cntBotijoes;
		this.tamGas = tamGas * 60;
		this.qtdGas = this.tamGas;
		this.lock = lock;
		this.fornoLivre = fornoLivre;
		this.reporGas = reporGas;
		this.esperarCliente = esperarCliente;
		cntBotijoes++;
	}
	
	public static Botijao criaBotijao(long qtdGas) {
		return new Botijao(qtdGas);
	}
	
	public void consomeGas(long qtd) {
		this.qtdGas -= qtd;
		System.out.println("*** Consumido "+qtd+"s de gás, restam "+this.qtdGas+"s!");
	}
	
	public long obtemQntGasDisponivel() {
		return this.qtdGas;
	}
	
	public long obtemTamGas() {
		return this.tamGas;
	}
	
	public String obtemNome() {
		return this.nome;
	}
}
