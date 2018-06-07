import java.io.*;
import java.util.ArrayList;

public class Main {


		/**
		 * Função main, ela que irá carregar os dados e iniciar o processamento da árvore.
		 *
		 * @param args
		 */
		public static void main(String[] args) {

			//Caminho para a pasta onde será lido o arquivo com a base de dados
			String path = "C:\\Users\\Marcos\\Documents\\IA\\";

			//Carrega os atributos da base de dados
			ListDisAtributos atributos = IOManager.readAtributos(path + "PlayGolf.txt");

			//Carrega os registros da base de dados
			ArrayList<Registro> records = IOManager.readDataset(path + "PlayGolf.txt", atributos);

			//Instância o primeiro ramo da nossa árvore
			Node root = new Node();
			root.setDados(records);

			//Inicia o processamento da árvore
			ID3 id3 = new ID3();
			id3.generateTree(records, root, atributos);

			//Imprime a arvore resultante no arquivo Result.txt
			PrintWriter writer = null;

			try {
				writer = new PrintWriter(path + "Result.txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			IOManager.writeArvore(root, writer, 0);

			//Fecha o arquivo
			writer.close();
		}
}
