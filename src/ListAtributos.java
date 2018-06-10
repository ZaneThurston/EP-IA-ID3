import java.util.ArrayList;

// Classe que enumera uma lista dos atributos do conjunto de dados
// possui metodos para manipular esta lista e configurá-la
public class ListAtributos {
	private ArrayList<Atributo> atributos;

	public ListAtributos() {}

	public void setAtributos(String[] campos) {
		int i = 0;
		while (i < campos.length) {
			atributos.add(new Atributo(campos[i]));
			i++;
		}
	}

	public int getQtdeAtributos() {
		return atributos.size();
	}


}
