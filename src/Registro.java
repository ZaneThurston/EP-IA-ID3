
// Classe que define um registro qualquer
// contem sua lista de dados
public class Registro {

    private String[] dados;

    public Registro(String[] dados) {
        this.dados = new String[dados.length];
        this.dados = dados;
    }

	public String[] getDados() { return dados; }

	public void setValores(String[] dados) {
		this.dados = new String[dados.length];
		this.dados = dados;
	}
}
