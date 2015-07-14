package entidades.value_objects;

import java.io.Serializable;

public class FuncionarioVO extends ConsumidorVO implements Serializable {
	private DepartamentoVO dpto;
	
	public FuncionarioVO(String nome, int matricula, int anoIngresso) {
		super(nome, matricula, anoIngresso);
		// TODO Auto-generated constructor stub
	}
	
	public DepartamentoVO getDepartamento(){
		return dpto;
	}
	public void setDepartamento(DepartamentoVO dpto){
		this.dpto = dpto;
	}
}
