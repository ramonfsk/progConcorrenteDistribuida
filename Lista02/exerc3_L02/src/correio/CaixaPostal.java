package correio;

import java.util.*;

public class CaixaPostal {
	
	private String nome;
	private Cidade cidade;
	private int qtdCartas;
	private Vector<Carta> cartas = new Vector<Carta>();
	
	public CaixaPostal(String nome, Cidade cidade) {
		this.nome = nome;
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return nome+" criada com sucesso!";
	}
	
	public void depositarCartas(Vector<Carta> cartas) {
		for (Carta carta : cartas) {
			this.cartas.add(carta);
			//System.out.println(carta+" depositada com sucesso!");
		}
	}
}
