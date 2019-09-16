package exerc3_L04;

import java.util.concurrent.locks.*;

public class Forno {
	
	private String nome;
	private Pao pao;
	private Lock lock;
	
	public Forno(String nome) {
		this.nome = nome;
		this.lock = new ReentrantLock();
	}
	
	public void colocarPaoNoForno(String nome, Pao pao) {
		lock.lock();
			System.out.println("O "+pao.getNome()+" foi colocado no "+this.nome+" pelo "+nome+"!");
			this.pao = pao;
			System.out.println("*** [O "+this.nome+" ESTÁ OCUPADO PELO "+nome+"!] ***");
		lock.unlock();
	}
	
	public void assarPaoNoForno(String nome) {
		lock.lock();
			try {
				long tmpRnd = (long) (Math.random() * (10000 + 1));
				System.out.println("O "+this.pao.getNome()+" vai assar no forno por "+(tmpRnd/1000)+"s.");
				Thread.sleep(tmpRnd);
				this.pao.setAssado(true);
				System.out.println("*** "+this.pao.getNome()+" está pronto! ***");
				System.out.println("*** [O "+this.nome+" FOI LIBERADO PELO "+nome+"!] ***");
			} catch (InterruptedException e) { e.printStackTrace(); }
		lock.unlock();
	}
}
