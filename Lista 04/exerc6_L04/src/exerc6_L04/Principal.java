package exerc6_L04;

import java.util.concurrent.locks.*;

public class Principal {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock(true);
		Condition fornoLivre = lock.newCondition();
		Condition reporGas = lock.newCondition();
		Condition esperarGerente = lock.newCondition();
		
		Botijao botijao = new Botijao(1);
		Forno forno = new Forno(botijao);
		for (int i = 0; i < 3; i++) {
			if(i < 1)
				(new Gerente(forno, botijao)).start();
			(new Padeiro(forno)).start();
		}
	}
}
