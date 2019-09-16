package exerc2_L03;

public class Barbeiro extends Pessoa implements Runnable {
	
	private static int QTDMAX_BARBEIROS = 5;
	private Cadeira cadeira;
	
	public Barbeiro(String nome, TipoPessoa tipo, Cadeira cadeira) {
		this.setNome(nome);
		this.setTipo(tipo);
		this.cadeira = cadeira;
	}
	
	@Override
	public void run() {
		this.dormir();
		this.cortarCabelo();
	}

	public void dormir() {
		synchronized (this.cadeira) {
			try {
				System.out.println("***[Não existem clientes a serem atendidos, o barbeiro "+this.getNome()+" vai dormir...]***");
				this.wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public void cortarCabelo() {
		
	}
}
