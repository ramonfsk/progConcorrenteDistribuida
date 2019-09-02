
public class Gato extends Thread {
	private int qtdRacaoComida;
	private Tigela tigela;
	private DonaMaria donaMaria;
	
	public Gato(String nome, Tigela tigela, DonaMaria donaMaria) {
		super(nome);
		setTigela(tigela);
		setDonaMaria(donaMaria);
	}

	@Override
	public String toString() {
		return "O gato "+getName()+" pertence a "+getDonaMaria().getName()+"!";
	}
	
	@Override
	public void run() {
		while(true) {
			this.comer();
			//this.toString();
			this.dormir();
		}
	}
	
	public void comer() {
		if(this.tigela.estaVazia()) {
			this.donaMaria.gatoMiou();
			System.out.println(getName()+" miando...");
			synchronized (this.tigela) {
				try {
					this.tigela.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			dormir();
		} else {
			this.qtdRacaoComida = Utils.randIntNew(1, this.tigela.getTamanhoTigela());
			if(this.tigela.comerRacao(getQtdRacaoComida()))
				System.out.println("O "+getName()+" comeu "+getQtdRacaoComida()+"g da tigela!");
			else
				System.out.println("O "+getName()+" não conseguiu comer, ração insuficiente...");
		}
	}
	
	public void dormir() {
		int tempoDormir = (int)(Math.random() * 60000);
		try {
			System.out.println(this.getName()+" vair dormir por "+tempoDormir+"ms.");
			Thread.sleep(tempoDormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getQtdRacaoComida() {
		return qtdRacaoComida;
	}
	public void setQtdRacaoComida(int qtdRacaoComida) {
		this.qtdRacaoComida = qtdRacaoComida;
	}
	public Tigela getTigela() {
		return tigela;
	}
	public void setTigela(Tigela tigela) {
		this.tigela = tigela;
	}
	public DonaMaria getDonaMaria() {
		return donaMaria;
	}
	public void setDonaMaria(DonaMaria donaMaria) {
		this.donaMaria = donaMaria;
	}
}
