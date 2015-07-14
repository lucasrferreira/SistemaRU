package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Refeicao;
import entidades.value_objects.RefeicaoVO;

public class GerirRefeicao
{

	public static Collection<RefeicaoVO> listarRefeicoes() throws Exception
	{
		return Refeicao._listarRefeicoesDisponiveis();
	}

	public static RefeicaoVO buscarRefeicao(RefeicaoVO refeicaoAntigo) throws ClassNotFoundException, SQLException
	{

		refeicaoAntigo = Refeicao._buscarRefeicao(refeicaoAntigo);

		return refeicaoAntigo;
	}

	public static void criarRefeicao(RefeicaoVO dpto) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException
	{

		if (Refeicao._buscarRefeicao(dpto) != null)
		{
			Refeicao._adicionarRefeicao(dpto);
			// retorno um refeicao bobo }
		}
	}

	public static void atualizarRefeicao(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException
	{

		refeicao = buscarRefeicao(refeicao);

		Refeicao._atualizarRefeicao(refeicao);
	}
}
