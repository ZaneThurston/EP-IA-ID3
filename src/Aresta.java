import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Aresta {
	private String valor;
	private ArrayList<Registro> registros;
	private Node child;

	public Aresta(String valor, ArrayList<Registro> registros, Node child) {
		this.valor = valor;
		this.registros = registros;
		this.child = child;
	}

	public Node getRaiz() { return child; }

	public String getClasseMajor() {

		Registro regAtu;
		String classeAtu, majorClass = "";
		int countMaior = 0, count;

		HashMap<String, Integer> classes = new HashMap<>();
		Iterator<Registro> it = registros.iterator();
		while (it.hasNext()) {
			regAtu = it.next();
			classeAtu = regAtu.getClasse();
			if (!classes.containsKey(classeAtu)) classes.put(classeAtu, 0);
			classes.put(classeAtu, classes.get(classeAtu) + 1);
		}

		Iterator<String> classIt = classes.keySet().iterator();
		while (classIt.hasNext()) {
			classeAtu = classIt.next();
			count = classes.get(classeAtu);
			if (count > countMaior) {
				countMaior = count;
				majorClass = classeAtu;
			}
		}
		return majorClass;
	}

}
