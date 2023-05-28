package entidades;

public class Advogado{
	private int id;
	private String oab;
	private String nome;
	private String cpf;
	private String datan;
	private String endereco;

	
	public Advogado(String oab, String nome, String cpf, String datan, String endereco) {
		this.oab = oab;
		this.nome = nome;
		this.cpf = cpf;
		this.datan = datan;
		this.endereco = endereco;
	}

	public Advogado(int id, String oab, String nome, String cpf, String datan, String endereco) {
		this.id = id;
		this.oab = oab;
		this.nome = nome;
		this.cpf = cpf;
		this.datan = datan;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOab() {
		return oab;
	}

	public void setOab(String oab) {
		this.oab = oab;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDatan() {
		return datan;
	}

	public void setDatan(String datan) {
		this.datan = datan;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void add(Advogado advogado) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
