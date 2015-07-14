package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.FuncionarioVO;

public class FuncionarioService extends Conexao {

	
	public static Boolean insert(FuncionarioVO funcinario) throws ClassNotFoundException, SQLException
	{
		ConsumidorService.insert(funcinario);
		
		String prepare = "Insert into funcinario ( cpf, departamento) value (?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, funcinario.getCpf().toString());
		pstmt.setString(2, funcinario.getDepartamento().getSigla());
		
		
		return execute();
		
	}
	public static ResultSet busca(FuncionarioVO funcinario) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from consumidor, funcinario where funcinario.cpf = consumidor.cpf and funcinario.cpf = ? ;";
		
		Conexao.prepare(prepare);
		
		psmt.setString(1, funcinario.getCpf().toString());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(FuncionarioVO funcinario) throws ClassNotFoundException, SQLException
	{
		ConsumidorService.alterar(funcinario);
		
		String prepare = "Update funcinario set departamento = ? where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, funcinario.getDepartamento().getSigla());
		
		pstmt.setString(2, funcinario.getCpf().toString());
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from funcinario , consumidor  where funcinario.cpf = consumidor.cpf ;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

