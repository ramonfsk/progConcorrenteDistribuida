package exerc4_L04;

public class Botijao {
	
	private static int cntBotijoes = 1;
	private String nome;
	private long qtdGas;
	private long tamGas;
	
	public Botijao(long tamGas) {
		this.nome = "Botij�o "+cntBotijoes;
		this.tamGas = tamGas * 60;
		this.qtdGas = tamGas * 60;
		cntBotijoes++;
	}
	
	public static Botijao criaBotijao(long qtdGas) {
		return new Botijao(qtdGas);
	}
	
	public synchronized void consomeGas(long qtd) {
		this.qtdGas -= qtd;
		System.out.println("*** [CONSUMIDO "+qtd+"s DE G�S, RESTAM "+this.qtdGas+"s!] ***");
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
