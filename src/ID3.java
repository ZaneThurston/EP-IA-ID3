
import java.util.LinkedList;
import java.util.List;

public class ID3 {

	/**
	 * Gera a arvore de decisao de forma recursiva.
	 *
	 * @param records   - Dados a serem classificados pela arvore
	 * @param root    - No da arvore do topo da arvore para essa iteracao
	 * @param learningSet  - Atributos a serem utilizados pelo classificador
	 * @return     - Arvore de decisao
	 */
	public Node generateTree(List<Registro> records, Node root, ListDiscreteAttributes learningSet) {

		//Inicializa as variaveis para selecionar o melhor atributp
		int bestAttribute = -1;
		double bestGain = 0.0;

		//Calcula a entropia para os registros a serem considerados
		root.setEntropia(Entropia.calcula(root.getDados(), learningSet));

		//Condicao de para da arvore
		if(root.getEntropia() == 0) {
			return populateResult(root.getDados(), root, learningSet);
		}

		//Avalia cada atributo ainda nao utilizado nesse galho da arvore
		for(int i = 0; i < learningSet.getAttributeQuantity() - 1; i++) {
			double entropy = 0;

			LinkedList<Double> entropies = new LinkedList<Double>();
			LinkedList<Integer> setSizes = new LinkedList<Integer>();

			//Faz um de para com a posicao do atributo no vetor de atributos com a posicao real dele na base de dados
			int attributePositionRecord = Utils.getAttributePositionOnRecords(learningSet.getAttributeInfo(i), root.getData().get(0));

			//Itera por cada possivel valor do atributo selecionado
			for(int j = 0; j < learningSet.getAttributeInfo(i).getListAttributes().getQuantity(); j++) {
				//Pega os registros com o valor a ser considerado
				ArrayList<Record> subset = Utils.subset(root, attributePositionRecord, j);
				//Pega o tamanho desse subset
				setSizes.add(subset.size());

				//Calcula a entropia para o subset
				if(subset.size() != 0) {
					entropy = Entropy.calculateEntropy(subset, learningSet);
					entropies.add(entropy);
				} else {
					entropies.add(0.0);
				}
			}

			//Calcula o ganho de informacao
			double gain = InformationGain.calculateGain(root.getEntropy(), entropies, setSizes, root.getData().size());

			//Se for melhor do que o melhor atributo atualiza os valores
			if(gain > bestGain) {
				bestAttribute = i;
				bestGain = gain;
			}
		}

		//Caso exista um atributo a ser considerado
		if(bestAttribute != -1) {
			//Preenche o no da arvore com os valores desse atributo
			AttributeInfo chosen = learningSet.getAttributeInfo(bestAttribute);
			String testedAttributeName = root.getTestAttribute().getValue();

			root.setTestAttribute(chosen);
			root.setValue(testedAttributeName);
			root.children = new Node[chosen.getListAttributes().getQuantity()];
			root.setUsed(true);

			learningSet.removeAttribute(bestAttribute);

			int bestAttributePositionRecord = Utils.getAttributePositionOnRecords(chosen, records.get(0));

			//Preenche as folhas geradas a partir desse atributo
			for (int j = 0; j < chosen.getListAttributes().getQuantity(); j++) {
				root.childs[j] = new Node();
				root.childs[j].setPai(root);
				root.childs[j].setData(Utils.subset(root, bestAttributePositionRecord, j));
				root.childs[j].getTestAttribute().setValue(chosen.getListAttributes().getValue(j));
			}

			//Itera recursivamente pelos filhos
			for (int j = 0; j < chosen.getListAttributes().getQuantity(); j++) {
				generateTree(records, root.childs[j], learningSet.clone());
			}
		}
		//Metodo de para do algoritmo
		else {
			return populateResult(root.getData(), root, learningSet);
		}

		return root;
	}

	/**
	 * Popula as folhas durante as condicoes de para do algoritmo
	 *
	 * @param records   - Registros filhos dessa folha
	 * @param root    - No com o atributo que gerou a folha
	 * @param learningSet  - Atributos ainda nao utilizados no ramo
	 * @return
	 */
	private Node populateResult(List<Registro> records, Node root, ListDiscreteAttributes learningSet) {
		AttributeInfo chosen = learningSet.getAttributeInfo(learningSet.getAttributeQuantity() - 1);

		root.children = new Node[1];

		root.children[0] = new Node();
		root.children[0].setParent(root);

		int classAttributePositionRecord = Utils.getAttributePositionOnRecords(chosen, records.get(0));
		int resultPosition = Utils.getMajority(root.getData(), learningSet.getAttributeInfo(learningSet.getAttributeQuantity() - 1).getListAttributes(), classAttributePositionRecord);

		root.children[0].getTestAttribute().setValue(chosen.getListAttributes().getValue(resultPosition));

		return root;
	}
}