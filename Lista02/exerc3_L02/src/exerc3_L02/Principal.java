package exerc3_L02;

import correio.*;

public class Principal {
	public static void main(String[] args) {
		CaixaPostal cxPstA = new CaixaPostal("CaixaPostal_A", Cidade.A);
		CaixaPostal cxPstB = new CaixaPostal("CaixaPostal_B", Cidade.B);
		
		for (int i = 0; i < 13; i++)
			(new Pessoa("Pessoa "+(i+1), cxPstA)).start();
		
		Gaiola gaiola = new Gaiola();
		for (int i = 0; i < 6; i++)
			(new Pombo("Pombo "+(i+1), cxPstA, cxPstB, gaiola, Cidade.A)).start();
		(new Tratador("Tratador", gaiola)).start();
	}
}
