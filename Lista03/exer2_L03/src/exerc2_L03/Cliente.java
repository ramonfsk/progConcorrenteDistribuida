package exerc2_L03;

public class Cliente extends Pessoa implements Runnable {

	private Barbearia barbearia;
	private boolean cabeloEstaCortado;
	
	public Cliente(String nome, TipoPessoa tipo, Barbearia barbearia) {
		this.setNome(nome);
		this.setTipo(tipo);
		this.cabeloEstaCortado = false; 
		this.barbearia = barbearia;
	}
	
	@Override
	public String toString() {
		return this.getNome()+" criado com sucesso!";
	}
	
	@Override
	public void run() {
		this.entraNaBarbearia();
		this.saiDaBarbearia();
	}

	public void entraNaBarbearia() {
		synchronized (this.barbearia) {
			System.out.println("*** [O "+this.getNome()+" entrou na barbearia!] ***");
			this.barbearia.addCliente(this);
			if(this.barbearia.verificaSeTodosOsBarbeirosEstaoOcupados()) {
				if(this.barbearia.verificaSeFilaDeEsperaEstaCheia()) {
					System.out.println("Não existem cadeiras suficientes, o "+this.getNome()+" está saindo da "+this.barbearia.getNome()+"!");
					Thread.interrupted();
				}
				try {
					System.out.println("O "+this.getNome()+" foi colocado em fila de espera!");
					this.barbearia.colocarClienteEmEspera(this);
					this.barbearia.wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
			} else { 
				System.out.println("*** [NOTIFICANDO TODOS OS BARBEIROS] ***");
				this.barbearia.notifyAll();
			}
		}
	}	
	
	public void cortarCabelo() {
		this.cabeloEstaCortado = true;
	}
	
	public void saiDaBarbearia() {
		System.out.println("Corte finalizado, saindo da "+this.barbearia.getNome()+"...");
	}
}
