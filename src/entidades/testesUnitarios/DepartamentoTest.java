package entidades.testesUnitarios;

import java.sql.SQLException;

import org.junit.Test;

import controladores.ccu.exceptions.nome.NomeEmptyException;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.sigla.SiglaEmptyException;
import entidades.Departamento;


public class DepartamentoTest
{


	@Test(expected = SiglaEmptyException.class)
	public void testEmptySigla() throws ClassNotFoundException, SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, SQLException
	{
		Departamento departamento = new Departamento();
		
		departamento.criarDepartamento("", "nome");
		
		
		
	}
	
	@Test(expected = NomeEmptyException.class)
	public void testEmptyName() throws ClassNotFoundException, SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, SQLException
	{
		Departamento departamento = new Departamento();
		
		departamento.criarDepartamento("DCC", "");
		
		
	}
	
	@Test(expected = SiglaAlreadyExistsException.class)
	public void testSameSigla() throws ClassNotFoundException, SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, SQLException
	{
		Departamento departamento = new Departamento();
		
		departamento.criarDepartamento("DCC", "Departamento de Ciência da Computação");
		
		
	}


}
