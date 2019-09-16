package exerc2_L03;

import java.util.*;

public class Barbearia {
	
	private String nome;
	private int qtdMaxCadeiras;
	private int cntClientesEsperando;
	private boolean todosOsBarbeirosEstaocupados;
	private Vector<Barbeiro> barbeiros;
	private Vector<Cliente> clientes;
	
	public Barbearia(String nome, int qtdMaxCadeiras) {
		this.nome = nome;
		this.qtdMaxCadeiras = qtdMaxCadeiras;
		this.cntClientesEsperando = 0;
		this.todosOsBarbeirosEstaocupados = false;
		new Vector<Barbeiro>();
		new Vector<Cliente>();
	}
	
	@Override
	public String toString() {
		return this.nome+" criada com sucesso!";
	}
	
	public synchronized void addBarbeiro(Barbeiro barbeiro) {
		this.barbeiros.add(barbeiro);
	}
	
	public synchronized void remBarbeiro() {
		this.barbeiros.remove(this.cntClientesEsperando);
	}
	
	public synchronized void addCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public synchronized void remCliente(int index) {
		this.barbeiros.remove(this.cntClientesEsperando);
	}
	
	public synchronized void colocarClienteEmEspera(Cliente cliente) {
		this.clientes.add(cliente);
		this.cntClientesEsperando++;
		System.out.println("O "+cliente.getNome()+" entrou na fila de espera, sentou cadeira "+this.cntClientesEsperando+".");
	}
	
	public synchronized void retirarClienteDaEspera() {
		
	}
	
	public synchronized boolean verificaSeFilaDeEsperaEstaCheia() {
		if(this.cntClientesEsperando == this.qtdMaxCadeiras)
			return true;
		return false;
	}

	public synchronized boolean verificaSeTodosOsBarbeirosEstaoOcupados() {
		int qtdBarbeiros = 0;
		for (Barbeiro barbeiro : barbeiros) {
			if(barbeiro.isDormindo() == false)
				qtdBarbeiros++;
		}
		if(this.barbeiros.size() == qtdBarbeiros)
			return true;
		return false;
	}
	
	public String getNome() {
		return nome;
	}

	public Vector<Barbeiro> getBarbeiros() {
		return barbeiros;
	}

	public Vector<Cliente> getClientes() {
		return clientes;
	}
}
