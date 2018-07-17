import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Apoda {

	ArrayList<Registro> test;
	ArrayList<Registro> validation;
	ArrayList<Registro> training;

    public Apoda() {
		Main.kFold(3, true);
		test = new ArrayList<>();
		validation = new ArrayList<>();
		training = new ArrayList<>();

		test.addAll(Main.testRegs);
		validation.addAll(Main.foldedExamples.get(0));
		training.addAll(Main.foldedExamples.get(1));

    }
    
    public Node criaArvore(ID3 id3) {
		Node root = id3.generateTree(training, Main.atributos);
		return root;
	}

	public double testArvore(Node root) {
    	boolean classificaResult = false;
    	double countErros = 0, n = test.size();
		for (int j = 0; j < test.size(); j++) {
			classificaResult = Main.classifica(root, test.get(j));
			if (!classificaResult) countErros++;
		}
		return 1 - (countErros / n);
	}

	public void podaArvore(Node root, double accur) {
		boolean classificaResult = false;
		double countErros = 0, accurTemp = 0;
		int n = validation.size();


		while (accurTemp < accur) {
			// processo basico do algoritmo:
			// seleciona no para remocao (busca em profundidade por no folha), marca nos visitados
			// remove no, testa com conjunto de validacao
			// se acurTemp diminuiu, recoloca no
			// senao, mantem no removido
			// repeat


			for (int j = 0; j < validation.size(); j++) {
				Node rootIt = root;
				Aresta arestaIt = new Aresta();
				while (rootIt != null) {
					arestaIt = rootIt.getAresta(validation.get(j).getDado(rootIt.getAtributoTeste(), Main.atributos));
					if (arestaIt != null) {
						if (arestaIt.getChild() == null) break;

						rootIt = arestaIt.getChild();
					} else break;
				}

				if (arestaIt != null) classificaResult = arestaIt.getClasseMajor().equals(validation.get(j).getClasse());

				if (!classificaResult) countErros++;
			}

			if (1 - (countErros / n) < accurTemp) break;
			accurTemp = 1 - (countErros / n);


		}

	}


}
