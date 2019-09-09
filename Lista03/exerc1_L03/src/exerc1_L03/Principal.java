package exerc1_L03;

import java.util.*;

public class Principal {	
	public static void main(String[] args) {
		
		Vector<Carro> carros = new Vector<Carro>();

		Carro c1 = new Carro("Honda Civic");
		carros.add(c1);
		Carro c2 = new Carro("Pegout 306");
		carros.add(c2);
		Carro c3 = new Carro("Citroen C4");
		carros.add(c3);
		Carro c4 = new Carro("Fiat Uno v.Escadinha TURBO");
		carros.add(c4);
		
		for(int i = 0; i < 30; i++) {
			if(i < 10)
				(new Pessoa("Professor "+(i+1), TipoPessoa.Professor, carros.get((i+1)%4))).start();
			if(i < 15)
				(new Pessoa("Funcionario "+(i+1), TipoPessoa.Funcionario, carros.get((i+1)%4))).start();
			(new Pessoa("Aluno "+(i+1), TipoPessoa.Aluno, carros.get((i+1)%4))).start();
		}
	}
}
