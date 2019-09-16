package exerc3_L04;

public class Pao {
	
	private String nome;
	private boolean assado;
	
	public Pao(String nome) {
		this.nome = nome;
		this.assado = false;
	}

	public static Pao criaPao(String nome) {
		return new Pao(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setAssado(boolean assado) {
		this.assado = assado;
	}
}
