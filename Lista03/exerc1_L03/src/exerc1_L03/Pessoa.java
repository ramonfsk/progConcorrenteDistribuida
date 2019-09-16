package exerc1_L03;

import java.util.concurrent.*;

public class Pessoa extends Thread {
	
	private static int QTDMAX_VAGAS = 30;
	private static int cntVagas = 1;
	private static Semaphore estacionamento = new Semaphore(QTDMAX_VAGAS, true);
	
	private TipoPessoa tipo;
	private Carro carro;
	
	public Pessoa(String nome, TipoPessoa tipo, Carro carro) {
		super(nome);
		this.tipo = tipo;
		this.carro = carro;
	}
	
	@Override
	public void run() {
		while(true)
			this.estaciona();
	}

	public void estaciona() {
		if(cntVagas == QTDMAX_VAGAS) {
			if(this.tipo == TipoPessoa.Professor) {
				System.out.println("*** [Limite "+cntVagas+" máximo, professores tem a preferência!] ***");
				this.setPriority(MAX_PRIORITY);
				this.entrarNoEstacionamento();
			} else if(this.tipo == TipoPessoa.Funcionario) {
				this.setPriority(MIN_PRIORITY);
				this.entrarNoEstacionamento();
			} else {
				this.setPriority(MIN_PRIORITY);
				this.entrarNoEstacionamento();
			}
		} else
			this.entrarNoEstacionamento();
		System.out.println("Qtd. vagas: "+cntVagas+"\n\n");
	}
	
	public void entrarNoEstacionamento() {
		try {
			estacionamento.acquire();
			System.out.println(this.getName()+" entrou no estacionamento com o carro "+this.carro.getModelo()+"!");
			Thread.sleep(10000);
			System.out.println(this.getName()+" estacionou na vaga "+cntVagas+"!");
			cntVagas++;
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println(this.getName()+" saindo do estacionamento!");
			cntVagas--;
			estacionamento.release();
		} catch (InterruptedException e) { e.printStackTrace(); }

	}
}
