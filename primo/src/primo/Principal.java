package primo;

import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		List<Conjunto> conjuntos = new ArrayList<Conjunto>();
		long qtdPedacos=1000;
		long ini=1, fim=99999;
		long tam=(fim-ini)+1;
		long pedaco=tam/qtdPedacos;
		
		for (int i=0; i<qtdPedacos; i++) {
			long f=ini+pedaco;
			if (f > fim)
				f = fim;
			conjuntos.add(new Conjunto(ini, f));
			ini = ini + pedaco + 1;
		}
		
		for (Conjunto c : conjuntos) {
			System.out.println(c.toString());
			c.start();
		}
		
		for (Conjunto c : conjuntos)
			try {
				c.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		long soma=0;
		for (Conjunto c : conjuntos)
			soma += c.getSomaPrimos();
		System.out.println("--> "+ soma);
		
		long tempo = System.currentTimeMillis() - inicio;
		System.out.println("--> "+ tempo +" milisegundos");
	}

}
