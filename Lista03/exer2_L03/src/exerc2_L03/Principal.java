package exerc2_L03;

public class Principal {
	public static void main(String[] args) {
		Barbearia b = new Barbearia("Barbearia do Jucá", 4);
		/*(new Barbeiro("André", TipoPessoa.Barbeiro, b)).run();*/
		(new Cliente("Cliente 01", TipoPessoa.Cliente, b)).run();
	}
}
