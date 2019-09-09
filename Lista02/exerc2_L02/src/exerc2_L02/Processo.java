package exerc2_L02;

public class Processo extends Thread {
	
	private String doc;
	private TipoProcesso tipo;
	private Impressora impressora;
	
	public Processo(String nome, String doc, TipoProcesso tipo, Impressora impressora) {
		super(nome);
		this.doc = doc;
		this.tipo = tipo;
		this.impressora = impressora;
	}
	
	@Override
	public String toString() {
		return this.getName()+" do tipo "+this.tipo+" criado com sucesso!";
	}
	
	@Override
	public void run() {
		while(true)
			enviarDocImpressora();
	}
	
	public void enviarDocImpressora() {
		synchronized (this.impressora) {
			if(this.tipo == TipoProcesso.PAB) {
				this.iniciarImpressao();
			} else {
				try {
					this.impressora.wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
				this.iniciarImpressao();
			}
		}
	}
	
	public void iniciarImpressao() {
		if(!this.impressora.checarStatusImpressora()) {
			try {
				System.out.println("*** ["+this.impressora.getNome()+" ocupada!] ***");
				this.impressora.wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		} else {
			System.out.println("*** ["+this.impressora.getNome()+" liberada!] ***");
			System.out.println("*** ["+this.impressora.getNome()+" inciando impressao!] ***");
			this.impressora.imprimeDocumento(this.tipo, this.doc);
			System.out.println("*** ["+this.impressora.getNome()+" finalizou impressao!] ***");
		}
	}
}
