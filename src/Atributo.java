public class Atributo {
	private String nome;
	private	double valor;
	private	boolean desconhecido;

	public Atributo(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
		desconhecido = false;
	}

	public Atributo(String nome, String valor) {
		this.nome = nome;
		try {
			this.valor = Double.valueOf(valor);
			this.desconhecido = false;
		} catch (NumberFormatException){
			this.valor = -1;
			this.desconhecido = true;
		}
	}


	public void setNome(String nome) { this.nome = nome; }
	public String getNome() { return nome; }

	public void setValor(double valor) { this.valor = valor; }
	public double getValor() { return valor; }

	public void setDesconhecido(boolean desconhecido) {	this.desconhecido = desconhecido; }
	public boolean isDesconhecido() { return desconhecido; }
}
