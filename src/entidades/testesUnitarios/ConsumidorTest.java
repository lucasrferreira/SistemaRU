package entidades.testesUnitarios;

import org.junit.Test;

import controladores.ccu.exceptions.AnoIngressoEmpty;
import controladores.ccu.exceptions.CpfEmpty;
import controladores.ccu.exceptions.MatriculaEmpty;
import controladores.ccu.exceptions.SexoEmpty;
import entidades.Consumidor;
import entidades.value_objects.Sexo;

public class ConsumidorTest
{

	@Test(expected = CpfEmpty.class)
	public void testAtualizarCpfNulo() throws Exception
	{
		Consumidor consumidor = new Consumidor();		
		consumidor.AtualizarConsumidor("", "Joaquim", 201245, 2015, Sexo.MASCULINO.getSexo());		
		
	}

	@Test(expected = MatriculaEmpty.class)
	public void testAtualizarMatricula() throws Exception
	{
		Consumidor consumidor = new Consumidor();		
		consumidor.AtualizarConsumidor("12345678971", "Joaquim", 0, 2015, Sexo.MASCULINO.getSexo());		
		
	}

	@Test(expected = AnoIngressoEmpty.class)
	public void testAtualizarAnoIngresso() throws Exception
	{
		Consumidor consumidor = new Consumidor();		
		consumidor.AtualizarConsumidor("12345678971", "Joaquim", 201245, 0, Sexo.MASCULINO.getSexo());		
		
	}

	@Test(expected = SexoEmpty.class)
	public void testAtualizarSexo() throws Exception
	{
		Consumidor consumidor = new Consumidor();		
		consumidor.AtualizarConsumidor("12345678971", "Joaquim", 201245, 2015, "");		
		
	}

}
