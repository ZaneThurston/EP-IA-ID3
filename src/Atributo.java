
// Classe que define um atributo
//
public class Atributo {
	private String nome;
	private	boolean desconhecido;
	private double entropia;
	private double ganho;

	public Atributo() { }

	public Atributo(String nome) {
		this.nome = nome;
		this.desconhecido = false;

	}


	public void setNome(String nome) { this.nome = nome; }
	public String getNome() { return nome; }

	public void setEntropia(double entropia) { this.entropia = entropia; }
	public double getEntropia() { return entropia; }

	public void setDesconhecido(boolean desconhecido) {	this.desconhecido = desconhecido; }
	public boolean isDesconhecido() { return desconhecido; }

	public double getGanho() { return ganho; }
	public void setGanho(double ganho) { this.ganho = ganho; }
}
