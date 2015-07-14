package entidades.value_objects;

import java.io.Serializable;

public class AlunoVO extends ConsumidorVO implements Serializable{

	private CursoVO curso;
	
	public AlunoVO(String nome, int matricula, int anoIngresso, Sexo sexo, Titulo titulo, CPF cpf) {
		super(nome, matricula, anoIngresso, sexo, titulo, cpf);
		
	}	
	
	public AlunoVO()
	{
		super();
	}

	public CursoVO getCurso(){
		return curso;
	}
	public void setCurso(CursoVO curso){
		this.curso = curso;
	}





}
