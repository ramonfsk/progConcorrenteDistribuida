package exerc1_L04;

public class Forno {
	
	private String nome;
	private Pao pao;
	
	public Forno(String nome) {
		this.nome = nome;
	}
	
	public void colocarPaoNoForno(String nome, Pao pao) {
		System.out.println("O "+pao.getNome()+" foi colocado no "+this.nome+" pelo "+nome+"!");
		this.pao = pao;
		System.out.println("*** [O "+this.nome+" ESTÁ OCUPADO!] ***");
	}
	
	public void assarPaoNoForno() {
		try {
			long tmpRnd = (long) (Math.random() * (10000 + 1));
			System.out.println("O "+this.pao.getNome()+" vai assar no forno por "+(tmpRnd/1000)+"s.");
			Thread.sleep(tmpRnd);
			this.pao.setAssado(true);
			System.out.println("*** "+this.pao.getNome()+" está pronto! ***");
			System.out.println("*** [O "+this.nome+" ESTÁ LIBERADO!] ***");
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
}
