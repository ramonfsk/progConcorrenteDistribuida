package correio;

import java.util.*;

public class Pombo extends Thread {

	private static int QTDMAX_CARTAS = 5;
	private Cidade cidade;
	private CaixaPostal caixaPostalA, caixaPostalB;
	private Gaiola gaiola;
	private boolean autorizacaoParaVoltar;
	private Vector<Carta> cartas = new Vector<Carta>();
	
	public Pombo(String nome, CaixaPostal caixaPostalA, CaixaPostal caixaPostalB, Gaiola gaiola, Cidade cidade) {
		super(nome);
		this.caixaPostalA = caixaPostalA;
		this.caixaPostalB = caixaPostalB;
		this.gaiola = gaiola;
		this.cidade = cidade;
		this.autorizacaoParaVoltar = false;
	}
	
	@Override
	public String toString() {
		return getName()+" criado com sucesso!";
	}
	
	@Override
	public void run() {
		setPriority(MAX_PRIORITY);
		while(true) {
			entregarCartas();
			voltarParaCidadeA();
		}
	}

	public void entregarCartas() {
		synchronized (caixaPostalA) {
			if(!caixaPostalA.checarQtdCartas(QTDMAX_CARTAS)) { //Não tem cartas suficientes ainda...
				try {
					System.out.println("*** [Não há cartas suficientes, o "+getName()+" vai aguardar na fila...] ***");
					caixaPostalA.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else { //Tem cartas suficientes, vamos entregar!
				System.out.println("*** [Há cartas suficientes, o "+getName()+" vai iniciar a entrega!] ***");
				cartas.addAll(caixaPostalA.retirarCartasParaEntrega(QTDMAX_CARTAS));
				try {
					Thread.sleep(10000); //10s para realizar a entrega...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		cidade = Cidade.B; //Chegou a cidade B
		caixaPostalB.depositarCartas(cartas); //Entregou na caixa postal B!
		cartas.removeAll(cartas); //Removeu todas as cartas do pombo!
		gaiola.entrarNaGaiola(this); //Entrar na gaiola e aguardar tratador...
	}
	
	public void voltarParaCidadeA() {
		synchronized (gaiola) {
			if(!autorizacaoParaVoltar) { //Não pode voltar para cidade
				try {
					System.out.println("*** [Ainda não autorizado a voltar, o "+getName()+" vai aguardar na gaiola!] ***");
					gaiola.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("*** ["+getName()+" autorizando, voltando agora!] ***");
				cidade = Cidade.A; //Altera em qual cidade ele está
				try {
					Thread.sleep(10000); //10s para realizar a travessia...
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("*** ["+this.getName()+" retornou!] ***");
			}
		}
	}
	
	public synchronized void autorizaVolta() {
		autorizacaoParaVoltar = true;
	}

	public Cidade getCidade() {
		return cidade;
	}
}
