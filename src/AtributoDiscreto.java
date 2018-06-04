import java.util.HashMap;
import java.util.Map;

public class AtributoDiscreto {
	private Map<String, Integer> valores;

	private AtributoDiscreto() { valores = new HashMap<String, Integer>(); }

	public void setValor(String chave) {
		if (!valores.containsKey(chave)) {
			valores.put(chave, valores.size());
		}
	}

	public int getValor(String chave) {	return valores.get(chave); }
}
