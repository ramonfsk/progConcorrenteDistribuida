package primo;

public class Conjunto extends Thread {
	private long inicio;
	private long fim;
	private long somaPrimos;

	public Conjunto(long inicio, long fim) {
		this.inicio = inicio;
		this.fim = fim;
	}
	
	public void run() {
		this.somaPrimos = this.somarPrimos();
	}
	
	public long somarPrimos() {
		long soma=0;
		for (long i=this.inicio; i<=this.fim; i++)
			if (this.isPrimo(i))
				soma += i;
		return soma;
	}
	
	public boolean isPrimo(long n) {
		long i=2;
		while (i<n)
			if (n % i++ == 0)
				return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conjunto [inicio=" + inicio + ", fim=" + fim + "]";
	}

	public long getSomaPrimos() {
		return somaPrimos;
	}

}
