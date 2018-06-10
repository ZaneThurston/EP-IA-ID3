import java.io.*;
import java.util.ArrayList;

public class IOManager {

	private static String SEPARADOR = "; ";

	public void setSeparador(String newSep){ this.SEPARADOR = newSep; }

	// todo: WIP CONSTRUCAO DA SAIDA
	public static void writeArvore(Node raiz, PrintWriter writer, int nivelArvore) {
		String linha;

		if (raiz.getAtributoTeste().getNome().isEmpty()) {
			//linha = raiz.getAtributoTeste().getValor();


		}

	}


	public static void readDataset(String path, ListAtributos atributos, ArrayList<Registro> registros) {
		String linha = null;
		try {
			BufferedReader rd = new BufferedReader(new FileReader(path));

			linha = rd.readLine();
			atributos.setAtributos(linha.split(SEPARADOR));
			while((linha = rd.readLine()) != null) {

			}

		} catch (IOException e1) {
			System.out.println("Erro ao ler arquivo de entrada");
		}
	}

}
