package exerc2_L02;

public class Impressora {
	
	private String nome;
	private TipoImpressora tipo;
	private StatusImpressora status;
	
	public Impressora(String nome, TipoImpressora tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.status = StatusImpressora.AGUARDANDO;
	}
	
	@Override
	public String toString() {
		return this.nome+" do tipo "+this.tipo+" criada com sucesso!";
	}
	
	public synchronized void imprimeDocumento(TipoProcesso tipoProcesso, String doc) {
		this.status = StatusImpressora.IMPRIMINDO;
		try {
			System.out.println("+----------------------+");
			System.out.println("|   "+this.nome+"   |");
			System.out.println("+----------------------+");
			Thread.sleep(10000);
			System.out.println("DOC: "+doc+"\n\n");
		} catch (Exception e) {}
		this.status = StatusImpressora.AGUARDANDO;
		notifyAll();
	}
	
	public boolean checarStatusImpressora() {
		if(this.status == StatusImpressora.AGUARDANDO)
			return true;
		return false;
	}

	public String getNome() {
		return nome;
	}
}
