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
		double countErros = 0, n = test.size();
		for (int j = 0; j < test.size(); j++) {
			classificaResult = Main.classifica(root, validation.get(j));
			if (!classificaResult) countErros++;
		}
	}


}
