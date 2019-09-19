package exerc5_L04;

public class Principal {
	public static void main(String[] args) {
		Botijao botijao = new Botijao(1);
		Forno forno = new Forno(botijao);
		for (int i = 0; i < 3; i++) {
			if(i < 1)
				(new Gerente(forno, botijao)).start();
			(new Padeiro(forno)).start();
		}
	}
}
