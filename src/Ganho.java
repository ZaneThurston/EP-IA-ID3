import java.util.ArrayList;
import java.util.LinkedList;

public class Ganho {

	/**
	 * Calcula o ganho de informacao de determinado atributo
	 *
	 * @param rootEntropy  - Entropia do conjunto como um todo
	 * @param subEntropies - Entropia dos possiveis subgrupos do atributo
	 * @param setSizes     - Tamanho dos possiveis subgrupos do atributo
	 * @param data         - Quantidade de registros total
	 * @return
	 */
	public static double calcula(ArrayList<Registro> dados, String atributoAtual) {
		double gain = Entropia.calcula(dados);

		for (int i = 0; i < subEntropies.size(); i++) {
			gain += -((setSizes.get(i) / (double) data) * subEntropies.get(i));
		}

		return gain;
	}
}
