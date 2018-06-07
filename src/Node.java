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
			double entropia;

	public Node(){}

	public void setPai(Node pai) { this.parent = pai; }

	public void setEntropia(double entropia) { this.entropia = entropia; }

	public double getEntropia(){ return entropia; }

	public Atributo getAtributoTeste() { return atributo; }

	public String getValor() { if (folha) return valor; else return null;}

	public Node getChild() { return childs.get(valor); }

	public Node getChild(String valor) { if(!folha) return childs.get(valor); else return null; }



	public ArrayList<Registro> getDados(){ return registros; }

	public void setDados(ArrayList<Registro> registros){ this.registros = registros; }

}
