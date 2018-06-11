import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

	// no eh interno (decisao)
	private String atributo;


	private Map<String, Node> childs;
			Node parent;

	public Node(){}

	public String getAtributoTeste() { return atributo; }

	public void setAtributoTeste(String atributo) { this.atributo = atributo; }

	public String getClasse() { if (folha) return classe; else return null;}

	public Node getChild() { return childs.get(classe); }

	public Node getChild(String valor) { if(!folha) return childs.get(valor); else return null; }



	public ArrayList<Registro> getDados(){ return registros; }



}
