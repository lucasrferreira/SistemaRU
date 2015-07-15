package controladores.ccu;

import java.sql.SQLException;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Aluno;
import entidades.AlunoFinder;
import entidades.Consumidor;
import entidades.Curso;
import entidades.CursoFinder;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class GerirAluno {
	
	
	public static void criarAluno(String nome, String cpf, String sexo, int matricula, String titulo, int ano, String curso) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException {
		
		Aluno aluno = (Aluno) new Consumidor(nome, matricula, ano);
		
		aluno.setCurso(CursoFinder._buscarCurso(curso));
		try
		{
			aluno.setCpf(CPF.fromString(cpf));
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		if (sexo.equals(Sexo.FEMININO.getSexo()))
			aluno.setSexo(Sexo.FEMININO);
		if (sexo.equals(Sexo.MASCULINO.getSexo()))
			aluno.setSexo(Sexo.MASCULINO);

		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			aluno.setTitulo(Titulo.MESTRADO);
		if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			aluno.setTitulo(Titulo.DOUTORADO);
		if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			aluno.setTitulo(Titulo.ESPECIALIZACAO);
	
		
		try
		{
			if (AlunoFinder._buscarAluno(CPF.fromString(cpf))!= null){
				aluno._adicionarAluno();
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
