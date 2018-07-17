import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {

	static ArrayList<Atributo> atributos = new ArrayList<>();
    static ArrayList<Registro> registros = new ArrayList<>();

    // para o k-fold cross validation:
    static ArrayList<Registro> testRegs = new ArrayList<>();
    static HashMap<Integer, ArrayList<Registro>> foldedExamples = new HashMap<>();

	static int numNodes = 0;
	static int numArestas = 0;

	private static final double CONFIDENCE_INTERVAL_CONSTANT = 1.96;


    // Dado um exemplo e a raiz do classificador, valida o exemplo no classificador
    public static boolean classifica(Node root, Registro exemplo) {

        Node rootIt = root;
        Aresta aresta = new Aresta();

        while (rootIt != null) {
            aresta = rootIt.getAresta(exemplo.getDado(rootIt.getAtributoTeste(), atributos));

            if (aresta == null) return false;
            if (aresta.getChild() == null) break;

            rootIt = aresta.getChild();
        }

        return aresta.getClasseMajor().equals(exemplo.getClasse());
    }


    // Dado um int K, separa o conjunto de exemplos em K subconjuntos, sendo 1 de teste e k-1 de treino
    public static void kFold(int k, boolean permuta) {
        if (k == 0 || k > registros.size()) {
            return;
        }
        int[] indices;
        int sizeFregs, n;
        n = registros.size();

        if (permuta) {
            indices = new Random().ints(0, n).distinct().limit(n).toArray();
        }
        else {
            indices = new int[n];
            for (int i = 0; i<indices.length; i++) indices[i] = i;
        }

        sizeFregs = n/k;

        for (int i = 0; i < sizeFregs; i++) {
            testRegs.add(registros.get(indices[i]));
        }

        int ini, fim;
        for (int i = 0; i < k-1; i++) {
            foldedExamples.put(i, new ArrayList<>());
            fim = (i+2) * sizeFregs;
            for (ini = (i+1) * sizeFregs; ini < fim; ini++) {
                foldedExamples.get(i).add(registros.get(indices[ini]));
            }
        }
    }

    // Apos separados os subconjuntos, o crossValidation calcula o erro medio do classificador
    public static double crossValidation() {
        double countErros = 0, nExemplos;
        int k = foldedExamples.size()+1;
        double calculatedError = 0;
        double[] errors = new double[k];
        boolean classificaResult = false;

        Node root;

        ArrayList<Registro> trainExamples = new ArrayList<>();
        ID3 id3;



        for (int i = 0; i < k; i++) {
            numNodes = 0;
            numArestas = 0;
            countErros = 0;
            trainExamples.clear();
            for (int j = 0; j < foldedExamples.size(); j++) {
                trainExamples.addAll(foldedExamples.get(j));
            }
            id3 = new ID3();
            root = id3.generateTree(trainExamples, atributos);
            for (int j = 0; j < testRegs.size(); j++) {
                classificaResult = classifica(root, testRegs.get(j));
                if (!classificaResult) countErros++;
            }
            nExemplos = testRegs.size();
            errors[i] = (countErros / nExemplos);

            if(i != foldedExamples.size()) {
                ArrayList<Registro> newTestExamples = new ArrayList<>(foldedExamples.get(i));
                ArrayList<Registro> newSubTrainExamples = new ArrayList<>(testRegs);

                foldedExamples.put(i, newSubTrainExamples);
                testRegs.clear();
                testRegs = newTestExamples;
            }
            calculatedError += errors[i];
            System.out.println("Arvore "+i+" acabou com: " + numNodes+ " nos.");
            System.out.println("Arvore "+i+" acabou com: " + numArestas+ " arestas.");
            System.out.println("Erro da arvore: "+errors[i]);
        }

        calculatedError = calculatedError / k;
        System.out.println("Erro calculado total: "+calculatedError);
        return calculatedError;
    }

		/**
		 * FunÃ§Ã£o main, ela que irÃ¡ carregar os dados e iniciar o processamento da Ã¡rvore.
		 *
		 * @param args
		 */
	public static void main(String[] args) {
    	//Caminho para a pasta onde serÃ¡ lido o arquivo com a base de dados
		if (args.length < 2) {
			System.out.println(" Informe caminho dos arquivos de entrada e saída! ");
			return;
		}
		//Carrega os atributos e registros da base de dados
		IOManager.readDataset(args[0], atributos, registros);

		//Instancia a raiz da arvore:
		Node root;

		//Inicia o processamento da Ã¡rvore
        kFold(10, true);
        double erro = crossValidation();
        double n = registros.size();

        double se =  Math.sqrt((erro * (1-erro)) / n);
        double minErr = erro - (CONFIDENCE_INTERVAL_CONSTANT * se);
        double maxErr = erro + (CONFIDENCE_INTERVAL_CONSTANT * se);

        System.out.println("O erro verdadeiro do classificador esta entre "+minErr+" e "+maxErr);

		ID3 id3 = new ID3();
		//root = id3.generateTree(registros, atributos);

		//Imprime a arvore resultante no arquivo Result.txt
//		PrintWriter writer = null;


        double accur = 0.0;
        // Cria classe para execucao da poda
        Apoda P = new Apoda();
        root = P.criaArvore(id3);
        accur = P.testArvore(root);

        System.out.println("Acuracia da arvore sem a poda: "+accur);

        P.podaArvore(root, accur);
        accur = P.testArvore(root);

        System.out.println("Acuracia da arvore apos a poda: "+accur);

		IOManager.writeArvore(args[1], root);

		//Fecha o arquivo
//		writer.close();
	}
}
