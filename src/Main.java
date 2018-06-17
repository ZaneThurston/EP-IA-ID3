import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {

	static ArrayList<Atributo> atributos = new ArrayList<>();
    static ArrayList<Registro> registros;

    // para o k-fold cross validation:
    static ArrayList<Registro> testRegs;
    static HashMap<Integer, ArrayList<Registro>> foldedExamples;

	static int numNodes = 0;
	static int numArestas = 0;

    // Dado um int K, separa o conjunto de exemplos em K subconjuntos, sendo 1 de teste e k-1 de treino
    public void kFold(int k, boolean permuta) {
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

        sizeFregs = k/n;

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

    public double crossValidation() {
        double calculatedError = 0.0;
        double[] errors;
        Node root;

        ArrayList<Registro> trainExamples = new ArrayList<>();
        ID3 id3;


        for (int i = 0; i < foldedExamples.size() + 1; i++) {
            for (int j = 0; j < foldedExamples.size(); j++) {
                trainExamples.addAll(foldedExamples.get(j));
            }
            id3 = new ID3();
            root = id3.generateTree(trainExamples, atributos);




        }





        return calculatedError;
    }

		/**
		 * Função main, ela que irá carregar os dados e iniciar o processamento da árvore.
		 *
		 * @param args
		 */
	public static void main(String[] args) {
    	//Caminho para a pasta onde será lido o arquivo com a base de dados
		if (args.length < 1) {
			System.out.println(" Informe caminho do arquivo de dados! ");
			return;
		}
		//Carrega os atributos e registros da base de dados
		registros = new ArrayList<>();
		IOManager.readDataset(args[0], atributos, registros);

		//Instancia a raiz da arvore:
		Node root;

		//Inicia o processamento da árvore
		ID3 id3 = new ID3();
		root = id3.generateTree(registros, atributos);

		//Imprime a arvore resultante no arquivo Result.txt
//		PrintWriter writer = null;
		System.out.println("acabou com: " + numNodes);
		System.out.println("acabou com: " + numArestas);

//		IOManager.writeArvore(args[0], root);

		//Fecha o arquivo
//		writer.close();
	}







}
