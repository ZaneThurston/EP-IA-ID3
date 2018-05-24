import java.util.List;

public class Node {

	private List<Registro> registros,
			Node childs[];
			Node parent;
			float entropia;

	public void setPai(Node pai) { this.parent = pai; }

	public void setEntropia(double entropia, ListDisAtributos setTreino){}

	public float getEntropia(){ return entropia; }

	public List<Registro> getDados(){ return registros; }

	public int setDados(List<Registro> registros){ this.registros = registros; }

}
