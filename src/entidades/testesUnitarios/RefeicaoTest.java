package entidades.testesUnitarios;

import java.sql.SQLException;

import org.junit.Test;

import controladores.ccu.exceptions.DescricaoEmpty;
import controladores.ccu.exceptions.DescricaoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.OpVegEmpty;
import controladores.ccu.exceptions.OpVegNotFound;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.TurnoEmpty;
import controladores.ccu.exceptions.TurnoNotFound;
import controladores.ccu.exceptions.sigla.SiglaAlreadyExistsException;
import entidades.Refeicao;
import entidades.value_objects.TurnoVO;

public class RefeicaoTest
{


	@Test(expected = DescricaoEmpty.class)
	public void testDescricao() throws ClassNotFoundException, SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound, DescricaoEmpty, OpVegEmpty, TurnoEmpty
	{
		Refeicao refeicao = new Refeicao();
		refeicao.criarRefeicao("qualquer coisa", "", TurnoVO.MANHA.getTurno());
	}

	@Test(expected = OpVegEmpty.class)
	public void testOpVeg() throws ClassNotFoundException, SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound, DescricaoEmpty, OpVegEmpty, TurnoEmpty
	{
		Refeicao refeicao = new Refeicao();
		refeicao.criarRefeicao("", "whatever", TurnoVO.MANHA.getTurno());
	}
	
	@Test(expected = TurnoEmpty.class)
	public void testTurno() throws ClassNotFoundException, SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound, DescricaoEmpty, OpVegEmpty, TurnoEmpty
	{
		Refeicao refeicao = new Refeicao();
		refeicao.criarRefeicao("qualquer coisa", "whatever", "");
	}

}
