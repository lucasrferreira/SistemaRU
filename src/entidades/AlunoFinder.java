package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;
import entidades.value_objects.CPF;

public class AlunoFinder {
	

	public static Collection<Aluno> getAll() throws Exception
	{
		Collection<Aluno> colAlu = new ArrayList<Aluno>();
		ResultSet rs = null;		
		
			Conexao.initConnection();
			
			String prepare = "Select * from aluno , consumidor where aluno.cpf = consumidor.cpf ;";
			PreparedStatement psmt = Conexao.prepare(prepare);
			
			rs = psmt.executeQuery();
			
			
			while(rs.next()){
				Aluno aluno = new Aluno();
				colAlu.add(aluno.load(rs));
			}

			Conexao.closeConnection();
		
		return 	colAlu ;
	}
	

	public static Aluno get(CPF cpf) throws Exception 
	{
		Conexao.initConnection();
		ResultSet rs = null;
		Aluno aluno = null;
		
		String prepare = "Select * from aluno, consumidor where aluno.cpf = consumidor.cpf and aluno.cpf = ?;";
		PreparedStatement psmt = Conexao.prepare(prepare);
		
		psmt.setString(1, cpf.toString());
		
		rs = psmt.executeQuery();
		
		
		if(rs.next()){
			Aluno _aluno = new Aluno();
			aluno = _aluno.load(rs);
		}

		
		Conexao.closeConnection();
		
		return aluno;
	}

}
