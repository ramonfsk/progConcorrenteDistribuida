package exerc1;

import java.util.ArrayList;

public class Executora {
	public static void main(String[] args) {
		long tempoInicial = System.currentTimeMillis();
		ArrayList<Conjunto> cnjts = new ArrayList<Conjunto>();
		
		try {
			long numInicial = View.lerDadoLong("Threads - Exercicio 01", "Informe o numero inicial: ");
			long numFinal = View.lerDadoLong("Threads - Exercicio 01", "Informe o numero final: ");
			long qtdThreads = View.lerDadoLong("Threads - Exercicio 01", "Deseja particionar a execução em quantas threads?");
			long tamanho = (numFinal - numInicial) + 1;
			long thread = tamanho/qtdThreads;
			
			for (int i = 0; i < qtdThreads; i++) {
				long numLimite = numInicial + thread;
				if(numLimite > numFinal)
					numLimite = numFinal;
				cnjts.add(new Conjunto(numInicial, numLimite));
				numInicial += thread + 1;
			}
			
			for (Conjunto cnjt : cnjts) {
				System.out.println(cnjt.toString());
				cnjt.start();
			}
			
			for (Conjunto cnjt : cnjts)
				try {
					cnjt.join();
				} catch (InterruptedException e) {
					View.exibirMsgErro("ERROR", e.getMessage());
				}
			
			long soma = 0;
			for (Conjunto cnjt : cnjts)
				soma += cnjt.getSomaNumsPrimos();
			System.out.println("Soma dos números = "+soma+".");
			//View.exibirMsg("Threads - Exercicio 01", "Soma dos números = "+soma+".");
			
			long tempoTotal = System.currentTimeMillis() - tempoInicial;
			System.out.println("O programa levou "+tempoTotal+" milissegundos para executar.");
			//View.exibirMsg("Threads - Exercicio 01", "O programa levou "+tempoTotal+" milissegundos para executar.");
			
		} catch (Exception e) {
			View.exibirMsgErro("ERROR", e.getMessage());
		}
	}

}
