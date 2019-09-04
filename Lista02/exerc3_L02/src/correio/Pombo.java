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
		while(true) {
			entregarCartas();
			voltarParaCidadeA();
		}
	}

	public void entregarCartas() {
		if(!checarCaixaPostal()) { //Não tem cartas suficientes ainda...
			synchronized (caixaPostalA) {
				try {
					caixaPostalA.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else { //Tem cartas suficientes, vamos entregar!
			cartas.addAll(caixaPostalA.retirarCartasParaEntrega(QTDMAX_CARTAS));
			try {
				Thread.sleep(10000); //10s para realizar a entrega...
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cidade = Cidade.B; //Chegou a cidade B
			caixaPostalB.depositarCartas(cartas); //Entregou na caixa postal B!
			cartas.removeAll(cartas); //Removeu todas as cartas do pombo!
			gaiola.entrarNaGaiola(this); //Entrar na gaiola e aguardar tratador...
		}
	}
	
	public void voltarParaCidadeA() {
		if(!autorizacaoParaVoltar) { //Não pode voltar para cidade 
			synchronized (gaiola) {
				try {
					gaiola.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			cidade = Cidade.A; //Altera em qual cidade ele está
			try {
				Thread.sleep(10000); //10s para realizar a travessia...
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void autorizaVolta() {
		autorizacaoParaVoltar = true;
	}
	
	public boolean checarCaixaPostal() {
		if(caixaPostalA.getCartas().size() < QTDMAX_CARTAS)
			return false;
		else
			return true;
	}

	public Cidade getCidade() {
		return cidade;
	}
}
