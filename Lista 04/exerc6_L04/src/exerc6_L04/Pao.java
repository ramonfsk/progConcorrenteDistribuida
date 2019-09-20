package exerc6_L04;

public class Pao {
	
	private static int cntPaes = 1;
	private String nome;
	private int peso;
	private boolean assado;
	
	public Pao(int peso) {
		this.nome = "Pão "+cntPaes;
		this.peso = peso;
		this.assado = false;
		cntPaes++;
	}

	public static Pao criaPao(int tmpRnd) {
		return new Pao(tmpRnd/5);
	}
	
	public String getNome() {
		return nome;
	}

	public int getPeso() {
		return peso;
	}
	
	public void setAssado(boolean assado) {
		this.assado = assado;
	}
}
