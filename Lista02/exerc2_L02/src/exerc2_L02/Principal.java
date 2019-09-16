package exerc2_L02;

import java.util.Random;

public class Principal {
	public static void main(String[] args) {
		Impressora ImpTipoA = new Impressora("ImpressoraTipo_A", TipoImpressora.A);
		Impressora ImpTipoB = new Impressora("ImpressoraTipo_B", TipoImpressora.B);
		
		(new Processo("Processo_PA","ABCDEF123456", TipoProcesso.PA, ImpTipoA)).start();
		(new Processo("Processo_PB","ZZZZZZ999999", TipoProcesso.PB, ImpTipoB)).start();
		Random r = new Random();
		(new Processo("Processo_PAB","AAAAAA000000", TipoProcesso.PAB, r.nextInt(1) == 0 ? ImpTipoA : ImpTipoB)).start();
	}
}
