package correio;

import java.util.*;

public class CaixaPostal {

	private String nome;
	private Cidade cidade;
	private Vector<Carta> cartas = new Vector<Carta>();
	
	public CaixaPostal(String nome, Cidade cidade) {
		this.nome = nome;
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return nome+" criada com sucesso!";
	}
	
	public synchronized void depositarCartas(Vector<Carta> cartas) {
		for(Carta carta : cartas) {
			this.cartas.add(carta);
			System.out.println(carta.getNome()+" depositada com sucesso na cidade "+cidade+"! \nTOTAL: "+this.cartas.size()+" cartas.");
		}
		//this.cartas.addAll(cartas);
		notifyAll();
	}
	
	public synchronized Vector<Carta> retirarCartasParaEntrega(int qtdCartas) {
		Vector<Carta> crtsTmp = new Vector<Carta>();
		
		for(int i = 0; i < qtdCartas; i++) {
			System.out.println("Retirando a "+cartas.get(i).getNome()+" da "+nome+"...");
			crtsTmp.add(cartas.get(i));
		}
		
		cartas.removeAll(cartas);
		
		notifyAll();
		return crtsTmp;
	}
	
	public boolean checarQtdCartas(int qtdCartas) {
		if(cartas.size() < qtdCartas)
			return false;
		else
			return true;
	}
	
	public Vector<Carta> getCartas() {
		return cartas;
	}
}
