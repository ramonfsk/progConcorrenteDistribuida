
public class Tigela {
	private int tamanhoTigela;
	private int qtdRacao;
	
	public Tigela(int tamanhoTigela) {
		setTamanhoTigela(tamanhoTigela);
	}
	
	@Override
	public String toString() {
		return "A tigela tem a capacidade de "+getTamanhoTigela()+"g de ração!";
	}
	
	public synchronized boolean comerRacao(int qtd) {
		if(qtd <= 0 || qtd > this.qtdRacao) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Racao insuficiente...");
			return false;
		} else {
			this.qtdRacao -= qtd;
			notifyAll();
			return true;
		}
	}
	
	public synchronized void reporRacao() {
		this.qtdRacao = this.tamanhoTigela;
		notifyAll();
	}
	
	public boolean estaVazia() {
		if(this.qtdRacao <= 0)
			return true;
		return false;
	}
	
	public int getTamanhoTigela() {
		return tamanhoTigela;
	}
	public void setTamanhoTigela(int tamanhoTigela) {
		Utils.validaInt(tamanhoTigela);
		this.tamanhoTigela = tamanhoTigela;
	}
	public int getQtdRacao() {
		return qtdRacao;
	}
	public void setQtdRacao(int qtdRacao) {
		Utils.validaInt(qtdRacao);
		this.qtdRacao = qtdRacao;
	}
}
