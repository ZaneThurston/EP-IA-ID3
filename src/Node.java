import java.util.List;

public class Node {

	private List<Registro> registros,
			float entropia;

	public void setEntropia(){}

	public float getEntropia(){ return entropia; }

	public List<Registro> getDados(){ return registros; }

	public int setDados(List<Registro> registros){ this.registros = registros; }

}
