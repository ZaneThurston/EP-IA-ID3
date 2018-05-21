import java.io.*;
import java.util.List;

public class Main {


		/**
		 * Função main, ela que irá carregar os dados e iniciar o processamento da árvore.
		 *
		 * @param args
		 */
		public static void main(String[] args) {

			//Caminho para a pasta onde será lido o arquivo com a base de dados
			String path = "C:\\Users\\davidson.sestaro\\Dropbox\\IA\\";

			//Carrega os atributos da base de dados
			ListDiscreteAttributes attributes = FileReader.readAttributes(path + "PlayGolf.txt");

			//Carrega os registros da base de dados
			List<Registro> records = FileReader.readDataset(path + "PlayGolf.txt", attributes);

			//Instância o primeiro ramo da nossa árvore
			Node root = new Node();
			root.setDados(records);

			//Inicia o processamento da árvore
			ID3 id3 = new ID3();
			id3.generateTree(records, root, attributes);

			//Imprime a arvore resultante no arquivo Result.txt
			PrintWriter writer = null;

			try {
				writer = new PrintWriter(path + "Result.txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			FileWriter.writeTree(root, writer, 0);

			//Fecha o arquivo
			writer.close();
		}
}
