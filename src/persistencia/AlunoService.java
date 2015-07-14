package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.AlunoVO;

public class AlunoService extends Conexao {

	
	public static Boolean insert(AlunoVO aluno) throws ClassNotFoundException, SQLException
	{
		ConsumidorService.insert(aluno);
		
		String prepare = "Insert into aluno ( cpf, curso) value (?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, aluno.getCpf().toString());
		pstmt.setString(2, aluno.getCurso().getSigla());
		
		
		return execute();
		
	}
	public static ResultSet busca(AlunoVO aluno) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from consumidor , aluno where aluno.cpf = consumidor.cpf and aluno.cpf = ? ;";
		
		Conexao.prepare(prepare);
		
		psmt.setString(1, aluno.getCpf().toString());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(AlunoVO aluno) throws ClassNotFoundException, SQLException
	{
		ConsumidorService.alterar(aluno);
		
		String prepare = "Update aluno set curso = ? where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, aluno.getCurso().getSigla());
		
		pstmt.setString(2, aluno.getCpf().toString());
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from aluno , consumidor where aluno.cpf = consumidor.cpf ;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

