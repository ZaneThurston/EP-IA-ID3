import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

	private boolean folha;

	// no eh interno (decisao)
	private Atributo atributo;
	private double ganho;
	private ArrayList<Registro> registros;

	// no eh folha (classificacao)
	private String valor;


	private Map<String, Node> childs;
			Node parent;
			float entropia;

	public void setPai(Node pai) { this.parent = pai; }

	public void setEntropia(double entropia, ListDisAtributos setTreino){}

	public float getEntropia(){ return entropia; }

	public Atributo getAtributoTeste() { return atributo; }

	public String getValor() { if (folha) return valor; else return null;}

	public Node getChild() { return childs.; }

	public Node getChild(String valor) { if(!folha) return childs.get(valor); else return null; }



	public List<Registro> getDados(){ return registros; }

	public int setDados(ArrayList<Registro> registros){ this.registros = registros; }

}
