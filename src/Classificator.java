import java.util.ArrayList;

public class Classificator {


    public boolean Classifica(Node root, Registro exemplo, ArrayList<Atributo> atributos) {

        Node rootIt = root;

        while (rootIt != null) {

            exemplo.getDado(root.getAtributoTeste(), atributos);

        }


        return false;
    }
}
