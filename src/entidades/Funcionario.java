package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.FuncionarioService;
import entidades.value_objects.CPF;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.FuncionarioVO;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Funcionario {

	public static Collection<FuncionarioVO> _listarFuncionariosDisponiveis() throws Exception
	{
		Collection<FuncionarioVO> colFunc = new ArrayList<FuncionarioVO>();
		ResultSet rs = null;		
		try {
			 
			FuncionarioService.initConnection();
			rs = FuncionarioService.listar();
			while(rs.next()){
				FuncionarioVO func = new FuncionarioVO();
				
				func.setCpf(CPF.fromString(rs.getString("cpf")));
				func.setDepartamento(new DepartamentoVO());
				func.getDepartamento().setSigla(rs.getString("departamento"));
				
				func.setNome(rs.getString("consumidor.nome"));
				func.setMatricula(rs.getInt("consumidor.matricula"));
				func.setAnoIngresso(rs.getInt("consumidor.ano"));

				if(rs.getString("consumidor.sexo").equals(Sexo.FEMININO.getSexo()))
					func.setSexo(Sexo.FEMININO);
				if(rs.getString("consumidor.sexo").equals(Sexo.MASCULINO.getSexo()))
					func.setSexo(Sexo.MASCULINO);
				
				
				if(rs.getString("consumidor.titulo").equals(Titulo.MESTRADO.getTitulo()))
					func.setTitulo(Titulo.MESTRADO);
				if(rs.getString("consumidor.titulo").equals(Titulo.DOUTORADO.getTitulo()))
					func.setTitulo(Titulo.DOUTORADO);
				if(rs.getString("consumidor.titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
					func.setTitulo(Titulo.ESPECIALIZACAO);
			
				
				colFunc.add(func);
			}

			FuncionarioService.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao buscar os dados do banco");
			e.printStackTrace();
		}
		return 	colFunc ;
	}
	
	public static void _adicionarFuncionario( FuncionarioVO funcionario) throws ClassNotFoundException, SQLException 
	{
		FuncionarioService.initConnection();
		FuncionarioService.insert(funcionario);
		FuncionarioService.closeConnection();
	}

	public static FuncionarioVO _buscarFuncionario(FuncionarioVO funcionario) throws SQLException, ClassNotFoundException 
	{
		FuncionarioService.initConnection();
		ResultSet rs = FuncionarioService.busca(funcionario);
		
		if(rs.next())
		{
			funcionario.setNome(rs.getString("nome"));
			funcionario.setDepartamento(new DepartamentoVO());
			funcionario.getDepartamento().setSigla(rs.getString("departamento"));
			
		}
		
		FuncionarioService.closeConnection();
		
		return funcionario;
	}

	public static void _atualizarFuncionario(FuncionarioVO funcionario) throws SQLException, ClassNotFoundException 
	{
		FuncionarioService.initConnection();
		FuncionarioService.alterar(funcionario);
		FuncionarioService.closeConnection();
	}
}
