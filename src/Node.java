import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

	// no eh interno (decisao)
	private String atributo;
	private HashMap<String, Aresta> arestas = new HashMap<String, Aresta>();

	public Node(){}

	public String getAtributoTeste() { return atributo; }

	public void setAtributoTeste(String atributo) { this.atributo = atributo; }

	//public String getClasse() { if (folha) return classe; else return null;}

	public void criaAresta(String valor, ArrayList<Registro> registros, Node seguinte) {
		Aresta a = new Aresta(valor, registros, seguinte);
		this.arestas.put(valor, a);
	}

	public Aresta getAresta(String valor) { return arestas.get(valor); }
	public HashMap<String, Aresta> getSetArestas() { return arestas; }
}
