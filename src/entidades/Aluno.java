package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.AlunoService;
import persistencia.DepartamentoService;
import entidades.value_objects.AlunoVO;
import entidades.value_objects.CPF;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Aluno {
	

	public static Collection<AlunoVO> _listarAlunosDisponiveis() throws Exception
	{
		Collection<AlunoVO> colAlu = new ArrayList<AlunoVO>();
		ResultSet rs = null;		
		try {
			 
			DepartamentoService.initConnection();
			rs = DepartamentoService.listar();
			while(rs.next()){
				AlunoVO aluno = new AlunoVO();
				
				aluno.setCpf(CPF.fromString(rs.getString("cpf")));
				aluno.setCurso(new CursoVO());
				aluno.getCurso().setSigla(rs.getString("curso"));
				
				aluno.setNome(rs.getString("consumidor.nome"));
				aluno.setMatricula(rs.getInt("consumidor.matricula"));
				aluno.setAnoIngresso(rs.getInt("consumidor.ano"));

				if(rs.getString("consumidor.sexo").equals(Sexo.FEMININO.getSexo()))
					aluno.setSexo(Sexo.FEMININO);
				if(rs.getString("consumidor.sexo").equals(Sexo.MASCULINO.getSexo()))
					aluno.setSexo(Sexo.MASCULINO);
				
				if(rs.getString("consumidor.titulo").equals(Titulo.MESTRADO.getTitulo()))
					aluno.setTitulo(Titulo.MESTRADO);
				if(rs.getString("consumidor.titulo").equals(Titulo.DOUTORADO.getTitulo()))
					aluno.setTitulo(Titulo.DOUTORADO);
				if(rs.getString("consumidor.titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
					aluno.setTitulo(Titulo.ESPECIALIZACAO);
			
				
				colAlu.add(aluno);
			}

			DepartamentoService.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao buscar os dados do banco");
			e.printStackTrace();
		}
		return 	colAlu ;
	}
	
	public static void _adicionarAluno( AlunoVO aluno) throws ClassNotFoundException, SQLException 
	{
		AlunoService.initConnection();
		AlunoService.insert(aluno);
		AlunoService.closeConnection();
	}

	public static AlunoVO _buscarAluno(AlunoVO aluno) throws SQLException, ClassNotFoundException 
	{
		AlunoService.initConnection();
		ResultSet rs = AlunoService.busca(aluno);
		
		if(rs.next())
		{
			aluno.setNome(rs.getString("nome"));
			aluno.setCurso(new CursoVO());
			aluno.getCurso().setSigla(rs.getString("curso"));
			
		}
		
		AlunoService.closeConnection();
		
		return aluno;
	}

	public static void _atualizarAluno(AlunoVO aluno) throws SQLException, ClassNotFoundException 
	{
		AlunoService.initConnection();
		AlunoService.alterar(aluno);
		AlunoService.closeConnection();
	}

}
