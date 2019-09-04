package correio;

import java.util.*;

public class Pessoa extends Thread {

	private Vector<Carta> cartas = new Vector<Carta>();
	private CaixaPostal caixaPostal;
	
	public Pessoa(String nome, CaixaPostal caixaPostal) {
		super(nome);
		this.caixaPostal = caixaPostal;
	}
	
	@Override
	public String toString() {
		return getName()+" criado com sucesso!";
	}
	
	@Override
	public void run() {
		
	}
	
	public void dpstrCartas() {
		caixaPostal.depositarCartas(cartas);
	}
	
	public void escreverCarta(String nomeCarta) {
		Carta carta = new Carta(nomeCarta);
		cartas.add(carta);
	}
	
}
