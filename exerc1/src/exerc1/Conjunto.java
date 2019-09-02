package exerc1;

public class Conjunto extends Thread {
	//ATTRIBUTES
	private long inicio;
	private long fim;
	private long somaNumsPrimos;
	//CONSTRUCTOR
	public Conjunto(long inicio, long fim){
		setInicio(inicio);
		setFim(fim);
	}
	//METHODS
	public void run() {
		this.somaNumsPrimos = somarNumPrimos();
	}
	
	@Override
	public String toString() {
		return "Conjunto [inicio = "+getInicio()+", fim = "+getFim()+"]";
	}
	
	public long somarNumPrimos() {
		long soma = 0;
		for (long i = getInicio(); i <= getFim(); i++)
			if(ehPrimo(i))
				soma += i;
		return soma;
	}
	
	public boolean ehPrimo(long num) {
		long i = 2;
		while (i < num)
			if(num % i++ == 0)
				return false;
		return true;
			
	}
	//GETTERS & SETTERS
	public long getInicio() {
		return inicio;
	}
	public void setInicio(long inicio) {
		this.inicio = inicio;
	}
	public long getFim() {
		return fim;
	}
	public void setFim(long fim) {
		this.fim = fim;
	}
	public long getSomaNumsPrimos() {
		return somaNumsPrimos;
	}
}
