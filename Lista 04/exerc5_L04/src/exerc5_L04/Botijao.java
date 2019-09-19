package exerc5_L04;

public class Botijao {
	
	private static int cntBotijoes = 0;
	private String nome;
	private long qtdGas;
	private long tamGas;
	
	public Botijao(long tamGas) {
		this.nome = "Botijão "+cntBotijoes;
		this.tamGas = tamGas * 60;
		this.qtdGas = this.tamGas;
		cntBotijoes++;
	}
	
	public static Botijao criaBotijao(long qtdGas) {
		return new Botijao(qtdGas);
	}
	
	public synchronized void consomeGas(long qtd) {
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
