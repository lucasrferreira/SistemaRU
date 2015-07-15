package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Refeicao;
import entidades.RefeicaoFinder;
import entidades.value_objects.TurnoVO;

public class GerirRefeicao
{

	public static Collection<Refeicao> listarRefeicoes() throws Exception
	{
		return RefeicaoFinder._listarRefeicoesDisponiveis();
	}

	public static Refeicao buscarRefeicao(int idRefeicao) throws ClassNotFoundException, SQLException
	{

		Refeicao refeicaoAntigo = RefeicaoFinder._buscarRefeicao(idRefeicao);

		return refeicaoAntigo;
	}

	public static void criarRefeicao(String op_veg, String descricao, String turno) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException
	{

		Refeicao refeicao = new Refeicao( descricao, op_veg);
		if (turno.equals(TurnoVO.MANHA.getTurno()))
			refeicao.setTurno(TurnoVO.MANHA);
		if (turno.equals(TurnoVO.NOITE.getTurno()))
			refeicao.setTurno(TurnoVO.NOITE);
		if (turno.equals(TurnoVO.TARDE.getTurno()))
			refeicao.setTurno(TurnoVO.TARDE);

		refeicao._adicionarRefeicao();
		// retorno um refeicao bobo }
	}

	public static void atualizarRefeicao(int idRefeicao, String op_veg, String descricao, String turno) throws ClassNotFoundException, SQLException
	{
		Refeicao refeicao = new Refeicao(idRefeicao, descricao, op_veg);

		refeicao = RefeicaoFinder._buscarRefeicao(idRefeicao);

		if (turno.equals(TurnoVO.MANHA.getTurno()))
			refeicao.setTurno(TurnoVO.MANHA);
		if (turno.equals(TurnoVO.NOITE.getTurno()))
			refeicao.setTurno(TurnoVO.NOITE);
		if (turno.equals(TurnoVO.TARDE.getTurno()))
			refeicao.setTurno(TurnoVO.TARDE);
		refeicao.setDescricao(descricao);
		refeicao.setOp_veg(op_veg);

		refeicao._atualizarRefeicao();
	}
}
