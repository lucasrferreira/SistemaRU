package entidades.testesUnitarios;

import java.sql.SQLException;

import org.junit.Test;

import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.nome.NomeEmptyException;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.sigla.SiglaEmptyException;
import entidades.Curso;


public class CursoTest
{

	@Test(expected = SiglaEmptyException.class)
	public void testEmptySigla() throws ClassNotFoundException, SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, SQLException, SiglaNotFoundException, NomeNotFoundException, DepartamentoNotFound
	{
		Curso curso = new Curso();		
		curso.criarCurso("", "", "DCC");			
		
	}
	
	@Test(expected = NomeEmptyException.class)
	public void testEmptyNome() throws ClassNotFoundException, SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, SQLException, SiglaNotFoundException, NomeNotFoundException, DepartamentoNotFound
	{
		Curso curso = new Curso();		
		curso.criarCurso("CC", "", "DCC");	
		
	}
	
	@Test(expected = SiglaEmptyException.class)
	public void testEmptySiglaDpto() throws ClassNotFoundException, SiglaAlreadyExistsException, SiglaEmptyException, NomeEmptyException, SQLException, SiglaNotFoundException, NomeNotFoundException, DepartamentoNotFound
	{
		Curso curso = new Curso();		
		curso.criarCurso("CC", "Ciência da Computação", "");		
		
	}
	

}
