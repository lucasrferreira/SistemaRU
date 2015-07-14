package entidades.value_objects;

public enum TurnoVO {
	MANHA(0.5,3), TARDE(6,1), NOITE(6,1);
	
	private double valorAluno, valorFuncionario;

	private TurnoVO(double valorAluno, double valorFuncionario) {
		this.valorAluno = valorAluno;
		this.valorFuncionario = valorFuncionario;
	}
	
	public double getValorAluno() {
		return valorAluno;
	}
	
	public double getValorFuncionario() {
		return valorFuncionario;
	}
	
	public void setValorAluno(double valorAluno) {
		this.valorAluno = valorAluno;
	}
	public void setValorFuncionario(double valorFuncionario) {
		this.valorFuncionario = valorFuncionario;
	}
}
