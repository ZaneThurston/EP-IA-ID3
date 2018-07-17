import java.util.*;

public class Node {

	// no eh interno (decisao)
	private String atributo;
	private HashMap<String, Aresta> arestas;

	private boolean visitado;

	public Node(){
		atributo = "";
		visitado = false;
		arestas = new HashMap<String, Aresta>();
	}

	public String getAtributoTeste() { return atributo; }

	public void setAtributoTeste(String atributo) { this.atributo = atributo; }

	//public String getClasse() { if (folha) return classe; else return null;}

	public void criaAresta(String valor, ArrayList<Registro> registros, Node seguinte) {
		Aresta a = new Aresta(valor, registros, seguinte);
		this.arestas.put(valor, a);
	}

	public Aresta getAresta(String valor) { return arestas.get(valor); }

	// Itera sobre o conjunto de arestas para retornar o primeiro filho nao visitado
	public Aresta getNonVisitedAresta() {
		String valor = "";
		// falta resolver o q exatamente usar para iterar sobre o hashmap
		Iterator arestaIterator = arestas.entrySet().iterator();
		while (arestaIterator.hasNext()) {
			HashMap.Entry a = arestaIterator.next();
			if ()
		}


		return arestas.get(valor);
	}
	public HashMap<String, Aresta> getSetArestas() { return arestas; }

	public boolean isVisitado() {return visitado;}

	public void setVisitado(boolean visitado) {this.visitado = visitado;}
}
