package banco;

public class PoolDeConexao {
	public String getConnection() {
		System.out.println("Emprestando conex�o");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		return "connection";
	}
}
