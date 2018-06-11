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
			if (args.length < 1) {
				System.out.println(" Informe caminho do arquivo de dados! ");
				return;
			}

			//Carrega os atributos e registros da base de dados
			ArrayList<Atributo> atributos = new ArrayList<>();
			ArrayList<Registro> registros = new ArrayList<>();
			IOManager.readDataset(args[0], atributos, registros);


			//Instancia a raiz da arvore:
			Node root = null;

			//Inicia o processamento da árvore
			ID3 id3 = new ID3();
			id3.generateTree(root, registros, atributos);

			//Imprime a arvore resultante no arquivo Result.txt
			PrintWriter writer = null;

			IOManager.writeArvore(args[0], root);

			//Fecha o arquivo
			writer.close();
		}
}
