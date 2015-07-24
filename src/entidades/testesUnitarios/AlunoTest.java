package entidades.testesUnitarios;

import org.junit.Test;

import controladores.ccu.exceptions.AnoIngressoEmpty;
import controladores.ccu.exceptions.CpfEmpty;
import controladores.ccu.exceptions.CursoEmpty;
import controladores.ccu.exceptions.MatriculaEmpty;
import controladores.ccu.exceptions.NomeEmpty;
import controladores.ccu.exceptions.SexoEmpty;
import controladores.ccu.exceptions.TituloEmpty;
import entidades.Aluno;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class AlunoTest
{

	@Test(expected = CpfEmpty.class)
	public void testCPF() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("whatever", "", Sexo.FEMININO.getSexo(), 2015, Titulo.MESTRADO.getTitulo(), 2015, "ccomp");
		
	}
	
	@Test(expected = NomeEmpty.class)
	public void testNome() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("", "12345678911", Sexo.FEMININO.getSexo(), 2015, Titulo.MESTRADO.getTitulo(), 2015, "ccomp");
		
	}
	
	@Test(expected = SexoEmpty.class)
	public void testSexo() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("whatever", "12345678911", "", 2015, Titulo.MESTRADO.getTitulo(), 2015, "ccomp");
		
	}
	
	@Test(expected = MatriculaEmpty.class)
	public void testMatricula() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("whatever", "12345678911", Sexo.FEMININO.getSexo(), 0, Titulo.MESTRADO.getTitulo(), 2015, "ccomp");
		
	}
	
	@Test(expected = TituloEmpty.class)
	public void testTitulo() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("whatever", "12345678911", Sexo.FEMININO.getSexo(), 2015, "", 2015, "ccomp");
		
	}
	
	@Test(expected = AnoIngressoEmpty.class)
	public void testAnoIngresso() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("whatever", "12345678911", Sexo.FEMININO.getSexo(), 2015, Titulo.MESTRADO.getTitulo(), 0, "ccomp");
		
	}
	
	@Test(expected = CursoEmpty.class)
	public void testCurso() throws Exception
	{
		Aluno aluno = new Aluno();
		
		aluno.criarAluno("whatever", "12345678911", Sexo.FEMININO.getSexo(), 2015, Titulo.MESTRADO.getTitulo(), 2015, "");
		
	}
	

}
