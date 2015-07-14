package entidades.value_objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public abstract class ConsumidorVO {
	private String nome;
	private int matricula;
	private int anoIngresso;
	private Sexo sexo;
	private Titulo titulo;
	private CPF cpf;
	
	public enum Sexo{
		MASCULINO,FEMININO;
	}	
	public enum Titulo{
		ESPECIALIZACAO, MESTRADO, DOUTORADO;
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
	
	
}
