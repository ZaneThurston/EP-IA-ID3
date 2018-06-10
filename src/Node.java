import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

	private boolean folha;

	// no eh interno (decisao)
	private Atributo atributo;
	private ArrayList<Registro> registros;

	// no eh folha (classificacao)
	private String classe;


	private Map<String, Node> childs;
			Node parent;

	public Node(){}

	public void setDados(ArrayList<Registro> registros){ this.registros = registros; }

	public void setPai(Node pai) { this.parent = pai; }

	public Atributo getAtributoTeste() { return atributo; }

	public String getClasse() { if (folha) return classe; else return null;}

	public Node getChild() { return childs.get(classe); }

	public Node getChild(String valor) { if(!folha) return childs.get(valor); else return null; }



	public ArrayList<Registro> getDados(){ return registros; }



}
