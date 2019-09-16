package exerc4_L04;

public class Principal {
	public static void main(String[] args) {
		Forno forno = new Forno();
		Gerente gerente = new Gerente(forno);
		for (int i = 0; i < 3; i++) {
			if(i < 1)
				(new Gerente(forno)).start();
			(new Padeiro(forno)).start();
		}
	}
}
