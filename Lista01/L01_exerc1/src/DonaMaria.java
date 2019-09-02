
public class DonaMaria extends Thread{
	private Tigela tigela;
	private boolean gatoMiou = false;
	
	public DonaMaria(String nome, Tigela tigela) {
		super(nome);
		setTigela(tigela);
	}
	
	@Override
	public String toString() {
		return getName()+" criada com sucesso!";
	}
	
	@Override
	public void run() {
		while(true) {
			if(this.gatoMiou == true)
				encherTigela();
			dormir();
		}
	}
	
	public synchronized void gatoMiou() {
		this.gatoMiou = true;
		System.out.println("var gatoMiou = "+gatoMiou);
	}
	
	public void encherTigela() {
		this.tigela.reporRacao();
		System.out.println("*** "+getName()+" repôs a ração na tigela! ***");
	}
	
	public void dormir() {
		int tempoDormir = (int)(Math.random() * 10000);
		try {
			System.out.println(getName()+" vai dormir por "+tempoDormir+"ms.");
			Thread.sleep(tempoDormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Tigela getTigela() {
		return tigela;
	}
	public void setTigela(Tigela tigela) {
		this.tigela = tigela;
	}
}
