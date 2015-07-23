package entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.Conexao;

public class Funcionario extends Consumidor {
	
	private Departamento departamento;
	

	
	public void _adicionarFuncionario( ) throws ClassNotFoundException, SQLException 
	{

		super._adicionarConsumidor();
		Conexao.initConnection();
		
		String prepare = "Insert into funcionario ( cpf, departamento) values (?, ?);";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, cpf.toString());
		pstmt.setString(2, departamento.getSigla());

		pstmt.execute();
		
		Conexao.closeConnection();
	}


	public void _atualizarFuncionario() throws SQLException, ClassNotFoundException 
	{
		super._atualizarConsumidor();
		
		Conexao.initConnection();
		
		String prepare = "Update funcinario set departamento = ? where cpf = ?;";
		
		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, departamento.toString());
		pstmt.setString(2, cpf.toString());
		
		pstmt.execute();
		Conexao.closeConnection();
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}
	
	public static Funcionario load(ResultSet rs) throws Exception
	{
		Funcionario func = (Funcionario) Consumidor.load(rs);
		
		func.setDepartamento(DepartamentoFinder.get(rs.getString("funcionario.departamento")));
		
		return func;
		
	}
}
