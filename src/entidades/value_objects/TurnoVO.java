package entidades.value_objects;

public enum TurnoVO {
	MANHA("manha",0.5,3), TARDE("tarde",6,1), NOITE("noite",6,1);
	
	private double valorAluno, valorFuncionario;
	private String turno;
	
	private TurnoVO(String turno, double valorAluno, double valorFuncionario) {
		this.turno = turno;
		this.valorAluno = valorAluno;
		this.valorFuncionario = valorFuncionario;
	}
	
	public double getValorAluno() {
		return valorAluno;
	}
	
	public double getValorFuncionario() {
		return valorFuncionario;
	}

	public String getTurno() {
		return turno;
	}
	

}
