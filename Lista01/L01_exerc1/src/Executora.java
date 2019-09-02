import java.util.ArrayList;

public class Executora {
	public static void main(String[] args) {
		Tigela tigela = new Tigela(100);
		System.out.println(tigela.toString());
		DonaMaria donaMaria = new DonaMaria("Dona Maria",tigela);
		System.out.println(donaMaria.toString());
		
		int nroGatos = 12;
		ArrayList<Gato> gatos = new ArrayList<Gato>();
		for(int i = 0; i < nroGatos; i++) {
			gatos.add(new Gato("Gato("+(i+1)+")", tigela, donaMaria));
			//System.out.println(gatos.toString());
		}
		
		donaMaria.start();
		for(Gato gato : gatos)
			gato.start();
	}
}
