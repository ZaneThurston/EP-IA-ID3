
// Classe que define um atributo
//
public class Atributo {
	private String nome;
	private	boolean desconhecido;

	public Atributo() { }

	public Atributo(String nome) {
		this.nome = nome;
		this.desconhecido = false;

	}


	public void setNome(String nome) { this.nome = nome; }
	public String getNome() { return nome; }

	public void setDesconhecido(boolean desconhecido) {	this.desconhecido = desconhecido; }
	public boolean isDesconhecido() { return desconhecido; }




	public void setValor(String chave) {
		if (!valores.containsKey(chave)) {
			valores.put(chave, valores.size());
		}
	}

	public int getValor(String chave) {	return valores.get(chave); }

}
