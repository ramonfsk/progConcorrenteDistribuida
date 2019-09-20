package exerc6_L04;

import java.util.concurrent.locks.*;

public class Forno {
	
	//private static int QTDMAX_PAES = 1;
	private static int cntFornos = 1;
	private String nome;
	private boolean estaEmUso;
	private Botijao botijao;
	private Lock lock;
	private Condition fornoLivre;
	private Condition reporGas;
	private Condition esperarGerente;
	
	public Forno(Botijao botijao, Lock lock, Condition fornoLivre, Condition reporGas, Condition esperarGerente) {
		this.nome = "Forno "+cntFornos;
		this.estaEmUso = false;
		this.botijao = botijao;
		this.lock = lock;
		this.fornoLivre = fornoLivre;
		this.reporGas = reporGas;
		this.esperarGerente = esperarGerente;
		cntFornos++;
	}
	
	public void colocarPaoNoForno(Padeiro padeiro, Pao pao) {
		this.lock.lock();
		try {
			while(this.estaEmUso) {
				try {
					this.fornoLivre.await();
				} catch (Exception e) { e.printStackTrace(); }
			}
			this.estaEmUso = true;
			if(this.botijao.obtemQntGasDisponivel() < (pao.getPeso() * 10)/1000) {
				System.out.println(">>> Qtd de gás "+this.botijao.obtemQntGasDisponivel()+" insuficiente para assar o "+pao.getNome()+"! (tempo="+((pao.getPeso() * 10)/1000)+"s)!] <<<");
				System.out.println("!!! Notificando o gerente !!!");
				this.reporGas.signal();
				while(this.botijao.obtemQntGasDisponivel() == 0)
					this.esperarGerente.await();
			}
			this.assarPao(padeiro, pao);
			this.estaEmUso = false;
			this.fornoLivre.signal();
		} catch (Exception e) { e.printStackTrace();
		} finally { this.lock.unlock(); }		
	}
	
	public void assarPao(Padeiro padeiro, Pao pao) {
		this.lock.lock();
		System.out.println("### O "+pao.getNome()+" foi colocado no "+this.nome+" pelo "+padeiro.getName()+"!");
		System.out.println(">>> [O "+this.nome+" ESTÁ OCUPADO PELO "+padeiro.getName()+"!] <<<");
		try {
			long tmpAssnd = pao.getPeso() * 10;
			System.out.println(">>> O "+pao.getNome()+" vai assar no forno por "+(tmpAssnd/1000)+"s. <<<");
			Thread.sleep(tmpAssnd);
			this.botijao.consomeGas(tmpAssnd/1000);
			pao.setAssado(true);
			System.out.println(">>> "+pao.getNome()+" está pronto! ***");
			System.out.println(">>> [O "+this.nome+" FOI LIBERADO PELO "+padeiro.getName()+"!] <<<");
			pao = null;
			System.out.println("+++ Qtd de gás restante: "+this.botijao.obtemQntGasDisponivel()+"s.");
		} catch (InterruptedException e) { e.printStackTrace(); 
		} finally { this.lock.unlock(); }
	}
	
	public void trocaBotijao(Botijao botijao) {
		this.lock.lock();
		try {
			while(botijao.obtemQntGasDisponivel() != 0) {
				this.reporGas.await();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.botijao = botijao;
		System.out.println("+++ O "+this.botijao.obtemNome()+" foi colocado no "+this.nome+"!");		
	}
}
