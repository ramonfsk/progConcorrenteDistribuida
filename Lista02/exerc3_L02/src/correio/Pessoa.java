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
		setPriority(MIN_PRIORITY);
		while(true) {
			escreverCarta();
			dpstrCartas();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void dpstrCartas() {
		caixaPostal.depositarCartas(cartas);
	}
	
	public void escreverCarta() {
		Random r = new Random();
		String alfabeto = "S3XY01245";
		String nomeRand = "Carta ";
		for (int i = 0; i < 4; i++)
			nomeRand += (alfabeto.charAt(r.nextInt(alfabeto.length())));
		
		Carta carta = new Carta(nomeRand);
		cartas.add(carta);
	}
	
}
