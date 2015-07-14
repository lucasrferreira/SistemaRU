package entidades.value_objects;

import java.io.Serializable;

public class RefeicaoVO implements Serializable {
	private TurnoVO turno;
	private String descricao;
	private String op_veg;
	private int idRefeicao;
	
	public RefeicaoVO() { }

	public RefeicaoVO(TurnoVO turno, String descricao, String op_veg) {
		this.op_veg = op_veg;
		this.descricao = descricao;
		this.turno = turno;
	}
	
	public String getOpVeg() {
		return op_veg;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public TurnoVO getTurno() {
		return turno;
	}
	
	public void setOpVeg(String op_veg)
	{
		this.op_veg = op_veg;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setTurno(TurnoVO turno)
	{
		this.turno = turno;
	}

	public int getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(int idRefeicao) {
		this.idRefeicao = idRefeicao;
	}


	
}
