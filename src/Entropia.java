import java.util.ArrayList;
import java.util.List;

public class Entropia {

	/**
	 * Metodo que dado um conjunto de registros e os atributos a serem calculados, calcula a entropia
	 * do conjunto
	 *
	 * @param data   - registros do qual sera calculado a entropia
	 * @param learningSet - atributos
	 * @return
	 */
	public static double calcula(ArrayList<Registro> data, ListDisAtributos learningSet) {
		double entropy = 0;

		if(data.size() == 0) {
			return 0;
		}

		//Obtem a posicao em que se encontra a classe no conjunto de atributos
		int positionClass = learningSet.getQtdeAtributos() - 1;
		//Obtem a posicao em que se encontra a classe nos registros
		int positionClassRecord = data.get(0).getAtributos().size() - 1;

		//Itera pelas classes existentes
		for(int i = 0; i < learningSet.getInfoAtributo(positionClass).getListAttributes().getQuantity(); i++) {
			int count = 0;
			for(int j = 0; j < data.size(); j++) {
				Registro record = data.get(j);

				if(record.getAtributos().get(positionClassRecord).getValor() == i) {
					count++;
				}
			}

			//Calcula a entropia
			double probability = count / (double)data.size();
			if(count > 0) {
				entropy += -probability * (Math.log(probability) / Math.log(2));
			}
		}

		return entropy;
	}
}


