package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Aluno extends Consumidor{
	
	private Curso curso;


	public void _adicionarAluno() throws ClassNotFoundException, SQLException 
	{
		super._adicionarConsumidor();
		Conexao.initConnection();
		
		String prepare = "Insert into aluno ( cpf, curso) value (?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, cpf.toString());
		pstmt.setString(2, curso.getSigla());
		
		pstmt.execute();
		Conexao.closeConnection();
	}


	public void _atualizarAluno() throws SQLException, ClassNotFoundException 
	{
		super._atualizarConsumidor();
		Conexao.initConnection();

		String prepare = "Update aluno set curso = ? where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, curso.getSigla());
		
		pstmt.setString(2, cpf.toString());
		pstmt.execute();
		
		Conexao.closeConnection();
	}
	
	public static Aluno load(ResultSet rs) throws Exception
	{
		Aluno aluno = (Aluno) Consumidor.load(rs);
		
		aluno.setCurso(CursoFinder._buscarCurso(rs.getString("aluno.curso")));
		
		return aluno;
	}

	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}

}
