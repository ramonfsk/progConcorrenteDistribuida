package banco;

public class TarefaAcessaBancoProcedimento implements Runnable {

	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;

	public TarefaAcessaBancoProcedimento(PoolDeConexao pool, GerenciadorDeTransacao tx) {
		this.pool = pool;
		this.tx = tx;
	}

	@Override
	public void run() {
		synchronized (this.pool) {
			System.out.println("Peguei a conex�o!");
			pool.getConnection();
			synchronized (this.tx) {
				System.out.println("Come�ando a tx!");
				tx.begin();
			}
		}
	}

}
