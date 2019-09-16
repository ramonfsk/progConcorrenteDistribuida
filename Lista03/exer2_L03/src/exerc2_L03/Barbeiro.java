package exerc2_L03;

public class Barbeiro extends Pessoa implements Runnable {
	
	private boolean dormindo;
	private Barbearia barbearia;
	
	public Barbeiro(String nome, TipoPessoa tipo, Barbearia barbearia) {
		this.setNome(nome);
		this.setTipo(tipo);
		this.barbearia = barbearia;
	}
	
	@Override
	public String toString() {
		return this.getNome()+" criado com sucesso!";
	}
	
	@Override
	public void run() {
		this.dorme();
		this.cortarCabelo();
	}

	public void dorme() {
		synchronized (this.barbearia) {
			System.out.println("*** [O "+this.getNome()+" está indo dormir...] ***");
			try {
				this.barbearia.wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public void cortarCabelo() {
		System.out.println(this.getNome()+" cortando o cabelo...");
	}

	public boolean isDormindo() {
		return dormindo;
	}
}
