package entidades.testesUnitarios;

import static org.junit.Assert.fail;

import org.junit.Test;

import entidades.value_objects.CPF;

public class CPFTest
{

	@Test(expected = Exception.class)
	public void testCPF() throws Exception
	{
		CPF cpf = new CPF(458774);
		
	}


}
