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
		for (Carta carta : cartas) {
			this.cartas.add(carta);
			System.out.println(carta+" depositada com sucesso na cidade "+cidade+"! \nTOTAL: "+this.cartas.size()+" cartas.");
		}
	}
	
	public synchronized Vector<Carta> retirarCartasParaEntrega(int qtdCartas) {
		Vector<Carta> crtsTmp = new Vector<Carta>();
		
		for (int i = 0; i < qtdCartas; i++) {
			crtsTmp.add(cartas.get(i));
			cartas.remove(i);
		}
		
		return crtsTmp;
	}
	
	public Vector<Carta> getCartas() {
		return cartas;
	}
}
