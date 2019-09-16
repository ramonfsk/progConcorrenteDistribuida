package exerc2_L02;

import java.util.Random;

public class Principal {
	public static void main(String[] args) {
		Impressora ImpTipoA = new Impressora("ImpressoraTipo_A", TipoImpressora.A);
<<<<<<< HEAD
		Impressora ImpTipoB = new Impressora("ImpressoraTipo_B", TipoImpressora.B);
		
		(new Processo("Processo_PA","ABCDEF123456", TipoProcesso.PA, ImpTipoA)).start();
		(new Processo("Processo_PB","ZZZZZZ999999", TipoProcesso.PB, ImpTipoB)).start();
		Random r = new Random();
		(new Processo("Processo_PAB","AAAAAA000000", TipoProcesso.PAB, r.nextInt(1) == 0 ? ImpTipoA : ImpTipoB)).start();
=======
		Impressora ImpTipoB = new Impressora("ImpressoraTipo_A", TipoImpressora.B);
		
		(new Processo("Processo_A","ABCDEF123456", TipoProcesso.PA, ImpTipoA)).start();
		(new Processo("Processo_A","ZZZZZZ999999", TipoProcesso.PB, ImpTipoA)).start();
		Random r = new Random();
		(new Processo("Processo_A","AAAAAA000000", TipoProcesso.PAB, r.nextInt(1) == 0 ? ImpTipoA : ImpTipoB)).start();
>>>>>>> refs/remotes/origin/master
	}
}
