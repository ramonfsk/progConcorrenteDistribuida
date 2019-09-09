package exerc2_L03;

public class Cliente extends Pessoa implements Runnable {

	private Cadeira cadeira;
	
	public Cliente(String nome, TipoPessoa tipo, Cadeira cadeira) {
		this.setNome(nome);
		this.setTipo(tipo);
		this.cadeira = cadeira;
	}
	
	@Override
	public void run() {
		this.entrarNaBarbearia();
		this.sairDaBarbearia();
	}

	public void entrarNaBarbearia() {
		
	}
	
	public void sairDaBarbearia() {
		
	}
}
