package exerc4_L04;

public class Botijao {
	
	private static int cntBotijoes = 1;
	private String nome;
	private long qtdGas;
	
	public Botijao() {
		this.nome = "Botijão "+cntBotijoes;
		this.qtdGas = 300;
		cntBotijoes++;
	}
	
	public static Botijao criaBotijao() {
		return new Botijao();
	}
	
	public long obtemQntGasDisponivel() {
		return this.qtdGas;
	}
	
	public String obtemNome() {
		return this.nome;
	}
	
	public void consomeGas(long qtd) {
		this.qtdGas -= qtd;
		System.out.println("*** [CONSUMIDO "+qtd+"min DE GÁS, RESTAM "+this.qtdGas+"!] ***");
	}
}
