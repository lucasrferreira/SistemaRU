package entidades.value_objects;


public class ConsumidorVO {
	private String nome;
	private int matricula;
	private int anoIngresso;
	private Sexo sexo;
	private Titulo titulo;
	private CPF cpf;
	
	
	public ConsumidorVO(){
		
	}
	
	
	public ConsumidorVO(String nome, int matricula, int anoIngresso) {
		this.nome = nome;
		this.matricula = matricula;
		this.anoIngresso = anoIngresso;
	}

	public ConsumidorVO(String nome, int matricula, int anoIngresso, Sexo sexo, Titulo titulo, CPF cpf) {
		this(nome,matricula,anoIngresso);
		this.sexo = sexo;
		this.titulo = titulo;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public int getAnoIngresso() {
		return anoIngresso;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public CPF getCpf() {
		return cpf;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setMatricula(int matricula){
		this.matricula = matricula;
	}
	public void setAnoIngresso(int anoIngresso){
		this.anoIngresso = anoIngresso;
	}
	
	public void setSexo(Sexo sexo){
		this.sexo = sexo;
	}
	public void setTitulo(Titulo titulo){
		this.titulo = titulo;
	}
	public void setCpf(CPF cpf){
		this.cpf = cpf;
	}
}
