import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Ganho {

	/**
	 * Calcula o ganho de informacao de determinado atributo
	 *
	 * @param dados  - Conjunto de dados
	 * @param subEntropies - Entropia dos possiveis subgrupos do atributo
	 * @param setSizes     - Tamanho dos possiveis subgrupos do atributo
	 * @param data         - Quantidade de registros total
	 * @return
	 */
	public static double calcula(ArrayList<Registro> dados, ArrayList<Atributo> atributos, String atributoChave) {
		double gain = Entropia.calcula(dados);
		HashMap<String, ArrayList<Registro>> subconj = montaSubconj(dados, atributos, atributoChave);

		// variaveis para o calculo das subentropías
		String atribValAtu;
		ArrayList<Registro> subconjAtu;


		Iterator<String> subconjIterator = subconj.keySet().iterator();
		while (subconjIterator.hasNext()) {
			atribValAtu = subconjIterator.next();
			subconjAtu = subconj.get(atribValAtu);

			double gainSubAtu = ((new Integer(subconjAtu.size())).doubleValue() / (new Integer(dados.size())).doubleValue()) * Entropia.calcula(subconjAtu);
			gain = gain - gainSubAtu;
		}
		return gain;
	}

	public static HashMap<String, ArrayList<Registro>> montaSubconj(ArrayList<Registro> dados, ArrayList<Atributo> atributos, String atributoChave) {

		HashMap<String, ArrayList<Registro>> subconj = new HashMap<>();

		for (int i = 0; i < dados.size(); i++) {
			Registro regAtu = dados.get(i);
			String valorAtribAtu = regAtu.getDado(atributoChave, atributos);
			if (!subconj.containsKey(valorAtribAtu)) {
				subconj.put(valorAtribAtu, new ArrayList<Registro>());

			}
			subconj.get(valorAtribAtu).add(regAtu);

		}
		return subconj;
	}

}
