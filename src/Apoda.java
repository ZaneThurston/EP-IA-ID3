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
		double countErros = 0, n = validation.size(), accurTemp = 0;

		while (accurTemp < accur) {
			for (int j = 0; j < validation.size(); j++) {



				Node rootIt = root;
				Aresta aresta = new Aresta();

				while (rootIt != null && aresta != null) {
					aresta = rootIt.getAresta(validation.get(j).getDado(rootIt.getAtributoTeste(), Main.atributos));

					if (aresta.getChild() == null) {
						classificaResult = false;
						break;
					}

					rootIt = aresta.getChild();
				}

				classificaResult = aresta.getClasseMajor().equals(validation.get(j).getClasse());

				if (!classificaResult) countErros++;
			}
			accurTemp = 1 - (countErros / n);
		}
	}


}
