package correio;

import java.util.*;

public class Gaiola {
	
	private Vector<Pombo> pombos = new Vector<Pombo>();
	
	public Gaiola() {};
	
	public synchronized void entrarNaGaiola(Pombo pombo) {
		pombos.add(pombo);
		System.out.println(pombo.getName()+" está preso na gaiola...");
	}
	
	public synchronized void abreGaiola(Vector<Pombo> pombos) {
		pombos.removeAll(pombos);
		System.out.println("Todos os pombos sairam da gaiola!");
		pombos.notifyAll();
	}

	public Vector<Pombo> getPombos() {
		return pombos;
	}
}
