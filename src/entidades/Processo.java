package entidades;

public class Processo {
	private int id;
	private String estado;
	private String nome;
	private Advogado advogado;

	public Processo(String estado, String nome, Advogado advogado) {
		this.estado = estado;
		this.nome = nome;
		this.advogado = advogado;
	}

	public Processo(int id, String estado, String nome, Advogado advogado) {
		this.id = id;
		this.estado = estado;
		this.nome = nome;
		this.advogado = advogado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado_processo(String estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}
	
	
}
