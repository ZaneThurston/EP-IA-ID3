import java.io.PrintWriter;
import java.util.ArrayList;

public class IOManager {

	public static void writeArvore(Node raiz, PrintWriter writer, int nivelArvore) {
		String linha;

		if (raiz.getAtributoTeste().getNome().isEmpty()) {
			linha = raiz.getAtributoTeste().getValor();

		}

	}


	public static ListDisAtributos readAtributos(String path) {
		ListDisAtributos attribs = null;

		return attribs;
	}

	public static ArrayList<Registro> readDataset(String path, ListDisAtributos atributos) {
		ArrayList<Registro> dataRegs = null;


		return dataRegs;
	}

}
