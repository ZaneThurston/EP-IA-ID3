import java.io.*;
import java.util.ArrayList;

// Classe que implementa um manipulador de entrada e saida
// assume-se que o conjunto de dados esteja discretizado
// e que a primeira linha do arquivo contenha os nomes dos atributos
// o separador dos valores pode ser alterado via setSeparador

public class IOManager {

	private static String SEPARADOR = ";";

	public static void setSeparador(String newSep){ SEPARADOR = newSep; }

	// todo: WIP CONSTRUCAO DA SAIDA
//	public static void writeArvore(String path, Node raiz) {
//		try {
//			PrintWriter wr = new PrintWriter(path, "UTF-8");
//			String linha;
//
////			if (raiz.getAtributoTeste().getNome().isEmpty()) {
////				//linha = raiz.getAtributoTeste().getValor();
////
////
////			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e3) {
//			e3.printStackTrace();
//		}
//	}

	// leitura do arquivo de entrada
	public static void readDataset(String path, ArrayList<Atributo> atributos, ArrayList<Registro> registros) {
		String linha;
		String[] atribs;
		try {
			BufferedReader rd = new BufferedReader(new FileReader(path));

			linha = rd.readLine();
			atribs = linha.split(SEPARADOR);

			for (int i = 0; i < atribs.length - 1; i++) atributos.add(new Atributo(atribs[i]));

			while((linha = rd.readLine()) != null) {
				registros.add(new Registro(linha.split(SEPARADOR)));
			}

		} catch (IOException e1) {
			System.out.println("Erro ao ler arquivo de entrada");
		}
	}

}
