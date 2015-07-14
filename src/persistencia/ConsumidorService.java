package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.DepartamentoVO;

public class ConsumidorService extends Conexao {

	
	public static Boolean insert(ConsumidorVO consumidor) throws ClassNotFoundException, SQLException
	{
		String prepare = "Insert into consumidor (nome, matricula, ano, sexo, titulo, cpf) value (?, ?, ? , ? , ?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, consumidor.getNome());
		pstmt.setInt(2, consumidor.getMatricula());
		pstmt.setInt(3, consumidor.getAnoIngresso());
		pstmt.setString(4, consumidor.getSexo().getSexo());
		pstmt.setString(5, consumidor.getTitulo().getTitulo());
		pstmt.setString(6, consumidor.getCpf().toString());
		
		
		return execute();
		
	}
	public static ResultSet busca(ConsumidorVO consumidor) throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from consumidor where cpf = ?;";
		
		Conexao.prepare(prepare);
		
		psmt.setString(1, consumidor.getCpf().toString());
		
		return executeQuery();
		
	}
	
	public static Boolean alterar(ConsumidorVO consumidor) throws ClassNotFoundException, SQLException
	{
		String prepare = "Update consumidor set nome = ?, ano =  ?, matricula = ?, sexo = ?  where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, consumidor.getNome());
		pstmt.setInt(2, consumidor.getAnoIngresso());
		pstmt.setInt(3, consumidor.getMatricula());
		pstmt.setString(4, consumidor.getSexo().getSexo());
		
		
		pstmt.setString(5, consumidor.getCpf().toString());
		
		return execute();
		
	}
	
	public static ResultSet listar() throws ClassNotFoundException, SQLException
	{
		String prepare = "Select * from consumidor;";
		
		Conexao.prepare(prepare);
		return executeQuery();
		
	}
	
}

