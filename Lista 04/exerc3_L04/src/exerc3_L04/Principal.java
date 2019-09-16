package exerc3_L04;

public class Principal {
	public static void main(String[] args) {
		
		Forno forno = new Forno("Forno");
		for (int i = 0; i < 3; i++)
			(new Padeiro("Padeiro "+(i+1), forno)).start();
	}
}
