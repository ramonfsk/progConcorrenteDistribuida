package exerc2_L03;

public class Barbeiro extends Pessoa implements Runnable {
	
	private static int QTDMAX_BARBEIROS = 5;
	
	public Barbeiro(String nome, TipoPessoa tipo) {
		this.setNome(nome);
		this.setTipo(tipo);
	}
	
	@Override
	public void run() {
		this.dormir();
		this.cortarCabelo();
	}

	public void dormir() {
		
	}
	
	public void cortarCabelo() {
		
	}
}
