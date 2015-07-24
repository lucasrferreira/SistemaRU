package entidades.testesUnitarios;

import org.junit.Test;

import controladores.ccu.exceptions.AnoIngressoEmpty;
import controladores.ccu.exceptions.CpfEmpty;
import controladores.ccu.exceptions.DepartamentoEmpty;
import controladores.ccu.exceptions.MatriculaEmpty;
import controladores.ccu.exceptions.NomeEmpty;
import controladores.ccu.exceptions.SexoEmpty;
import controladores.ccu.exceptions.TituloEmpty;
import entidades.Funcionario;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class FuncionarioTest
{


	@Test(expected = NomeEmpty.class)
	public void testNome() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("", "12345678911", Sexo.FEMININO.getSexo(), 2015, Titulo.DOUTORADO.getTitulo(), 2015, "DCC");
		
	}
	
	@Test(expected = CpfEmpty.class)
	public void testCpf() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("Letícia", "", Sexo.FEMININO.getSexo(), 2015, Titulo.DOUTORADO.getTitulo(), 2015, "DCC");
		
	}
	
	@Test(expected = SexoEmpty.class)
	public void testSexo() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("Leticia", "12345678911", "", 2015, Titulo.DOUTORADO.getTitulo(), 2015, "DCC");
		
	}
	
	@Test(expected = MatriculaEmpty.class)
	public void testMatricula() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("Leticia", "12345678911", Sexo.FEMININO.getSexo(), 0,Titulo.DOUTORADO.getTitulo(), 2015, "DCC");
		
	}
	
	@Test(expected = TituloEmpty.class)
	public void testTitulo() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("Leticia", "12345678911", Sexo.FEMININO.getSexo(), 2015, "", 2015, "DCC");
		
	}
	
	@Test(expected = AnoIngressoEmpty.class)
	public void testAno() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("Leticia", "12345678911", Sexo.FEMININO.getSexo(), 2015, Titulo.DOUTORADO.getTitulo(), 0, "DCC");
		
	}
	
	@Test(expected = DepartamentoEmpty.class)
	public void testCurso() throws Exception
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.criarFuncionario("Leticia", "12345678911", Sexo.FEMININO.getSexo(), 2015, Titulo.DOUTORADO.getTitulo(), 2015, "");
		
	}
	

}
