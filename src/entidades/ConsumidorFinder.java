package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.Conexao;
import entidades.value_objects.CPF;

public class ConsumidorFinder
{
	
	public static Collection<Consumidor> _listarConsumidoresDisponiveis() throws Exception
	{
		Collection<Consumidor> colConsumidor = new ArrayList<Consumidor>();
		ResultSet rs = null;

		Conexao.initConnection();

		String prepare = "Select * from consumidor ;";
		
		PreparedStatement psmt =  Conexao.prepare(prepare);
		rs = psmt.executeQuery();
	
		
		while (rs.next())
		{
			colConsumidor.add(Consumidor.load(rs));
		}

		Conexao.closeConnection();
		return colConsumidor;

	}

	public static Consumidor _buscarConsumidor(CPF cpf) throws SQLException, Exception
	{

		Conexao.initConnection();
		
		String prepare = "Select * from consumidor where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);
		
		pstmt.setString(1, cpf.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
		{
			return Consumidor.load(rs);

		}

		Conexao.closeConnection();
		return null;
	}


}
